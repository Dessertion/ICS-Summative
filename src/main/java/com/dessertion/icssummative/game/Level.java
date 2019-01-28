package com.dessertion.icssummative.game;

import com.dessertion.icssummative.engine.Timer;
import com.dessertion.icssummative.engine.graphics.*;
import com.dessertion.icssummative.engine.sound.Sound;
import com.dessertion.icssummative.game.entities.*;
import com.dessertion.icssummative.game.entities.projectiles.Projectile;
import com.dessertion.icssummative.game.entities.towers.*;
import com.dessertion.icssummative.game.gui.BuyMenu;
import com.dessertion.icssummative.game.state.MainGameState;
import com.dessertion.icssummative.game.util.BloonFactory;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static com.dessertion.icssummative.engine.Engine.proj_mat;

/**
 * @author Dessertion
 */
public class Level {
	
	private VertexArray mesh;
	private Texture     tex;
	private BuyMenu     menu;
	private boolean     click    = false;
	
	private Timer       timer;
	
	public static boolean     newRound = true;
	public static int         money    = 650;
	
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
		Sound.loopSound(Sound.BGM);
	}
	
	public void render() {
		shader.enable();
		tex.bind();
		mesh.render();
		tex.unbind();
		shader.disable();
		menu.render();
		Bloon.renderAll();
		for(Entity e : Entity.entities){
			if(e instanceof Bloon)continue;
			e.render();
		}
		
	}
	
	public void update(float interp) {
		//update things
		for(int i = 0 ; i < Entity.entities.size(); i++)Entity.entities.get(i).update(interp);
		menu.update();
		
		//handle click events
		handleClickEvents();
		
		if(BloonFactory.startWave){
			newRound =false;
			BloonFactory.spawnWave();
		}
		else{
			if(Bloon.bloons.isEmpty()&&!newRound){
				newRound =true;
				nextRound();
			}
		}
		
		
		//remove killed
		for(int i = 0 ; i < Entity.entities.size(); i++){
			if(Entity.entities.get(i).isKill()){
				Entity.entities.get(i).release();
			}
		}
		
	}
	
	private void nextRound() {
		money+=99+BloonFactory.waveNum;
		if(BloonFactory.done()) MainGameState.gameWin=true;
	}
	
	private void handleClickEvents() {
		if(MouseInput.MOUSE_DOWN) click=true;
		else if(click){
			if(BuyMenu.buyingTower!=null)placeTower();
			click = false;
			menu.checkButtons();
		}
	}
	
	private void placeTower() {
		TowerType buying = BuyMenu.buyingTower;
		Vector3f mousePos = new Vector3f((float)MouseInput.lastClickX,(float)MouseInput.lastClickY,0);
		for(Tower t : Tower.towers){
			Vector3f dis = new Vector3f(mousePos).sub(t.getPosition());
			if(dis.length()<t.getSize()/2+buying.size/2){
				return;
			}
		}
		
		BuyMenu.buyingTower=null;
		
		switch(buying){
			case DART_TOWER: {
				new DartTower((float)MouseInput.lastClickX, (float)MouseInput.lastClickY);
				break;
			}
			case TACK_TOWER: new TackTower((float)MouseInput.lastClickX, (float)MouseInput.lastClickY);break;
			case BOMB_TOWER: break;
			case SUPER_TOWER: break;
		}
	}
	
}

