package com.dessertion.icssummative.engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.*;

public final class GameWindow {

	private String title;
	private int width, height;
	private boolean vsync;



	private boolean resized;
	private long windowHandle;

	/**
	 * Constructor for game window
	 * @param title
	 * @param width
	 * @param height
	 * @param vsync
	 */
	public GameWindow(String title, int width, int height, boolean vsync){
		this.title=title;
		this.width=width;
		this.height=height;
		this.vsync=vsync;
		this.resized=false;
	}

	/**
	 * Called on fist time initialization of game window
	 */
	public void init(){
		//set error callback
		GLFWErrorCallback.createPrint(System.err).set();

		//initialize glfw
		if(!glfwInit())throw new IllegalStateException("Unable to initialize GLFW");

		//set default window hints
		glfwDefaultWindowHints();

		//create window
		windowHandle = glfwCreateWindow(width,height,title,NULL,NULL);
		if(windowHandle == NULL)throw new RuntimeException("Failed to create GLFW window");

		//setup resize callback
		glfwSetFramebufferSizeCallback(windowHandle, (window,width,height)->{
			this.width=width;
			this.height=height;
			this.setResized(true);
		});

		//TODO see if can remove?
		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		glfwSetKeyCallback(windowHandle, (window, key, scancode, action, mods) -> {
			if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
			}
		});

		//get resolution of primary monitor
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		//center window
		glfwSetWindowPos(windowHandle,(vidmode.width()-width)/2,(vidmode.height()-height)/2);

		//make the OpenGL context current
		glfwMakeContextCurrent(windowHandle);

		//set vsync
		if(isVSync())glfwSwapInterval(1);

		//make window visible
		glfwShowWindow(windowHandle);

		//create OpenGL capabilities
		GL.create();
		//set clear colour
		glClearColor(1.0f,1.0f,0.0f,0.0f);

	}

	public void setClearColor(float r, float g, float b, float a){
		glClearColor(r,g,b,a);
	}

	public boolean windowShouldClose(){
		return glfwWindowShouldClose(windowHandle);
	}

	public boolean isKeyPressed(int keyCode){
		return glfwGetKey(windowHandle,keyCode)==GLFW_PRESS;
	}

	/**
	 * Called on window destruction
	 */
	public void release(){
		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(windowHandle);
		glfwDestroyWindow(windowHandle);
	}


	//getter setters
	//TODO javadocs
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isVSync() {
		return vsync;
	}

	public void setVSync(boolean vsync) {
		this.vsync = vsync;
	}

	public boolean isResized() {
		return resized;
	}

	public void setResized(boolean resized) {
		this.resized = resized;
	}

	/**
	 * Call on window update (renders)
	 */
	public void update(){
		glfwSwapBuffers(windowHandle);
		glfwPollEvents();
	}
}
