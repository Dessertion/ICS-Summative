package com.dessertion.icssummative.engine;

public class GameEngine {


	private static final int TARGET_FPS = 60;
	private static final int TARGET_UPS = 30;
	private final GameWindow window;
	private final Thread gameThread;


	public GameEngine(String windowTitle, int width, int height, boolean vsync) throws Exception{
		window = new GameWindow(windowTitle,width,height,vsync);
	}

}
