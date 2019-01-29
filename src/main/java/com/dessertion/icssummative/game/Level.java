package com.dessertion.icssummative.game;

import com.dessertion.icssummative.engine.Engine;
import com.dessertion.icssummative.engine.Timer;
import com.dessertion.icssummative.engine.graphics.*;
import com.dessertion.icssummative.engine.sound.Sound;
import com.dessertion.icssummative.engine.sound.SoundClip;
import com.dessertion.icssummative.game.entities.*;
import com.dessertion.icssummative.game.entities.NumImage;
import com.dessertion.icssummative.game.entities.projectiles.Projectile;
import com.dessertion.icssummative.game.entities.towers.*;
import com.dessertion.icssummative.game.gui.Button;
import com.dessertion.icssummative.game.gui.InGameGUI;
import com.dessertion.icssummative.game.input.MouseInput;
import com.dessertion.icssummative.game.state.MainGameState;
import com.dessertion.icssummative.game.util.*;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import javax.xml.soap.Text;
import java.util.ArrayList;

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
	
	private SoundClip bgm;
	
	public ArrayList<Button> endGameButtons = new ArrayList<>();
	public TexturedImage winScreen, loseScreen;
	
	private static Shader shader = new Shader("/shaders/level.vert", "/shaders/level.frag")
			.setUniform1i("tex", 0)
			.setUniformMat4f("proj_mat", proj_mat)
			.setUniformMat4f("view_mat", new Matrix4f().translate(-4, -3, 0));
	
	
	public Level() {
		tex = new Texture("/textures/level.png");
	}
	
	public void init() {
		System.out.println(Entity.entities.size());
		if(!NumImage.intialized)NumImage.init();
		if(!TextImage.initialized) TextImage.init();
		mesh = VertexArray.createMesh(8,6,0);
		menu = new InGameGUI() ;
		
		BloonFactory.init();
		menu.init();
		timer = new Timer();
		
		timer.init();
		bgm = Sound.loopSound(Sound.BGM);
		newRound=true;
		money=650;
		lives=200;
		gameWin=false;
		gameLose=false;
		
		Button retry = new Button(-2.5f,-2,2,1,-0.96f);
		retry.loadTexture("/textures/retry.png");
		retry.addButtonListener(()->{MainGameState.doRelease=true;
			MainGameState.levelInit=false;});
		Button quit = new Button(0.5f,-2,2,1,-0.96f);
		quit.loadTexture("/textures/quit.png");
		quit.addButtonListener(()->{MainGameState.doRelease = true;
			Engine.running=false;});
		
		if(endGameButtons.isEmpty()) {
			endGameButtons.add(retry);
			endGameButtons.add(quit);
		}
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
		
		if(gameLose||gameWin) {
			if(gameLose)loseScreen = new TexturedImage(0f,0,6, 4, -0.95f, "/textures/game_lose.png");
			else winScreen = new TexturedImage(0f,0,6, 4, -0.95f, "/textures/game_win.png");
			endGameButtons.forEach(Button::render);
		}
	}
	
	public void update(float interp) {
		//handle click events
		handleClickEvents();
		
		//update things
		for(int i = 0 ; i < Entity.entities.size(); i++)Entity.entities.get(i).update(interp);
		
		menu.update();
		
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
			if(!gameLose && !gameWin) {
				if (placeTower()) return;
				if (menu.checkButtons()) return;
				for (Tower t : Tower.towers) if (t.checkClick()) return;
			}
			else{
				for(Button b: endGameButtons)if(b.checkClick()){
					b.performAction();
					return;
				}
			}
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
		for(FloatRect r : TrackAvoid.track){
			if(r.contains(mousePos.x, mousePos.y))return false;
		}
		
		InGameGUI.buyingTower=null;
		Level.money-=buying.cost;
		float mx = (float)MouseInput.lastClickX;
		float my = (float)MouseInput.lastClickY;
		switch(buying){
			case DART_TOWER: new DartTower(mx,my);break;
			case TACK_TOWER: new TackTower(mx, my);break;
			case BOMB_TOWER: new BombTower(mx, my);break;
			case SUPER_TOWER: new SuperTower(mx,my); break;
		}
		return true;
	}
	
	public void release(){
		Entity.entities.clear();
		Tower.towers.clear();
		Projectile.projectiles.clear();
		Bloon.bloons.clear();
		bgm.stop();
	}
	
}

