package com.dessertion.icssummative.game.entities;

import com.dessertion.icssummative.engine.graphics.Shader;
import com.dessertion.icssummative.engine.graphics.Texture;
import org.joml.Matrix4f;

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
	private BloonType type;
	
	public Bloon(float x, float y, BloonType type){
		//TODO maybe create bloon factory? idk
		super(x,y,type.size,type.size,-0.2f);
		//TODO moab
		tex = new Texture("/textures/" + type.texString);
		this.size = size;
		this.type = type;
		bloons.add(this);
	}
	
	public enum BloonType{
		RED(0.5f,"red_bloon.png"),
		BLUE(1.15f*0.5f,"blue_bloon.png"),
		GREEN(1.3f*0.5f,"green_bloon.png"),
		YELLOW(1.5f*0.5f,"yellow_bloon.png"),
		PINK(1.7f*0.5f,"pink_bloon.png"),
		RAINBOW(1.8f*0.5f,"rainbow_bloon.png"),
		LEAD(1.4f*0.5f,"lead_bloon.png"),
		MOAB(2f,"moab.png"),
		TEST(5f,"test.png");
		private final float size;
		private final String texString;
		BloonType(float size, String texString){
			this.size=size;
			this.texString=texString;
		}
		public float getSize(){return size;}
		
		public String getTextureString(){return texString;}
	}
	
	@Override
	public void update() {
	
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

