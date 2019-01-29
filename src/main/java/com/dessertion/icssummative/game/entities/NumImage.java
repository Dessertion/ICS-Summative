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
public class NumImage extends Entity{
	
	public static BufferedImage[] num = new BufferedImage[10];
	public static Shader numberShader = new Shader("/shaders/number.vert","/shaders/number.frag").setUniformMat4f("proj_mat", proj_mat);
	public static void init(){
		BufferedImage numbers = null;
		try {
			numbers = ImageIO.read(NumImage.class.getResource("/textures/numbers.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			BufferedImage sub = numbers.getSubimage(i*8, 0, 8, 8);
			num[i] = sub;
		}
	}
	
	private String s;
	private float scale;
	private BufferedImage img;
	public NumImage(String s, float x, float y, float scale) {
		super(x, y, s.length()*8*scale, 8*scale, -0.9f);
		this.s=s;
		img = create();
		tex = new Texture(img);
	}
	
	private BufferedImage create() {
		BufferedImage ret = new BufferedImage(s.length()*8,8,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = ret.createGraphics();
		if(Integer.parseInt(s)<0)s=Integer.toString(-Integer.parseInt(s));
		for(int i = 0 ; i < s.length(); i++){
			int n = s.charAt(i)-'0';
			g2d.drawImage(num[n],i*8,0,null);
		}
		g2d.dispose();
		return ret;
	}
	
	public String getString(){
		return s;
	}
	
	public void setString(String s){
		this.s=s;
		width = s.length()*8*scale;
		mesh = VertexArray.createMesh(width,height,depth);
		img = create();
		tex = new Texture(img);
	}
	
	
	@Override
	public void update(float interp) {
	
	}
	
	@Override
	public void render() {
		numberShader.enable();
		tex.bind();
		numberShader.setUniformMat4f("model_mat", new Matrix4f().translate(position));
		mesh.render();
		tex.unbind();
		numberShader.disable();
	}
}

