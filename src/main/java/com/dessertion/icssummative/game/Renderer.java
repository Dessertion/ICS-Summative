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
	
	public void init() throws Exception {
		shader = new EngineShader();
		shader.createVertexShader(Util.loadResourceAsString("/vertex.vs"));
		shader.createFragmentShader(Util.loadResourceAsString("/fragment.vs"));
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
			
			//create vao and bind it
			vaoId = glGenVertexArrays();
			glBindVertexArray(vaoId);
			
			//create vbo and bind it
			vboId = glGenBuffers();
			glBindBuffer(GL_ARRAY_BUFFER,vboId);
			glBufferData(GL_ARRAY_BUFFER,verticesBuffer,GL_STATIC_DRAW);
			
			//define structure of the data
			glVertexAttribPointer(0,3,GL_FLOAT,false,0,0);
			
			glBindBuffer(GL_ARRAY_BUFFER,0);
			glBindVertexArray(0);
			
			shader.unbind();
			
		} finally {
			//manually free the memory :P
			if(verticesBuffer!=null)MemoryUtil.memFree(verticesBuffer);
		}
		
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
		
		//draw triangles
		glDrawArrays(GL_TRIANGLES, 0, 3);
		
		//restore state
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
		
		shader.unbind();
	}
	
	public void release(){
		if(shader!=null)shader.release();
		
		glDisableVertexAttribArray(0);
		
		//delete vbo
		glBindBuffer(GL_ARRAY_BUFFER,0);
		glDeleteBuffers(vboId);
		
		//delete vao
		glBindVertexArray(0);
		glDeleteVertexArrays(vaoId);
	}
	
	
	
	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
}
