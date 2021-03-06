package com.dessertion.icssummative.game.entities.towers;

import com.dessertion.icssummative.engine.Timer;
import com.dessertion.icssummative.engine.graphics.Shader;
import com.dessertion.icssummative.game.entities.Bloon;
import com.dessertion.icssummative.game.entities.Entity;
import com.dessertion.icssummative.game.gui.Button;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.*;

import static com.dessertion.icssummative.engine.Engine.proj_mat;

/**
 * @author Dessertion
 */
public abstract class Tower extends Entity {
	
	protected int     pierce;
	protected float   range;
	protected float   rate;
	protected float   size;
	protected boolean physical;
	
	protected TowerType type;
	
	
	protected Button b;
	
	protected              com.dessertion.icssummative.engine.Timer timer;
	public final static float                                    hitSize = 0.2f;
	
	
	
	public static Shader towerShader = new Shader("/shaders/tower.vert", "/shaders/tower.frag")
			.setUniformMat4f("proj_mat", proj_mat);
	
	public static List<Tower> towers = Collections.synchronizedList(new ArrayList<>());
	
	public Tower(float x, float y, float size) {
		super(x,y,size,size,-0.5f);
		towers.add(this);
		timer = new Timer();
		this.size=size;
		b = new Button(x-size/2,y-size/2,size,size);
		b.addButtonListener(()->{
			System.out.println("yeet");});
	}
	
	@Override
	public void release() {
		super.release();
		towers.remove(this);
	}
	
	@Override
	public void render(){
		towerShader.setUniformMat4f("model_mat", new Matrix4f().translate(position).rotateZ(ang));
		Vector3f off = new Vector3f(-width/2,-height/2,0).rotateZ(ang);
		towerShader.setUniformMat4f("view_mat", new Matrix4f().translate(off));
		towerShader.enable();
		tex.bind();
		mesh.render();
		tex.unbind();
		towerShader.disable();
	}
	
	public boolean checkClick(){
		if(b.checkClick()){
			b.performAction();
			return true;
		}
		return false;
	}
	
	protected Bloon getFirstBloonInRange() {
		Bloon ret = null;
		for (Bloon b : Bloon.bloons) {
			float displ = new Vector3f(b.getPosition()).sub(position).length();
			if ((displ < range) && (ret == null || b.getDistance() > ret.getDistance())) ret = b;
		}
		return ret;
	}
	
	protected abstract void shoot(Bloon bloon);
	
	
	
	//<editor-fold desc="Getter Setters">
	public float getRange() {
		return range;
	}
	
	public void setRange(float range) {
		this.range = range;
	}
	
	public float getRate() {
		return rate;
	}
	
	public void setRate(float rate) {
		this.rate = rate;
	}
	
	public boolean isPhysical() {
		return physical;
	}
	
	public void setPhysical(boolean physical) {
		this.physical = physical;
	}
	
	public int getPierce() {
		return pierce;
	}
	
	public static float getHitSize() {
		return hitSize;
	}
	
	public float getSize() {
		return size;
	}
	
	public Button getB() {
		return b;
	}
	
	
	//</editor-fold>
	
}

