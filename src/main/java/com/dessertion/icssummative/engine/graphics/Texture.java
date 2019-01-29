package com.dessertion.icssummative.engine.graphics;

import com.dessertion.icssummative.engine.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Dessertion
 */
public class Texture {
	private int width;
	private int height;
	private int texture;
	
	public Texture(String path){
		texture = load(path);
	}
	
	public Texture(BufferedImage img){
		texture = load(img);
	}
	
	private int load(BufferedImage img) {
		width = img.getWidth();
		height = img.getHeight();
		int[] pixels = new int[width * height];
		img.getRGB(0, 0, width, height, pixels, 0, width);
		
		int[] data = ARGBtoRGBA(pixels);
		int   ret  = glGenTextures();
		
		//bind tex
		glBindTexture(GL_TEXTURE_2D, ret);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
		glBindTexture(GL_TEXTURE_2D, 0);
		return ret;
	}
	
	private int load(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return load(img);
	}
	
	public int[] ARGBtoRGBA(int[] pixels) {
		int[] ret = new int[pixels.length];
		int a,r,g,b;
		for(int i = 0; i < width*height;i++){
			Color c = new Color(pixels[i],true);
			a = c.getAlpha();
			r = c.getRed();
			g = c.getGreen();
			b = c.getBlue();
			ret[i] = a<<24|b<<16|g<<8|r;
		}
		return ret;
	}
	
	public void bind(){
		glBindTexture(GL_TEXTURE_2D,texture);
	}
	
	public void unbind(){
		glBindTexture(GL_TEXTURE_2D,0);
	}
	
}

