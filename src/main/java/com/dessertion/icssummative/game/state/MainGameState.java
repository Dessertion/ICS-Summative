package com.dessertion.icssummative.game.state;

import com.dessertion.icssummative.engine.Window;
import com.dessertion.icssummative.game.Level;
import com.dessertion.icssummative.game.entities.Bloon;
import com.dessertion.icssummative.game.util.Node;

import static org.lwjgl.opengl.GL11.GL_NO_ERROR;
import static org.lwjgl.opengl.GL11.glGetError;

/**
 * @author Dessertion
 */
public class MainGameState implements State{
	
	private       Level   level;
	public static boolean gameWin = false;
	
	@Override
	public void init() {
		Node.init();
		level = new Level();
	}
	
	@Override
	public void update(double interval) {
		if(gameWin){
			//TODO implement winning thing
			System.out.println("hooray! you won!");
		}
		level.update();
	}
	
	@Override
	public void render(Window window) {
		level.render();
		int err = glGetError();
		if(err!=GL_NO_ERROR)System.out.println(err);
	}
	
	@Override
	public void release() {
		Bloon.removeAll();
		Node.releaseAll();
	}
	
	@Override
	public void keyboardUpdate(Window window) {
	
	}
	
	@Override
	public void mouseUpdate(Window window) {
	
	}
}

