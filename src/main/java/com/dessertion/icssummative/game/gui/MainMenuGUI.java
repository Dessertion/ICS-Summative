package com.dessertion.icssummative.game.gui;

import com.dessertion.icssummative.engine.graphics.Texture;
import org.joml.Vector3f;

import java.util.*;

/**
 * @author Dessertion
 */
public class MainMenuGUI extends GUI {
	
	public static List<Button> buttons = Collections.synchronizedList(new ArrayList<>());
	
	public MainMenuGUI() {
		super(new Vector3f(-4,-3,0), 10, 6);
		tex = new Texture("/textures/monkeys_vs_bloons.png");
	}
	
	@Override
	public void init() {
		Button startButton = new Button()
	}
	
	@Override
	public void update() {
	
	}
	
	@Override
	public void render() {
		
		renderComponents();
	}
	
	@Override
	public void renderComponents() {
	
	}
}

