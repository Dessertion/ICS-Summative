package com.dessertion.icssummative.game.state;

import com.dessertion.icssummative.engine.Window;
import com.dessertion.icssummative.game.Level;
import com.dessertion.icssummative.game.entities.Bloon;
import com.dessertion.icssummative.game.util.Node;
import com.dessertion.icssummative.game.util.TrackAvoid;

import static org.lwjgl.opengl.GL11.GL_NO_ERROR;
import static org.lwjgl.opengl.GL11.glGetError;

/**
 * @author Dessertion
 */
public class MainGameState implements State{
	
	private       Level   level;
	public static boolean levelInit = false;
	public static boolean doRelease = false;
	
	@Override
	public void init() {
		Node.init();
		level = new Level();
		TrackAvoid.init();
	}
	
	@Override
	public void update(double interval) {
		if(doRelease){
			release();
			doRelease=false;
			return;
		}
		if(!levelInit){
			level.init(); levelInit=true;
			return;
		}
		level.update((float) interval);
	}
	
	@Override
	public void render(Window window) {
		if(!levelInit){
			level.init(); levelInit=true;
		}
		level.render();
		int err = glGetError();
		if(err!=GL_NO_ERROR)System.out.println(err);
	}
	
	@Override
	public void release() {
		level.release();
	}
	
	@Override
	public void keyboardUpdate(Window window) {
	
	}
	
	@Override
	public void mouseUpdate(Window window) {
	
	}
}

