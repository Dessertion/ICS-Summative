package com.dessertion.icssummative.game;

import com.dessertion.icssummative.engine.graphics.VertexArray;

/**
 * @author Dessertion
 */
public class Level {
	
	private VertexArray background;
	private float[]     vertices;
	private byte[]      indices;
	private float[]     tex;
	
	public Level() {
		init();
	}
	
	private void init() {
		vertices = new float[]{
				-4f, -3f, 0f,
				-4f, 3f, 0f,
				4f, 3f, 0f,
				4f, -3f, 0f
		};
		
		indices = new byte[]{
				0, 1, 2,
				2, 3, 0
		};
		//TODO understand this
		tex = new float[]{
				0, 1,
				0, 0,
				1, 0,
				1, 1
		};
		
		background = new VertexArray(vertices,indices,tex);
	}
	
	public void render(){
	
	}
	
}

