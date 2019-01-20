package com.dessertion.icssummative.game.state;

import com.dessertion.icssummative.engine.Window;
import com.dessertion.icssummative.game.Level;

import static org.lwjgl.opengl.GL11.*;

public class TestState implements State {
	
	Level level;
	
	@Override
	public void init() {
		level = new Level();
	}
	
	@Override
	public void update(double interval) {
	
	}
	
	@Override
	public void render(Window window) {
		level.render();
		int err = glGetError();
		if(err!=GL_NO_ERROR)System.out.println(err);
	}
	
	@Override
	public void release() {
	}
	
	@Override
	public void keyboardUpdate(Window window) {
	
	}
	
	@Override
	public void mouseUpdate(Window window) {
	
	}
}
