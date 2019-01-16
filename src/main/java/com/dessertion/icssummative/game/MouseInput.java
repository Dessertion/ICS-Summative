package com.dessertion.icssummative.game;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;

/**
 * @author Dessertion
 */
//TODO
public class MouseInput extends GLFWMouseButtonCallback {
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		if(button == GLFW_MOUSE_BUTTON_1){
			System.out.println("aaa");
		}
	}
}

