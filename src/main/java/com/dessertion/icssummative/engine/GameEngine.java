package com.dessertion.icssummative.engine;

import com.dessertion.icssummative.engine.state.StateManager;

public class GameEngine implements Runnable {


	private static final int TARGET_FPS = 60;
	private static final int TARGET_UPS = 30;
	private final GameTimer timer;
	private final GameWindow window;
	private final Thread gameThread;
	private boolean running;


	public GameEngine(String windowTitle, int width, int height, boolean vsync) throws Exception {
		window = new GameWindow(windowTitle, width, height, vsync);
		gameThread = new Thread(this, "GAME_THREAD");
		timer = new GameTimer();
	}

	public void start() {
		String osName = System.getProperty("os.name");
		if (osName.contains("Mac")) {
			gameThread.run();
		} else {
			gameThread.start();
		}
	}

	@Override
	public void run() {
		try {
			init();
			loop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void init() {
		window.init();
		timer.init();
		StateManager.init();
	}

	/**
	 * Main game loop
	 */
	protected void loop() {
		float elapsed;
		float accumulated = 0f;
		float interval = 1f / TARGET_UPS;

		running = true;
		//loop while running and the window hasn't received a close command
		while(running && !window.windowShouldClose()){
			elapsed=timer.getElapsedTime();
			accumulated+=elapsed;

			getInput();

			while(accumulated>=interval){
				update(interval);
				accumulated-=interval;
			}
			//render once per loop, then wait until the render time slot is over before restarting
			render();
			sync();

		}
		release();
	}

	protected void release() {
		window.release();
	}

	protected void update(float interval) {
		StateManager.update();
	}

	protected void getInput() {
		StateManager.getInput();
	}

	protected void render() {
		StateManager.render();
		window.update();

	}

	/**
	 * Syncs rendering to the render interval
	 */
	private void sync(){
		float renderInterval = 1f/TARGET_FPS;
		double endTime = timer.getLastCallTime() + renderInterval;
		//while current time is less than allotted time, wait
		while(timer.getTime()<endTime){
			try{
				Thread.sleep(1);
			}catch (InterruptedException e) {}
		}
	}


	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
