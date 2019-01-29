package com.dessertion.icssummative.game.entities.towers;

import com.dessertion.icssummative.engine.graphics.Texture;
import com.dessertion.icssummative.game.entities.Bloon;
import com.dessertion.icssummative.game.entities.projectiles.Dart;
import org.joml.Vector3f;

/**
 * @author Dessertion
 */
public class SuperTower extends Tower{
	
	public SuperTower(float x, float y) {
		super(x, y, TowerType.SUPER_TOWER.size);
		type = TowerType.SUPER_TOWER;
		pierce = 1;
		range = 3f;
		rate = 5f;
		physical = true;
		tex = new Texture(type.path);
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

