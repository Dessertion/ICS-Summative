package com.dessertion.icssummative;

import com.dessertion.icssummative.engine.GameWindow;
import com.dessertion.icssummative.state.StateManager;
import org.lwjgl.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {
	
	private GameWindow window;
	private boolean running;
	private boolean paused;
	
	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");
		init();
		loop();
		

		
		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
		
		running = false;
	}
	
	private void init() {
		StateManager.init();
		running = true;
	}
	
	private void loop() {
		//create GL capabilities
		GL.createCapabilities();
		// Set the clear color
		glClearColor(1.0f, 0.0f, 1.0f, 0.0f);
		
		//game loop
		while (!glfwWindowShouldClose(window)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
			
			glfwSwapBuffers(window); // swap the color buffers
			
			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
		}
	}
	
	/**
	 * Returns current state of the game
	 *
	 * @return True if the game is running
	 */
	public boolean isRunning() {
		return running;
	}
	
	/**
	 * Returns if the game is paused
	 *
	 * @return True if game paused
	 */
	public boolean isPaused() {
		return paused;
	}
	
	public static void main(String[] args) {
		new Main().run();
	}
	
}