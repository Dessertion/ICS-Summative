package com.dessertion.icssummative.game;

import com.dessertion.icssummative.engine.Timer;
import com.dessertion.icssummative.engine.graphics.*;
import com.dessertion.icssummative.game.entities.*;
import com.dessertion.icssummative.game.gui.BuyMenu;
import com.dessertion.icssummative.game.state.MainGameState;
import com.dessertion.icssummative.game.util.BloonFactory;
import com.dessertion.icssummative.game.util.BloonWave;
import org.joml.Matrix4f;

import static com.dessertion.icssummative.engine.Engine.proj_mat;

/**
 * @author Dessertion
 */
public class Level {
	
	private VertexArray         mesh;
	private Texture             tex;
	private BuyMenu             menu;
	private int                 waveNum;
	private boolean             click     = false;
	private boolean             startWave = false;
	private BloonWave           currentWave = null;
	private Timer               timer;
	private BloonWave.BloonInfo currentInfo = null;
	
	private static Shader shader = new Shader("/shaders/level.vert", "/shaders/level.frag")
			.setUniform1i("tex", 0)
			.setUniformMat4f("proj_mat", proj_mat)
			.setUniformMat4f("view_mat", new Matrix4f().translate(-4, -3, 0));
	
	public Level() {
		init();
		tex = new Texture("/textures/level.png");
	}
	
	private void init() {
		mesh = VertexArray.createMesh(8,6,0);
		menu = new BuyMenu();
		BloonFactory.init();
		timer = new Timer();
		timer.init();
	}
	
	public void render() {
		shader.enable();
		tex.bind();
		mesh.render();
		tex.unbind();
		shader.disable();
		menu.render();
		Bloon.renderAll();
	}
	
	public void update() {
		Bloon.updateAll();
		//handle click events
		handleClickEvents();
		if(startWave){
			spawnWave();
		}
		//remove killed
		for(int i = 0 ; i < Entity.entities.size(); i++){
			if(Entity.entities.get(i).isKill()){
				Entity e = Entity.entities.get(i);
				e.release();
			}
		}
		System.out.println(Entity.entities.size());
	}
	
	private void handleClickEvents() {
		if(MouseInput.MOUSE_DOWN) click=true;
		else if(click&&!startWave&&Bloon.bloons.isEmpty()){
			click = false;
			
			if(BloonFactory.done()){
				MainGameState.gameWin = true;
				return;
			}
			
			startWave=true;
			currentWave = BloonFactory.getNextWave();
			waveNum = currentWave.getWaveNum();
			currentInfo =currentWave.getNext();
			
			timer.setTime();
		}
	}
	
	
	private int numSpawned = 0;
	private void spawnWave() {
		if(currentInfo.num<=numSpawned){
			currentInfo = currentWave.getNext();
			timer.setTime();
			numSpawned=0;
			if(currentInfo==null){
				startWave=false;
				return;
			}
		}
		if(timer.getElapsedSinceSetTime()>=currentInfo.delay){
			timer.setTime();
			BloonFactory.createBloon(currentInfo.bloonType);
			numSpawned++;
		}
	}
}

