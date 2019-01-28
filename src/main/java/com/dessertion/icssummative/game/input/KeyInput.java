package com.dessertion.icssummative.game.input;

import com.dessertion.icssummative.game.gui.BuyMenu;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;

/**
 * @author Dessertion
 */
public class KeyInput extends GLFWKeyCallback {
	
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		if(key==GLFW_KEY_ESCAPE&&action == GLFW_PRESS){
			BuyMenu.buyingTower=null;
		}
	}
}

