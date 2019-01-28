package com.dessertion.icssummative.game.gui;

import com.dessertion.icssummative.engine.graphics.Texture;
import com.dessertion.icssummative.game.Level;
import com.dessertion.icssummative.game.entities.towers.*;
import com.dessertion.icssummative.game.util.BloonFactory;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.ArrayList;

/**
 * @author Dessertion
 */
public class BuyMenu extends Menu {
	
	private static final float WIDTH = 2f;
	private static final float HEIGHT = 6f;
	private static final Vector3f position = new Vector3f(4,-3,0);
	
	private static ArrayList<Button> buttons = new ArrayList<>();
	
	public static TowerType buyingTower = null;
	
	
	public BuyMenu() {
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
		startButton.loadTexture("/textures/start_button.png");
		startButton.addButtonListener(BloonFactory::beginWaveSpawning);
		buttons.add(startButton);
		
		Button dartMonkeyButton = new TowerBuyButton(4.1f,1f,0.5f, 0.5f,TowerType.DART_TOWER);
		dartMonkeyButton.loadTexture("/textures/dart_monkey1.png");
		buttons.add(dartMonkeyButton);
		
		Button tackTowerButton = new TowerBuyButton(4.7f, 1f,0.5f, 0.5f,TowerType.TACK_TOWER);
		tackTowerButton.loadTexture("/textures/tack_tower1.png");
		buttons.add(tackTowerButton);
	}
	
	@Override
	public void update() {
		buttons.forEach(Button::update);
	}
	
	public void checkButtons(){
		for(Button b : buttons){
			if(b.checkClick()){
				b.performAction();
			}
		}
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
		if(buyingTower!=null){
		
		
		}
	}
	
	@Override
	public void renderComponents() {
		buttons.forEach(Button::render);
	}
}

