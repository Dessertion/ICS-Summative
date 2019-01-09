package com.dessertion.icssummative.engine.state;

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
	void update();
	
	/**
	 * Render to screen
	 */
	void render();
	
	/**
	 * Destroys game state
	 */
	void release();
	
	/**
	 * Processes user keyboard input
	 */
	void keyboardUpdate();
	
	/**
	 * Processes user mouse input
	 */
	void mouseUpdate();
	
}



