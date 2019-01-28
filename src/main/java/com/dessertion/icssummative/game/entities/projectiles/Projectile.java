package com.dessertion.icssummative.game.entities.projectiles;

import com.dessertion.icssummative.engine.Engine;
import com.dessertion.icssummative.engine.graphics.Shader;
import com.dessertion.icssummative.game.entities.Bloon;
import com.dessertion.icssummative.game.entities.Entity;
import com.dessertion.icssummative.game.util.FloatRect;
import org.joml.Vector3f;

import java.util.*;

import static com.dessertion.icssummative.engine.Engine.proj_mat;

/**
 * @author Dessertion
 */
public abstract class Projectile extends Entity {
	
	public static List<Projectile> projectiles = Collections.synchronizedList(new ArrayList<>());
	public static Shader projectileShader = new Shader("/shaders/projectile.vert","/shaders/projectile.frag")
					.setUniformMat4f("proj_mat", proj_mat);
	
	protected float travelDis;
	protected float pierce;
	protected Vector3f velocity;
	protected FloatRect hitbox;
	
	public Projectile(float x, float y, float width, float height) {
		super(x, y, width, height,-0.6f);
		projectiles.add(this);
		hitbox = new FloatRect(x-width/2,y-height/2,width,height);
	}
	
	public abstract void onHit(Bloon target);
	
	@Override
	public void release(){
		super.release();
		projectiles.remove(this);
	}
	
	@Override
	public void update(float interp){
		Bloon b = checkCollision();
		if(b!=null)onHit(b);
		Vector3f lerpVel = new Vector3f().lerp(velocity, interp* Engine.TARGET_UPS);
		position = position.add(lerpVel);
		hitbox.translate(lerpVel);
		travelDis-=lerpVel.length();
		if(travelDis<=0||pierce<=0)kill();
	}
	
	protected Bloon checkCollision(){
		Bloon ret = null;
		for(Bloon b : Bloon.bloons){
			if(hitbox.intersects(b.getHitbox())){
				ret = b;
				break;
			}
		}
		return ret;
	}
}

