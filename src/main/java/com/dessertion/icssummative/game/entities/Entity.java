package com.dessertion.icssummative.game.entities;

import com.dessertion.icssummative.engine.graphics.Texture;
import com.dessertion.icssummative.engine.graphics.VertexArray;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	protected Vector3f looking;
	protected Matrix4f view_mat;
	

	
	protected Matrix4f model_mat = new Matrix4f();
	
	
	protected boolean kill = false;

	public static List<Entity> entities = Collections.synchronizedList(new ArrayList<>());
	
	public Entity(float x, float y) {
		this.x = x;
		this.y = y;
		position = new Vector3f(x, y, 0f);
		looking = new Vector3f(0,1,0);
		entities.add(this);
	}
	
	public Entity(float x, float y, float width, float height, float depth) {
		this(x, y);
		this.width = width;
		this.height = height;
		this.depth = depth;
		mesh = VertexArray.createMesh(width,height,depth);
	}
	
	public void release() {
		entities.remove(this);
	}
	
	public abstract void update();
	
	public abstract void render();
	
	//<editor-fold desc="Getter Setters">
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
	
	public Texture getTex() {
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
	
	public Matrix4f getView_mat() {
		return view_mat;
	}
	
	public void setView_mat(Matrix4f view_mat) {
		this.view_mat = view_mat;
	}
	
	public boolean isKill() {
		return kill;
	}
	
	public void setKill(boolean kill) {
		this.kill = kill;
	}
	
	public Matrix4f getModel_mat() {
		return model_mat;
	}
	
	public void setModel_mat(Matrix4f model_mat) {
		this.model_mat = model_mat;
	}
	//</editor-fold>
	
}

