package com.dessertion.icssummative.game.state;

import com.dessertion.icssummative.engine.Window;
import com.dessertion.icssummative.game.Renderer;

public class TestState implements State {
	
	private Renderer renderer;
	
	@Override
	public void init() {
		renderer = new Renderer();
		try {
			renderer.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(float interval) {
	
	}
	
	@Override
	public void render(Window window) {
		renderer.render(window);
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
