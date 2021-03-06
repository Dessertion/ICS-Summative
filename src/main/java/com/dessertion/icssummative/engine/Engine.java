package com.dessertion.icssummative.engine;

import com.dessertion.icssummative.game.state.StateManager;
import org.joml.Matrix4f;
import org.lwjgl.Version;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.*;

public class Engine implements Runnable {
	
	
	public static final int     TARGET_FPS = 60;
	public static final int     TARGET_UPS = 60;
	private final       Timer   timer;
	private final       Window  window;
	private final       Thread  gameThread;
	public static       boolean running;
	
	public static Matrix4f proj_mat = new Matrix4f().ortho(-4.0f, 6.0f, -3.0f, 3.0f, 1.0f, -1.0f);
	
	public static int WIDTH, HEIGHT;
	
	public Engine(String windowTitle, int width, int height, boolean vsync) throws Exception {
		window = new Window(windowTitle, width, height, vsync);
		WIDTH = width;
		HEIGHT = height;
		gameThread = new Thread(this, "GAME_THREAD");
		timer = new Timer();
		
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
		} finally {
			release();
		}
	}
	
	protected void init() {
		System.out.println("OpenGL Version: " + Version.getVersion());
		window.init();
		timer.init();
		StateManager.init();
	}
	
	/**
	 * Main game loop
	 */
	protected void loop() {
		float  elapsed;
		float  accumulated = 0f;
		float  interval    = 1f / TARGET_UPS;
		int    frames      = 0, updates = 0;
		double frameTimer  = timer.getTime();
		running = true;
		//loop while running and the window hasn't received a close command
		while (running && !window.windowShouldClose()) {
			
			elapsed = timer.getElapsedTime();
			accumulated += elapsed;
			
			getInput();
			
			while (accumulated >= interval) {
				update(interval);
				updates++;
				accumulated -= interval;
			}
			//render once per loop, then wait until the render time slot is over before restarting
			render();
			frames++;
			
			if (timer.getTime() >= frameTimer + 1) {
				frameTimer++;
				System.out.println(updates + " ups " + frames + " fps");
				updates = 0;
				frames = 0;
			}
			//TODO figure out how to sync without destroying fps lol
			//sync(lastUpdateTime);
			
		}
	}
	
	/**
	 * Syncs rendering to the render interval
	 */
	private void sync(double lastUpdateTime) {
		double renderInterval = 1.0 / TARGET_FPS;
		double endTime        = timer.getLastCallTime() + renderInterval;
		double current;
		//while current time is less than allotted time, wait
		while ((current = timer.getTime()) < endTime && current - lastUpdateTime < 1.0 / TARGET_UPS) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
	}
	
	protected void update(float interval) {
		glfwPollEvents();
		StateManager.update(interval);
	}
	
	protected void getInput() {
		StateManager.getInput(window);
	}
	
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		StateManager.render(window);
		window.render();
	}
	
	
	protected void release() {
		window.release();
		StateManager.release();
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
}
