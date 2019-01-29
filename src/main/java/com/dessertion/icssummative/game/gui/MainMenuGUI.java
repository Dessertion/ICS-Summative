package com.dessertion.icssummative.game.gui;

import com.dessertion.icssummative.engine.graphics.Texture;
import com.dessertion.icssummative.engine.sound.Sound;
import com.dessertion.icssummative.engine.sound.SoundClip;
import com.dessertion.icssummative.game.entities.towers.Tower;
import com.dessertion.icssummative.game.input.MouseInput;
import com.dessertion.icssummative.game.state.StateManager;
import com.dessertion.icssummative.game.state.stateExceptions.InvalidStateException;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.*;

/**
 * @author Dessertion
 */
public class MainMenuGUI extends GUI {
	
	public static List<Button> buttons = Collections.synchronizedList(new ArrayList<>());
	private boolean click = false;
	private SoundClip bgm;
	
	private void handleClickEvents() {
		if(MouseInput.MOUSE_DOWN) click=true;
		else if(click){
			click = false;
			checkButtons();
		}
	}
	
	public MainMenuGUI() {
		super(new Vector3f(-4,-3,0), 10, 6);
		tex = new Texture("/textures/monkeys_vs_bloons.png");
	}
	
	@Override
	public void init() {
		bgm = Sound.loopSound(Sound.TITLE);
		
		Button startButton = new Button(-2f,-2f, 2f, 1f);
		startButton.loadTexture("/textures/game_start_button.png");
		startButton.addButtonListener(()->{
					try {
						bgm.stop();
						StateManager.setState(1);
					} catch (InvalidStateException e) {
						e.printStackTrace();
					}
				}
			);
		buttons.add(startButton);
	}
	
	@Override
	public void update() {
		handleClickEvents();
	}
	
	@Override
	public void render() {
		tex.bind();
		menuShader.setUniformMat4f("view_mat",new Matrix4f().translate(position));
		menuShader.enable();
		mesh.render();
		menuShader.disable();
		tex.unbind();
		renderComponents();
	}
	
	@Override
	public void renderComponents() {
		buttons.forEach(Button::render);
	}
	
	private boolean checkButtons(){
		for(Button b : buttons){
			if(b.checkClick()){
				b.performAction();
				return true;
			}
		}
		return false;
	}
}

