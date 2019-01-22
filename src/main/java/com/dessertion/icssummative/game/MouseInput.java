package com.dessertion.icssummative.game;

import com.dessertion.icssummative.engine.Engine;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

/**
 * @author Dessertion
 */
//TODO
public class MouseInput extends GLFWMouseButtonCallback {
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		if(button == GLFW_MOUSE_BUTTON_1&&action==GLFW_PRESS){
			DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
			DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);
			glfwGetCursorPos(window,posX,posY);
			double width = 4, height = 3;
			
			double normalizedX = -width + 2*width* (posX.get(0)/Engine.WIDTH);
			double normalizedY = height - 2*height* (posY.get(0)/Engine.HEIGHT);
			System.out.println(normalizedX + " " + normalizedY);
		}
	}
}

