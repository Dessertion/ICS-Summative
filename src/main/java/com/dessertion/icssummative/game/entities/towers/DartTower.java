package com.dessertion.icssummative.game.entities.towers;

import com.dessertion.icssummative.engine.graphics.Texture;
import com.dessertion.icssummative.game.entities.Bloon;
import com.dessertion.icssummative.game.entities.projectiles.Dart;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * @author Dessertion
 */
public class DartTower extends Tower{
	
	public DartTower(float x, float y) {
		super(x, y,0.65f);
		pierce = 2;
		range = 1.5f;
		rate = 1f;
		physical = true;
		tex = new Texture("/textures/dart_monkey1.png");
	}
	
	@Override
	protected void shoot(Bloon target) {
		timer.setTime();
		Vector3f dir = new Vector3f(target.getPosition()).sub(position);
		ang = dir.angle(new Vector3f(0.0f,1.0f,0.0f));
		if(dir.x>0)ang*=-1;
		Dart dart = new Dart(new Vector3f(position), dir,this);
		render();
	}
	
	@Override
	public void update(float interp) {
		Bloon target = getFirstBloonInRange();
		if(target!=null) {
			if(timer.getElapsedSinceSetTime()>1/rate)shoot(target);
		}
	}
	
}

