package com.dessertion.icssummative.game.gui;

import com.dessertion.icssummative.engine.graphics.*;
import com.dessertion.icssummative.game.MouseInput;
import com.dessertion.icssummative.game.util.FloatRect;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static com.dessertion.icssummative.engine.Engine.proj_mat;

/**
 * @author Dessertion
 */
public class Button {
	private FloatRect rect;
	private float     xx,yy,ww,hh;
	private       ButtonListener listener;
	private       Texture        texture;
	private       VertexArray    mesh;
	private       Vector3f       position;
	public static Shader         buttonShader = new Shader("/shaders/button.vert", "/shaders/button.frag")
											.setUniformMat4f("proj_mat", proj_mat);
	
	public Button(float x, float y, float w, float h) {
		xx = x;
		yy=y;
		ww=w;
		hh=h;
		rect = new FloatRect(xx,yy,ww,hh);
		position = new Vector3f(xx,yy,0);
		mesh = VertexArray.createMesh(w,h,-0.3f);
	}
	
	public void loadTexture(Texture texture){
		this.texture=texture;
	}
	
	public void loadTexture(String path){
		texture = new Texture(path);
	}
	
	public void addButtonListener(ButtonListener listener){
		this.listener=listener;
	}
	
	public void performAction(){
		if(listener!=null)listener.clicked();
	}
	
	public boolean checkClick(){
		return rect.contains((float)MouseInput.X, (float)MouseInput.Y);
	}
	
	public void render(){
		if(texture!=null)texture.bind();
		buttonShader.enable();
		buttonShader.setUniformMat4f("view_mat", new Matrix4f().translate(position));
		mesh.render();
		buttonShader.disable();
		if(texture!=null)texture.unbind();
	}
	
}

interface ButtonListener{
	void clicked();
}


