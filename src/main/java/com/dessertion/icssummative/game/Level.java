package com.dessertion.icssummative.game;

import com.dessertion.icssummative.engine.Timer;
import com.dessertion.icssummative.engine.graphics.*;
import com.dessertion.icssummative.engine.sound.Sound;
import com.dessertion.icssummative.game.entities.*;
import com.dessertion.icssummative.game.entities.NumImage;
import com.dessertion.icssummative.game.entities.towers.*;
import com.dessertion.icssummative.game.gui.InGameGUI;
import com.dessertion.icssummative.game.input.MouseInput;
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
	private InGameGUI   menu;
	private boolean     click    = false;
	
	private Timer       timer;
	
	public static boolean     newRound = true;
	public static boolean gameWin = false;
	public static boolean gameLose = false;
	public static int         money    = 650;
	public static int lives = 200;
	
	
	
	private static Shader shader = new Shader("/shaders/level.vert", "/shaders/level.frag")
			.setUniform1i("tex", 0)
			.setUniformMat4f("proj_mat", proj_mat)
			.setUniformMat4f("view_mat", new Matrix4f().translate(-4, -3, 0));
	
	public Level() {
		init();
		tex = new Texture("/textures/level.png");
	}
	
	private void init() {
		NumImage.init();
		
		mesh = VertexArray.createMesh(8,6,0);
		menu = new InGameGUI();
		BloonFactory.init();
		timer = new Timer();
		timer.init();
		Sound.loopSound(Sound.BGM);
		newRound=true;
		money=650;
		lives=200;
		gameWin=false;
		gameLose=false;
		
	
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
		
		if(gameLose){
			System.out.println("oof you lost");
			return;
		}
		
		if(gameWin){
			System.out.println("hooray! you won!");
			return;
		}
		
		
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
		//check game lost state
		if(lives<=0)gameLose=true;
		
	}
	
	private void nextRound() {
		money+=99+BloonFactory.waveNum;
		if(BloonFactory.done()) gameWin=true;
	}
	
	private void handleClickEvents() {
		if(MouseInput.MOUSE_DOWN) click=true;
		else if(click){
			click = false;
			if(placeTower())return;
			if(menu.checkButtons())return;
			for(Tower t : Tower.towers)if(t.checkClick())return;
		}
	}
	
	private boolean placeTower() {
		TowerType buying = InGameGUI.buyingTower;
		if(buying==null)return false;
		Vector3f mousePos = new Vector3f((float)MouseInput.lastClickX,(float)MouseInput.lastClickY,0);
		for(Tower t : Tower.towers){
			Vector3f dis = new Vector3f(mousePos).sub(t.getPosition());
			if(dis.length()<t.getSize()/2+buying.size/2)return false;
		}
		
		InGameGUI.buyingTower=null;
		Level.money-=buying.cost;
		float mx = (float)MouseInput.lastClickX;
		float my = (float)MouseInput.lastClickY;
		switch(buying){
			case DART_TOWER: {
				new DartTower(mx,my);
				break;
			}
			case TACK_TOWER: new TackTower(mx, my);break;
			case BOMB_TOWER: new BombTower(mx, my);break;
			case SUPER_TOWER: new SuperTower(mx,my); break;
		}
		return true;
	}
	
}

