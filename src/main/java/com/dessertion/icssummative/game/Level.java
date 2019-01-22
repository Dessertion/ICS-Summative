package com.dessertion.icssummative.game;

import com.dessertion.icssummative.engine.graphics.*;
import com.dessertion.icssummative.game.entities.Bloon;

import static com.dessertion.icssummative.engine.Engine.proj_mat;

/**
 * @author Dessertion
 */
public class Level {
	
	private        VertexArray mesh;
	private        Texture     tex;
	private static Shader      shader = new Shader("/shaders/bg.vert", "/shaders/bg.frag")
											.setUniform1i("tex", 0)
											.setUniformMat4f("proj_mat", proj_mat);
	
	
	private float[] vertices;
	private float[] tcs;
	private byte[]  indices;
	
	public Level() {
		init();
		tex = new Texture("/textures/level.png");
		Bloon test = new Bloon(0,0,Bloon.BloonType.TEST);
	}
	
	private void init() {
		createMesh();
	}
	
	private void createMesh() {
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
		tcs = new float[]{
				0, 1,
				0, 0,
				1, 0,
				1, 1
		};
		
		mesh = new VertexArray(vertices, indices, tcs);
	}
	
	public void render() {
		
		shader.enable();
		tex.bind();
		mesh.render();
		tex.unbind();
		shader.disable();
		
		Bloon.renderAll();
	}
	
	public void update() {
		Bloon.updateAll();
	}
}

