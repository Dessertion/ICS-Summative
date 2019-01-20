package com.dessertion.icssummative.game.state;

import com.dessertion.icssummative.engine.Window;

/*
State interface, containing mandatory methods for a valid state
@author dessertion
 */
public interface State {
	
	/**
	 * Called on first time initialization
	 */
	void init();
	
	/**
	 * Update state information
	 */
	void update(double interval);
	
	/**
	 * Render to screen
	 */
	void render(Window window);
	
	/**
	 * Destroys game state
	 */
	void release();
	
	/**
	 * Processes user keyboard input
	 */
	void keyboardUpdate(Window window);
	
	/**
	 * Processes user mouse input
	 */
	void mouseUpdate(Window window);
	
}



