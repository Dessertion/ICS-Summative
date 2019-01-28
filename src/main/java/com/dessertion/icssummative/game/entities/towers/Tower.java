package com.dessertion.icssummative.game.entities.towers;

import com.dessertion.icssummative.engine.Timer;
import com.dessertion.icssummative.engine.graphics.Shader;
import com.dessertion.icssummative.game.entities.Bloon;
import com.dessertion.icssummative.game.entities.Entity;
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
	}
	
	@Override
	public void release() {
		super.release();
		towers.remove(this);
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
	
	
	//</editor-fold>
	
}

