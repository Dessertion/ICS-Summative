package com.dessertion.icssummative.game;

import com.dessertion.icssummative.engine.Window;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {
	
	public Renderer() {
	
	}
	
	public void init() throws Exception {
	
	}
	
	public void render(Window window) {
		clear();
	}
	
	public void release(){
	}
	
	
	
	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
}
