package com.dessertion.icssummative.game.entities;

import com.dessertion.icssummative.engine.graphics.*;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.*;

import static com.dessertion.icssummative.engine.Engine.proj_mat;

/**
 * @author Dessertion
 */
public class TexturedImage extends Entity {
	
	public static Shader              imgShader      = new Shader("/shaders/bloon.vert","/shaders/bloon.frag").setUniformMat4f("proj_mat",proj_mat);
	public TexturedImage(float x, float y, float width, float height, float depth, String path){
		super(x,y,width,height,depth);
		tex = new Texture(path);
	}
	
	public void update(float interp){
	
	}
	
	public void render(){
		imgShader.enable();
		tex.bind();
		Vector3f off = new Vector3f(-width/2,-height/2,0);
		imgShader.setUniformMat4f("model_mat", new Matrix4f().translate(position));
		imgShader.setUniformMat4f("view_mat", new Matrix4f().translate(off));
		mesh.render();
		tex.unbind();
		imgShader.disable();
	}
	
}

