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
	protected float depth;
	
	protected VertexArray mesh;
	protected Texture     tex;
	
	protected Vector3f position;
	protected Matrix4f model_mat;
	
	public Entity(float x, float y){
		this.x=x;
		this.y=y;
		position = new Vector3f(x,y,0f);
	}
	
	public Entity(float x, float y, float width, float height, float depth){
		this(x,y);
		this.width=width;
		this.height=height;
		this.depth = depth;
		createMesh(depth);
	}
	
	public enum EntityType{
		BLOON,
		MOAB,
		DART,
		BOMB,
		NODE,
		DART_TOWER,
		TACK_TOWER,
		BOMB_TOWER,
		SUPER_TOWER
	}
	
	protected void createMesh(float depth){
		float[] vertices = new float[]{
				0.0f, 0.0f, depth,
				0.0f, height, depth,
				width, height, depth,
				width, 0.0f, depth
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
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getWidth() {
		return width;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public float getDepth() {
		return depth;
	}
	
	public void setDepth(float depth) {
		this.depth = depth;
	}
	
	public VertexArray getMesh() {
		return mesh;
	}
	
	public void setMesh(VertexArray mesh) {
		this.mesh = mesh;
	}
	
	public Texture getTexture() {
		return tex;
	}
	
	public void setTex(Texture tex) {
		this.tex = tex;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public Matrix4f getModel_mat() {
		return model_mat;
	}
	
	public void setModel_mat(Matrix4f model_mat) {
		this.model_mat = model_mat;
	}
	
	
	
}

