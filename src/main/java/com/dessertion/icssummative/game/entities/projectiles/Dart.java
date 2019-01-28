package com.dessertion.icssummative.game.entities.projectiles;

import com.dessertion.icssummative.engine.graphics.Texture;
import com.dessertion.icssummative.game.entities.Bloon;
import com.dessertion.icssummative.game.entities.towers.Tower;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * @author Dessertion
 */
public class Dart extends Projectile {
	
	public Dart(Vector3f pos, Vector3f dir, Tower source) {
		super(pos.x, pos.y, 0.3f, 0.15f);
		tex=new Texture("/textures/dart.png");
		velocity = new Vector3f(dir).normalize().mul(0.25f);
		ang = source.getAng()+(float)Math.PI/2;
		pierce = source.getPierce();
		travelDis=source.getRange()*1.2f;
	}
	
	@Override
	public void onHit(Bloon target) {
		target.pop(this);
		pierce--;
		if(pierce<=0)kill();
	}
	
	@Override
	public void update(float interp){
		super.update(interp);
	}
	
	@Override
	public void render() {
		Vector3f off = new Vector3f(-width/2,-height/2,0).rotateZ(ang);
		projectileShader.setUniformMat4f("view_mat", new Matrix4f().translate(off));
		projectileShader.setUniformMat4f("model_mat", new Matrix4f().translate(position).rotateZ(ang));
		projectileShader.enable();
		tex.bind();
		mesh.render();
		tex.unbind();
		projectileShader.disable();
	}
}

