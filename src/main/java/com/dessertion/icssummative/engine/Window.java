package com.dessertion.icssummative.engine;

import com.dessertion.icssummative.game.input.KeyInput;
import com.dessertion.icssummative.game.input.MouseInput;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.stb.STBImage.stbi_load;
import static org.lwjgl.system.MemoryUtil.*;

public final class Window {
	
	private String title;
	private int    width, height;
	private long    windowHandle;
	private boolean vsync;
	
	/**
	 * Constructor for game window
	 *
	 * @param title  Title of Window
	 * @param width  Width in px
	 * @param height Height in px
	 * @param vsync  If vsync is on or off
	 */
	public Window(String title, int width, int height, boolean vsync) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.vsync = vsync;
	}
	
	/**
	 * Called on first time initialization of game window
	 */
	public void init() {
		//set error callback
		GLFWErrorCallback.createPrint(System.err).set();
		
		//initialize glfw
		if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");
		
		//set default window hints
		glfwDefaultWindowHints();
		//for mac compatibility
		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
		
		//create window
		windowHandle = glfwCreateWindow(width, height, title, NULL, NULL);
		if (windowHandle == NULL) throw new RuntimeException("Failed to create GLFW window");
		
		setIcon();
		
		//centre window
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(windowHandle, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
		
		glfwMakeContextCurrent(windowHandle);//make the OpenGL context current
		glfwShowWindow(windowHandle); //make window visible
		
		//set vsync
		if (isVSync()) glfwSwapInterval(1);
		
		//create OpenGL capabilities
		GL.createCapabilities();
		
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_BLEND);
		glEnable(GL_LINE_SMOOTH);
		glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glActiveTexture(GL_TEXTURE0);
		
		setClearColor(0,0,0,0);	//set clear colour
		
		//setup input callbacks
		glfwSetMouseButtonCallback(windowHandle, new MouseInput());
		glfwSetKeyCallback(windowHandle, new KeyInput());
	}
	
	private void setIcon(){
		//TODO maybe later
	}
	
	/**
	 * Called on window destruction
	 */
	public void release() {
		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(windowHandle);
		glfwDestroyWindow(windowHandle);
	}
	
	/**
	 * Call on window render (renders)
	 */
	public void render() {
		MouseInput.update(windowHandle);
		glfwSwapBuffers(windowHandle);
	}
	
	public void setClearColor(float r, float g, float b, float a) {
		glClearColor(r, g, b, a);
	}
	
	public boolean windowShouldClose() {
		return glfwWindowShouldClose(windowHandle);
	}
	
	//<editor-fold desc="Getter Setters">
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
		//set vsync
		if (isVSync()) glfwSwapInterval(1);
		else glfwSwapInterval(0);
	}
	//</editor-fold>
	
	
}
