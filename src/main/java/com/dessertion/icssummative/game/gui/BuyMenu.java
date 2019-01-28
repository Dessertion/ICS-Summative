package com.dessertion.icssummative.game.gui;

import com.dessertion.icssummative.engine.graphics.Texture;
import com.dessertion.icssummative.game.Level;
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
	
	public BuyMenu() {
		super(position, WIDTH, HEIGHT);
		tex = new Texture("/textures/buy_menu.png");
		init();
	}
	
	@Override
	public void init() {
		
		Button startButton = new Button(4.25f,-2f, 1.5f, 1f);
		startButton.loadTexture("/textures/start_button.png");
		startButton.addButtonListener(()->{
			BloonFactory.beginWaveSpawning();
			System.out.println("heyaaaa");});
		buttons.add(startButton);
	}
	
	@Override
	public void update() {
	
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
	}
	
	@Override
	public void renderComponents() {
		buttons.forEach(Button::render);
	}
}

