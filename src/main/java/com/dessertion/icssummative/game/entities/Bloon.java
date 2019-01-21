package com.dessertion.icssummative.game.entities;

import com.dessertion.icssummative.engine.graphics.Shader;
import com.dessertion.icssummative.engine.graphics.Texture;

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
		RED(1f,"red_bloon.png"),
		BLUE(1.15f,"blue_bloon.png"),
		GREEN(1.3f,"green_bloon.png"),
		YELLOW(1.5f,"yellow_bloon.png"),
		PINK(1.7f,"pink_bloon.png"),
		RAINBOW(1.8f,"rainbow_bloon.png"),
		LEAD(1.4f,"lead_bloon.png"),
		MOAB(5f,"moab.png");
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
	
	public static void renderAll(){
		bloonShader.enable();
		Texture temp = null;
		for(Bloon bloon : bloons){
			if(bloon.getTexture()!=temp){
				if(temp!=null)temp.unbind();
				temp = bloon.getTexture();
				temp.bind();
			}
			bloon.getMesh().render();
		}
		if(temp!=null)temp.unbind();
		bloonShader.disable();
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

