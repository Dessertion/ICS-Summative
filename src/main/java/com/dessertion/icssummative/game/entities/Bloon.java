package com.dessertion.icssummative.game.entities;

import com.dessertion.icssummative.engine.Engine;
import com.dessertion.icssummative.engine.graphics.*;
import com.dessertion.icssummative.engine.sound.Sound;
import com.dessertion.icssummative.engine.sound.SoundClip;
import com.dessertion.icssummative.engine.util.Lerp;
import com.dessertion.icssummative.game.Level;
import com.dessertion.icssummative.game.entities.projectiles.Projectile;
import com.dessertion.icssummative.game.util.FloatRect;
import com.dessertion.icssummative.game.util.Node;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.*;

import static com.dessertion.icssummative.engine.Engine.proj_mat;

/**
 * @author Dessertion
 */
public class Bloon extends Entity{
	
	public static Shader bloonShader = new Shader("/shaders/bloon.vert","/shaders/bloon.frag")
			.setUniform1i("tex",0)
			.setUniformMat4f("proj_mat",proj_mat);
	
	public static List<Bloon> bloons = Collections.synchronizedList(new ArrayList<Bloon>());
	
	private float     size;
	private float     speed;
	private Projectile immuneAgainst;
	
	private float     distance = 0 ;
	private BloonType type;
	private int       nextNode = 0;
	
	private FloatRect hitbox;
	
	
	public Bloon(BloonType type){
		super(Node.BEGIN.getX(),Node.BEGIN.getY(),type.size*3/4,type.size,-0.2f);
		setType(type);
	}
	
	public Bloon(float x, float y, BloonType type){
		this(type);
		this.x=x;
		this.y=y;
	}
	private Bloon(Bloon bloon){
		super(bloon.x,bloon.y,bloon.width,bloon.height,-0.2f);
		setType(bloon.type);
	}
	
	public void setType(BloonType type){
		mesh = VertexArray.createMesh(type.size*3/4,type.size,-0.2f);
		tex = new Texture("/textures/" + type.texString);
		this.size = type.size;
		this.type = type;
		this.speed=type.speed;
		hitbox = new FloatRect(position.x-size/2,position.y-size/2,size,size);
	}
	
	
	public enum BloonType{
		RED(0.4f,0.01f,"red_bloon.png"),
		BLUE(1.05f*0.4f,0.02f,"blue_bloon.png"),
		GREEN(1.1f*0.4f,0.03f,"green_bloon.png"),
		YELLOW(1.12f*0.4f,0.05f,"yellow_bloon.png"),
		PINK(1.13f*0.4f,0.06f,"pink_bloon.png"),
		RAINBOW(1.13f*0.4f,0.05f,"rainbow_bloon.png"),
		LEAD(1.1f*0.4f,0.01f,"lead_bloon.png"),
		MOAB(2f,0.01f,"moab.png"),
		TEST(0.2f,0.01f,"test.png");
		private final float size;
		private final float speed;
		private final String texString;
		BloonType(float size, float speed, String texString){
			this.size=size;
			this.texString=texString;
			this.speed=speed;
		}
		public float getSize(){return size;}
		
		public String getTextureString(){return texString;}
	}
	
	@Override
	public void update(float interp) {
		Node node = Node.nodes.get(nextNode);
		Vector3f dir = new Vector3f(node.getV()).sub(position);
		if(dir.length()<0.06f){
			if(node == Node.END){
				kill();
				return;
			}
			node = Node.nodes.get(++nextNode);
			dir = dir = new Vector3f(node.getV()).sub(position);
		}
		dir.normalize().mul(speed);
		position.lerp(new Vector3f(position).add(dir),interp*Engine.TARGET_UPS);
		dir = new Vector3f().lerp(dir, interp*Engine.TARGET_UPS);
		hitbox.translate(dir);
		distance+=dir.length();
	}
	
	public void pop(Projectile source){
		if(source==immuneAgainst)return;
		Level.money++;
		Sound.playSound(Sound.POP);
		switch (type) {
			case BLUE:
				setType(BloonType.RED);
				break;
			case GREEN:
				setType(BloonType.BLUE);
				break;
			case YELLOW:
				setType(BloonType.GREEN);
				break;
			case PINK:
				setType(BloonType.YELLOW);
				break;
			case RAINBOW:
				setType(BloonType.PINK);
				Bloon newSpawn = new Bloon(this);
				newSpawn.immuneAgainst = source;
				break;
			case LEAD:
				setType(BloonType.YELLOW);
				break;
			case RED:
			default:
				kill();
				break;
		}
		immuneAgainst=source;
	}
	
	@Override
	public void render() {
		tex.bind();
		bloonShader.enable();
		mesh.render();
		bloonShader.disable();
		tex.unbind();
	}
	
	private void renderAllUtil(){
		view_mat = new Matrix4f().translate(-width/2,-height/2,0);
		bloonShader.setUniformMat4f("view_mat", view_mat);
		bloonShader.setUniformMat4f("model_mat", new Matrix4f().translate(position));
		mesh.render();
	}
	
	public static void renderAll(){
		bloonShader.enable();
		Texture temp = null;
		for(Bloon bloon : bloons){
			if(bloon.getTex()!=temp){
				temp = bloon.getTex();
				temp.bind();
			}
			bloon.renderAllUtil();
		}
		if(temp!=null)temp.unbind();
		bloonShader.disable();
	}
	
	@Override
	public void release(){
		super.release();
		bloons.remove(this);
	}
	
	public static void removeAll() {
		bloons.clear();
	}
	
	//<editor-fold desc="Getters">
	public float getSize() {
		return size;
	}
	
	public BloonType getType() {
		return type;
	}
	
	public float getDistance() {
		return distance;
	}
	
	public FloatRect getHitbox() {
		return hitbox;
	}
	//</editor-fold>
}

