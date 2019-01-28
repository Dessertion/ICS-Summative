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
	
	@Override
	public void render() {
		towerShader.setUniformMat4f("model_mat", new Matrix4f().translate(position).rotateZ(ang));
		Vector3f off = new Vector3f(-width/2,-height/2,0).rotateZ(ang);
		towerShader.setUniformMat4f("view_mat", new Matrix4f().translate(off));
		towerShader.enable();
		tex.bind();
		mesh.render();
		tex.unbind();
		towerShader.disable();
	}
}

