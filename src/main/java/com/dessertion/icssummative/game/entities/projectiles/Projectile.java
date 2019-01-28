package com.dessertion.icssummative.game.entities.projectiles;

import com.dessertion.icssummative.engine.graphics.Shader;
import com.dessertion.icssummative.game.entities.Bloon;
import com.dessertion.icssummative.game.entities.Entity;
import org.joml.Vector3f;

import java.util.*;

/**
 * @author Dessertion
 */
public abstract class Projectile extends Entity {
	
	public static List<Projectile> projectiles = Collections.synchronizedList(new ArrayList<>());
	public static Shader projectileShader = new Shader("","");
	
	protected float travelDis;
	protected float pierce;
	protected Vector3f velocity;
	
	public Projectile(float x, float y, float width, float height) {
		super(x, y, width, height,-0.6f);
		projectiles.add(this);
	}
	
	public abstract void onHit(Bloon target);
	
	@Override
	public void release(){
		super.release();
		projectiles.remove(this);
	}
	
	@Override
	public void update(){
		Bloon b = checkCollision();
		position.add(velocity);
		travelDis-=velocity.length();
		if(travelDis<=0||pierce<=0)release();
	}
	
	//TODO
	protected Bloon checkCollision(){
		return null;
	}
}

