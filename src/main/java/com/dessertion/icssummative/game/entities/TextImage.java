package com.dessertion.icssummative.game.entities;

import com.dessertion.icssummative.engine.graphics.*;
import org.joml.Matrix4f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.dessertion.icssummative.engine.Engine.proj_mat;

/**
 * @author Dessertion
 */
public class TextImage extends Entity {
	
	public static BufferedImage[] chars = new BufferedImage[26];
	public static Shader textShader = new Shader("/shaders/number.vert","/shaders/number.frag").setUniformMat4f("proj_mat", proj_mat);
	public static boolean initialized = false;
	
	public static void init(){
		initialized=true;
		BufferedImage characters = null;
		try {
			characters = ImageIO.read(TextImage.class.getResource("/textures/alphabet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 0 ; i < 26; i++){
			BufferedImage sub = characters.getSubimage(i*10,0,10,8);
			chars[i] = sub;
		}
	}
	
	private String s;
	private float scale;
	private BufferedImage img;
	public TextImage(String s, float x, float y, float scale){
		super(x,y,s.length()*scale,scale,-0.9f);
		this.s=s;
		img = create();
		tex = new Texture(img);
	}
	
	public String getString(){
		return s;
	}
	
	public void setString(String s){
		this.s=s;
		width = s.length()*10*scale;
		mesh = VertexArray.createMesh(width,height,depth);
		img = create();
		tex = new Texture(img);
	}
	
	private BufferedImage create() {
		BufferedImage ret = new BufferedImage(s.length()*10,8,BufferedImage.TYPE_INT_ARGB);
		Graphics2D    g2d = ret.createGraphics();
		for(int i = 0 ; i < s.length(); i++){
			int n = s.charAt(i)-'A';
			g2d.drawImage(chars[n],i*10,0,null);
		}
		g2d.dispose();
		return ret;
	}
	
	@Override
	public void update(float interp) {
	
	}
	
	@Override
	public void render() {
		textShader.enable();
		tex.bind();
		textShader.setUniformMat4f("model_mat", new Matrix4f().translate(position));
		mesh.render();
		tex.unbind();
		textShader.disable();
	}
}

