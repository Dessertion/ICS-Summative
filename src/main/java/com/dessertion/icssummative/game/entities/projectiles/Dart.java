package com.dessertion.icssummative.game.entities.projectiles;

import com.dessertion.icssummative.engine.graphics.Texture;
import com.dessertion.icssummative.game.entities.Bloon;

/**
 * @author Dessertion
 */
public class Dart extends Projectile {
	
	public Dart(float x, float y, float width, float height) {
		super(x, y, width, height);
		tex=new Texture("/textures/dart.png");
	}
	
	@Override
	public void onHit(Bloon target) {
		target.pop();
		pierce--;
		if(pierce<=0)release();
	}
	
	@Override
	public void render() {
	
	}
}

