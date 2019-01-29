package com.dessertion.icssummative.game.entities.towers;

import com.dessertion.icssummative.engine.graphics.Texture;
import com.dessertion.icssummative.game.entities.Bloon;
import com.dessertion.icssummative.game.entities.projectiles.Dart;
import com.dessertion.icssummative.game.entities.projectiles.Tack;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * @author Dessertion
 */
public class TackTower extends Tower{
	
	public TackTower(float x, float y) {
		super(x, y, 0.5f);
		size = 0.5f;
		pierce = 1;
		range = 0.8f;
		rate = 0.8f;
		physical = true;
		tex = new Texture("/textures/tack_tower1.png");
	}
	
	@Override
	protected void shoot(Bloon bloon) {
		timer.setTime();
		for(int i = 0 ; i < 8; i ++){
			Vector3f dir  = new Vector3f(0,1,0).rotateZ((float)Math.PI/4*i);
			ang = dir.angle(new Vector3f(0.0f,1.0f,0.0f));
			if(dir.x>0)ang*=-1;
			Tack     tack = new Tack(new Vector3f(position).add(new Vector3f(0,0.2f,0).rotateZ(ang)),dir,this);
		}
	}
	
	@Override
	public void update(float interp) {
		Bloon target = getFirstBloonInRange();
		if(target!=null&&timer.getElapsedSinceSetTime()>1/rate)shoot(target);
	}

}

