package com.dessertion.icssummative.game.state;

import com.dessertion.icssummative.engine.Window;
import com.dessertion.icssummative.game.gui.MainMenuGUI;

/**
 * @author Dessertion
 */
public class MainMenuState implements State {
	
	protected MainMenuGUI menu;
	
	@Override
	public void init() {
		
		menu = new MainMenuGUI();
		menu.init();
	}
	
	@Override
	public void update(double interval) {
		menu.update();
	}
	
	@Override
	public void render(Window window) {
		menu.render();
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

