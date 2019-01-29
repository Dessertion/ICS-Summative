package com.dessertion.icssummative.game.entities.projectiles;

import com.dessertion.icssummative.engine.graphics.Texture;
import com.dessertion.icssummative.engine.sound.Sound;
import com.dessertion.icssummative.game.entities.Bloon;
import com.dessertion.icssummative.game.entities.towers.BombTower;
import com.dessertion.icssummative.game.entities.towers.Tower;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * @author Dessertion
 */
public class Bomb extends Projectile{
	
	private float blastRad;
	
	public Bomb(Vector3f pos, Vector3f dir, BombTower source) {
		super(pos.x, pos.y, 0.3f, 0.3f);
		tex = new Texture("/textures/bomb.png");
		velocity = new Vector3f(dir).normalize().mul(0.1f);
		ang = source.getAng()+(float)Math.PI/2;
		pierce = source.getPierce();
		travelDis=source.getRange()*1.1f;
		blastRad = source.getBlastRadius();
	}
	
	@Override
	public void onHit(Bloon target) {
		target.pop(this);
		Sound.playSound(Sound.EXPLOSION);
		for(Bloon b : Bloon.bloons){
			Vector3f dis = 	new Vector3f(b.getPosition()).sub(position);
			if(dis.length()<blastRad)b.pop(this);
		}
		pierce--;
		if(pierce<=0)kill();
	}
	
	@Override
	public void render() {
		Vector3f off = new Vector3f(-width/2,-height/2,0);
		projectileShader.setUniformMat4f("view_mat", new Matrix4f().translate(off));
		projectileShader.setUniformMat4f("model_mat", new Matrix4f().translate(position));
		projectileShader.enable();
		tex.bind();
		mesh.render();
		tex.unbind();
		projectileShader.disable();
	}
}

