package com.dessertion.icssummative.game.entities.towers;

import com.dessertion.icssummative.engine.graphics.Texture;
import com.dessertion.icssummative.game.entities.Bloon;
import com.dessertion.icssummative.game.entities.projectiles.Bomb;
import com.dessertion.icssummative.game.entities.projectiles.Dart;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * @author Dessertion
 */
public class BombTower extends Tower{
	
	
	private float blastRadius;
	
	public BombTower(float x, float y) {
		super(x, y, TowerType.BOMB_TOWER.size);
		type = TowerType.BOMB_TOWER;
		pierce = 1;
		range = 2f;
		rate = 0.5f;
		physical = false;
		tex = new Texture(type.path);
		blastRadius = 1f;
	}
	
	@Override
	public void shoot(Bloon target) {
		timer.setTime();
		Vector3f dir = new Vector3f(target.getPosition()).sub(position);
		ang = dir.angle(new Vector3f(0.0f,1.0f,0.0f));
		if(dir.x>0)ang*=-1;
		Bomb bomb = new Bomb(new Vector3f(position), dir,this);
		render();
	}
	
	@Override
	public void update(float interp) {
		Bloon target = getFirstBloonInRange();
		if(target!=null) {
			if(timer.getElapsedSinceSetTime()>1/rate)shoot(target);
		}
	}
	
	public float getBlastRadius() {
		return blastRadius;
	}
}

