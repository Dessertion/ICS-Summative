package com.dessertion.icssummative.game;

import com.dessertion.icssummative.engine.graphics.*;
import com.dessertion.icssummative.game.entities.Bloon;
import com.dessertion.icssummative.game.entities.Entity;
import org.joml.Matrix4f;

import static com.dessertion.icssummative.engine.Engine.proj_mat;

/**
 * @author Dessertion
 */
public class Level {
	
	private VertexArray mesh;
	private Texture     tex;
	private BuyMenu     menu;
	
	private static Shader shader = new Shader("/shaders/level.vert", "/shaders/level.frag")
			.setUniform1i("tex", 0)
			.setUniformMat4f("proj_mat", proj_mat)
			.setUniformMat4f("view_mat", new Matrix4f().translate(-4, -3, 0));
	
	
	public Level() {
		init();
		tex = new Texture("/textures/level.png");
	}
	
	private void init() {
		mesh = VertexArray.createMesh(8,6,0);
		Bloon test = new Bloon(Bloon.BloonType.BLUE);
	//	menu = new BuyMenu();
		
	}
	
	public void render() {
		
		shader.enable();
		tex.bind();
		mesh.render();
		tex.unbind();
		shader.disable();
		//menu.render();
		Bloon.renderAll();
		
	}
	
	public void update() {
		Bloon.updateAll();
		
		//remove killed
		for(int i = 0 ; i < Entity.entities.size(); i++){
			if(Entity.entities.get(i).isKill())Entity.entities.remove(i);
		}
	}
}

