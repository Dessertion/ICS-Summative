package com.dessertion.icssummative.game;

import com.dessertion.icssummative.engine.Window;
import com.dessertion.icssummative.engine.Util;
import com.dessertion.icssummative.engine.shader.EngineShader;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer {
	
	private EngineShader shader;
	private int          vaoId;
	private int          vboId;
	
	
	public Renderer() {
	
	}
	
	public void render(Window window) {
		clear();
		if (window.isResized()) {
			glViewport(0, 0, window.getWidth(), window.getHeight());
			window.setResized(false);
		}
		
		shader.bind();
		
		//bind to vao
		glBindVertexArray(vaoId);
		glEnableVertexAttribArray(0);
		
		
	}
	
	public void init() throws Exception {
		shader = new EngineShader();
		shader.createVertexShader(Util.loadResourceAsString("/vertex.vs"));
		shader.createFragmentShader(Util.loadResourceAsString("/fragment.fs"));
		shader.link();
		
		//vertices?
		float[] vertices = new float[]{
				0.0f, 0.5f, 0.0f,
				-0.5f, -0.5f, 0.0f,
				0.5f, -0.5f, 0.0f
		};
		
		FloatBuffer verticesBuffer = null;
		try {
			verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
			verticesBuffer.put(vertices).flip();
			
			//create vao
			vaoId = glGenVertexArrays();
			
			
		} finally {
		
		}
		
	}
	
	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
}
