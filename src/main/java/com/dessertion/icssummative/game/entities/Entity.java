package com.dessertion.icssummative.game.entities;

import com.dessertion.icssummative.engine.graphics.Texture;
import com.dessertion.icssummative.engine.graphics.VertexArray;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * @author Dessertion
 */
public abstract class Entity {
	
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	
	protected VertexArray mesh;
	protected Texture     tex;
	
	protected Vector3f pos;
	protected Matrix4f model_mat;
	
	public Entity(float width, float height){
		this.width=width;
		this.height=height;
		createMesh();
	}
	
	public enum EntityType{
		BLOON,
		BIG_BOI,
		DART,
		BOMB,
		DART_TOWER,
		TACK_TOWER,
		BOMB_TOWER,
		SUPER_TOWER
	}
	
	protected void createMesh(){
		float[] vertices = new float[]{
				0.0f, 0.0f, 0.2f,
				0.0f, height, 0.2f,
				width, height, 0.2f,
				width, 0.0f, 0.2f
		};
		byte[] indices = new byte[]{
				0, 1, 2,
				2, 3, 0
		};
		float[] tcs = new float[]{
				0, 1,
				0, 0,
				1, 0,
				1, 1
		};
		mesh = new VertexArray(vertices, indices, tcs);
	}
	
	public abstract void update();
	
	public abstract void render();
	
	
	
}

