package com.dessertion.icssummative.game;

import com.dessertion.icssummative.engine.Window;
import com.dessertion.icssummative.engine.util.ResourceUtils;
import com.dessertion.icssummative.engine.graphics.ShaderUtil;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer {
	
	private ShaderUtil shaderUtil;
	private int        vaoId;
	private int        vboId;
	
	
	public Renderer() {
	
	}
	
	public void init() throws Exception {
		shaderUtil = new ShaderUtil();
		shaderUtil.createVertexShader(ResourceUtils.loadResourceAsString("/vertex.glsl"));
		shaderUtil.createFragmentShader(ResourceUtils.loadResourceAsString("/fragment.glsl"));
		shaderUtil.link();
		
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
			
			//create vao and enable it
			vaoId = glGenVertexArrays();
			glBindVertexArray(vaoId);
			
			//create vbo and enable it
			vboId = glGenBuffers();
			glBindBuffer(GL_ARRAY_BUFFER,vboId);
			glBufferData(GL_ARRAY_BUFFER,verticesBuffer,GL_STATIC_DRAW);
			
			//define structure of the data
			glVertexAttribPointer(0,3,GL_FLOAT,false,0,0);
			
			glBindBuffer(GL_ARRAY_BUFFER,0);
			glBindVertexArray(0);
			
			shaderUtil.disable();
			
		} finally {
			//manually free the memory :P
			if(verticesBuffer!=null)MemoryUtil.memFree(verticesBuffer);
		}
		
	}
	
	public void render(Window window) {
		clear();
		shaderUtil.enable();
		
		//enable to vao
		glBindVertexArray(vaoId);
		glEnableVertexAttribArray(0);
		
		//draw triangles
		glDrawArrays(GL_TRIANGLES, 0, 3);
		
		//restore state
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
		
		shaderUtil.disable();
	}
	
	public void release(){
		if(shaderUtil !=null) shaderUtil.release();
		
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
