package com.dessertion.icssummative.engine.graphics;

import com.dessertion.icssummative.engine.util.MyBufferUtils;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 * @author Dessertion
 */
public class VertexArray {
	private int vao,vbo,ibo,tbo; //vertex array object, vertex buffer object, indices buffer object, texture buffer object
	private int vcount; //vertices
	
	public VertexArray(float[] vertices, byte[] indices, float[] textureCoordinates){
		init(vertices, indices, textureCoordinates);
	}
	
	private void init(float[] vertices, byte[] indices, float[] textureCoordinates) {
		vcount = indices.length;
		
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER,vbo);
		glBufferData(GL_ARRAY_BUFFER,MyBufferUtils.createFloatBuffer(vertices),GL_STATIC_DRAW);
		glVertexAttribPointer(Shader.VERTEX_ATTRIB,3,GL_FLOAT,false,0,0);
		glEnableVertexAttribArray(Shader.VERTEX_ATTRIB);
		
		tbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER,tbo);
		glBufferData(GL_ARRAY_BUFFER,MyBufferUtils.createFloatBuffer(textureCoordinates),GL_STATIC_DRAW);
		glVertexAttribPointer(Shader.TEX_COORD,2,GL_FLOAT,false,0,0);
		glEnableVertexAttribArray(Shader.TEX_COORD);
		
		ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER,MyBufferUtils.createByteBuffer(indices),GL_STATIC_DRAW);
		
		//unbind stuff
		glBindBuffer(GL_ARRAY_BUFFER,0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);
		glBindVertexArray(0);
	}
	
	public void bind(){
		glBindVertexArray(vao);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);
	}
	
	public void unbind(){
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);
		glBindVertexArray(0);
	}
	
	public void draw(){
		glDrawElements(GL_TRIANGLES,vcount,GL_UNSIGNED_BYTE,0);
	}
	
	public void render(){
		bind();
		draw();
	}
	
}

