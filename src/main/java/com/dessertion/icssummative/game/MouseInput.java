package com.dessertion.icssummative.game;

import com.dessertion.icssummative.engine.Engine;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

/**
 * @author Dessertion
 */
public class MouseInput extends GLFWMouseButtonCallback {
	
	public static boolean MOUSE_DOWN = false;
	public static double X=0;
	public static double Y=0;
	
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		if(button == GLFW_MOUSE_BUTTON_1&&action==GLFW_PRESS){
			DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
			DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);
			glfwGetCursorPos(window,posX,posY);
			double width = 4, height = 3;
			
			//youre gonna settle with magick values cuz i suck at programming :P
			X= -width + 2*width* (posX.get(0)/800);
			Y = height - 2*height* (posY.get(0)/Engine.HEIGHT);
			System.out.println(X + " " + Y);
			MOUSE_DOWN=true;
		}
		else if(button == GLFW_MOUSE_BUTTON_1&&action==GLFW_RELEASE){
			
			MOUSE_DOWN=false;
		}
	}
}

