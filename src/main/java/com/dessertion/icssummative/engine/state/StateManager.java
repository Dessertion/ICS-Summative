package com.dessertion.icssummative.engine.state;

import com.dessertion.icssummative.engine.state.stateExceptions.InvalidStateException;

import java.util.ArrayList;

public final class StateManager {
	
	private static ArrayList<State> states;
	private static State            current;
	private static int              currentIdx;
	
	/**
	 * Called on first initialization of the manager
	 */
	public static void init() {
		states = new ArrayList<>();
		states.add(new MainMenuState());
		states.add(new MainGameState());
		currentIdx = 0;
		current = states.get(0);
	}
	
	/**
	 * Destroys the game manager and all its states
	 */
	public static void release() {
		for (State s : states) s.release();
	}
	
	/**
	 * TODO
	 */
	public static void pause() {
	
	}
	
	/**
	 * TODO
	 */
	public static void resume() {
	
	}
	
	/**
	 * Updates current state
	 */
	public static void update() {
		current.update();
	}
	
	/**
	 * Handles user input events
	 */
	public static void getInput() {
		current.keyboardUpdate();
		current.mouseUpdate();
	}
	
	/**
	 * Sets the current state to the state at the given index
	 *
	 * @param idx index
	 */
	public static void setState(int idx) throws InvalidStateException {
		currentIdx = idx;
		try {
			current = states.get(idx);
		} catch (ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();
			throw new InvalidStateException(idx);
		}
	}
	
	/**
	 * Renders current state to screen
	 */
	public static void render() {
		current.render();
	}
	
}

