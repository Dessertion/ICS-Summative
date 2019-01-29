package com.dessertion.icssummative.game.gui;

import com.dessertion.icssummative.engine.graphics.Texture;
import com.dessertion.icssummative.game.Level;
import com.dessertion.icssummative.game.entities.*;
import com.dessertion.icssummative.game.entities.towers.*;
import com.dessertion.icssummative.game.util.BloonFactory;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.ArrayList;

/**
 * @author Dessertion
 */
public class InGameGUI extends GUI {
	
	private static final float WIDTH = 2f;
	private static final float HEIGHT = 6f;
	private static final Vector3f position = new Vector3f(4,-3,0);
	
	private static ArrayList<Button> buttons = new ArrayList<>();
	
	public static TowerType buyingTower = null;
	
	public static NumImage moneyNum, livesNum;
	
	public static Tower selectedTower = null;
	
	public InGameGUI() {
		super(position, WIDTH, HEIGHT);
		tex = new Texture("/textures/buy_menu.png");
		init();
	}
	
	@Override
	public void init() {
		Button startButton = new Button(4.25f,-2.5f, 1.5f, 1f){
			@Override
			public void update(){
				if(Level.newRound)grey=0;
				else grey =1;
			}
		};
		buyingTower = null;
		selectedTower = null;
		
		startButton.loadTexture("/textures/start_button.png");
		startButton.addButtonListener(BloonFactory::beginWaveSpawning);
		buttons.add(startButton);
		
		Button dartMonkeyButton = new TowerBuyButton(4.3f,1f,0.5f, 0.5f,TowerType.DART_TOWER);
		dartMonkeyButton.loadTexture("/textures/dart_monkey1.png");
		buttons.add(dartMonkeyButton);
		
		Button tackTowerButton = new TowerBuyButton(5f, 1f,0.5f, 0.5f,TowerType.TACK_TOWER);
		tackTowerButton.loadTexture("/textures/tack_tower1.png");
		buttons.add(tackTowerButton);
		
		Button bombTowerButton = new TowerBuyButton(4.3f, 0.3f,0.5f, 0.5f,TowerType.BOMB_TOWER);
		bombTowerButton.loadTexture("/textures/bomb_tower1.png");
		buttons.add(bombTowerButton);
		
		Button superTowerButton = new TowerBuyButton(5f, 0.3f,0.5f, 0.5f,TowerType.SUPER_TOWER);
		superTowerButton.loadTexture("/textures/super_tower1.png");
		buttons.add(superTowerButton);
		
		TexturedImage heart = new TexturedImage(4.3f, 2.5f, 0.25f, 0.25f,-0.9f, "/textures/heart.png");
		TexturedImage cash = new TexturedImage(4.3f,2.1f,0.25f,0.25f, -0.9f,"/textures/money.png");
	
		livesNum = new NumImage(Integer.toString(Level.lives),4.5f,2.4f,0.02f);
		moneyNum = new NumImage(Integer.toString(Level.money),4.5f,2.0f, 0.02f);
	}
	
	@Override
	public void update() {
		buttons.forEach(Button::update);
		if(Integer.parseInt(livesNum.getString())!=Level.lives){
			Entity.entities.remove(livesNum);
			livesNum = new NumImage(Integer.toString(Level.lives),4.5f,2.4f,0.02f);
		}
		if(Integer.parseInt(moneyNum.getString())!=Level.money){
			Entity.entities.remove(moneyNum);
			moneyNum = new NumImage(Integer.toString(Level.money),4.5f,2.0f, 0.02f);
		}
	}
	
	public boolean checkButtons(){
		for(Button b : buttons){
			if(b.checkClick()){
				b.performAction();
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void render() {
		tex.bind();
		menuShader.setUniformMat4f("view_mat",new Matrix4f().translate(position));
		menuShader.enable();
		mesh.render();
		menuShader.disable();
		tex.unbind();
		
		renderComponents();
	}
	
	@Override
	public void renderComponents() {
		buttons.forEach(Button::render);
	}
}

