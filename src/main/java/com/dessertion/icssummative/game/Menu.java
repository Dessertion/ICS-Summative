package com.dessertion.icssummative.game;

import com.dessertion.icssummative.engine.graphics.Shader;
import com.dessertion.icssummative.engine.graphics.Texture;
import com.dessertion.icssummative.engine.graphics.VertexArray;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static com.dessertion.icssummative.engine.Engine.proj_mat;

/**
 * @author Dessertion
 */
public abstract class Menu {
	
	protected VertexArray mesh;
	protected Texture     tex;
//	protected Shader      menuShader = new Shader("/shaders/bloon.vert","/shaders/bloon.frag")
//			.setUniform1i("tex",0)
//			.setUniformMat4f("proj_mat",proj_mat);
	protected Vector3f    position;
	protected float       width;
	protected float       height;
	
	public Menu(Vector3f position, float width, float height){
		this.position=position;
		this.width=width;
		this.height=height;
		mesh = VertexArray.createMesh(width,height,0.1f);
	}
	
	public abstract void init();
	
	public abstract void update();
	
	public abstract void render();
	
	public abstract void renderComponents();
	
	
	
}

