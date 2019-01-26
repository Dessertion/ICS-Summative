package com.dessertion.icssummative.game.gui;

import com.dessertion.icssummative.engine.graphics.Texture;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * @author Dessertion
 */
public class BuyMenu extends Menu {
	
	private static final float WIDTH = 2f;
	private static final float HEIGHT = 6f;
	private static final Vector3f position = new Vector3f(4,-3,0);
	
	public BuyMenu() {
		super(position, WIDTH, HEIGHT);
		tex = new Texture("/textures/buy_menu.png");
		init();
	}
	
	@Override
	public void init() {
	
	}
	
	@Override
	public void update() {
	
	}
	
	@Override
	public void render() {
		tex.bind();
		menuShader.setUniformMat4f("view_mat",new Matrix4f().translate(position));
		menuShader.enable();
		
		mesh.render();
		menuShader.disable();
		tex.unbind();
	}
	
	@Override
	public void renderComponents() {
	
	}
}

