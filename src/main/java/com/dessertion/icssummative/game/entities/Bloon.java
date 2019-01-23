package com.dessertion.icssummative.game.entities;

import com.dessertion.icssummative.engine.graphics.Shader;
import com.dessertion.icssummative.engine.graphics.Texture;
import com.dessertion.icssummative.game.util.Node;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.Comparator;
import java.util.TreeSet;

import static com.dessertion.icssummative.engine.Engine.proj_mat;

/**
 * @author Dessertion
 */
public class Bloon extends Entity{
	
	public static Shader bloonShader = new Shader("/shaders/bloon.vert","/shaders/bloon.frag")
			.setUniform1i("tex",0)
			.setUniformMat4f("proj_mat",proj_mat);
	
	public static TreeSet<Bloon> bloons = new TreeSet<>(Comparator.comparing(Bloon::getType));
	
	private float     size;
	private float     speed;
	private BloonType type;
	private int       nextNode = 0;
	
	public Bloon(BloonType type){
		super(Node.BEGIN.getX(),Node.BEGIN.getY(),type.size*3/4,type.size,-0.2f);
		tex = new Texture("/textures/" + type.texString);
		this.size = size;
		this.type = type;
		this.speed=type.speed;
		bloons.add(this);
	}
	
	
	public enum BloonType{
		RED(0.4f,0.02f,"red_bloon.png"),
		BLUE(1.15f*0.4f,0.04f,"blue_bloon.png"),
		GREEN(1.3f*0.4f,0.06f,"green_bloon.png"),
		YELLOW(1.5f*0.4f,0.1f,"yellow_bloon.png"),
		PINK(1.7f*0.4f,0.12f,"pink_bloon.png"),
		RAINBOW(1.8f*0.4f,0.1f,"rainbow_bloon.png"),
		LEAD(1.4f*0.4f,0.01f,"lead_bloon.png"),
		MOAB(2f,0.01f,"moab.png"),
		TEST(5f,0.5f,"test.png");
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
	public void update() {
		Node node = Node.nodes.get(nextNode);
		Vector3f dir = new Vector3f(node.getV()).sub(position);
		if(dir.length()<0.1f){
			if(node == Node.END){
				kill=true;
				return;
			}
			node = Node.nodes.get(++nextNode);
			dir = dir = new Vector3f(node.getV()).sub(position);
		}
		dir = dir.normalize().mul(speed);
		position.add(dir);
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
		model_mat = new Matrix4f().translate(position);
		bloonShader.setUniformMat4f("model_mat",model_mat);
		mesh.render();
	}
	
	public static void renderAll(){
		bloonShader.enable();
		Texture temp = null;
		for(Bloon bloon : bloons){
			if(bloon.getTexture()!=temp){
				temp = bloon.getTexture();
				temp.bind();
			}
			bloon.renderAllUtil();
		}
		if(temp!=null)temp.unbind();
		bloonShader.disable();
	}
	
	public static void updateAll(){
		bloons.forEach(Bloon::update);
	}
	
	public void remove(){
		bloons.remove(this);
	}
	
	public static void removeAll() {
		bloons.forEach(Bloon::remove);
	}
	
	//<editor-fold desc="Getter Setters">
	public float getSize() {
		return size;
	}
	
	public void setSize(float size) {
		this.size = size;
	}
	
	public BloonType getType() {
		return type;
	}
	
	public void setType(BloonType type) {
		this.type = type;
	}
	//</editor-fold>
}

