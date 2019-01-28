package com.dessertion.icssummative.game.entities;

import java.util.*;

/**
 * @author Dessertion
 */
public abstract class Tower extends Entity {
	
	protected int     cost;
	protected int     pierce;
	protected float   range;
	protected float   rate;
	protected boolean physical;
	
	protected final static float size = 0.2f;
	
	public static List<Tower> towers = Collections.synchronizedList(new ArrayList<>());
	
	public Tower(float x, float y) {
		super(x, y);
		towers.add(this);
	}
	
	@Override
	public void release() {
		super.release();
		towers.remove(this);
	}
	
	protected Bloon getFirstBloonInRange() {
		Bloon ret = null;
		for (Bloon b : Bloon.bloons) {
			float displ = (float) Math.sqrt(Math.pow(b.getX() - x, 2) + Math.pow(b.getY() - y, 2));
			if ((displ < range) && (ret == null || b.getDistance() < ret.getDistance())) ret = b;
		}
		return ret;
	}
	
	protected abstract void shoot(Bloon bloon);
	
	//<editor-fold desc="Getter Setters">
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
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
	//</editor-fold>
	
}

