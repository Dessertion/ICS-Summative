package com.dessertion.icssummative.game.gui;

import com.dessertion.icssummative.game.Level;
import com.dessertion.icssummative.game.MouseInput;
import com.dessertion.icssummative.game.entities.TexturedImage;
import com.dessertion.icssummative.game.entities.towers.TowerType;

/**
 * @author Dessertion
 */
public class TowerButton extends Button{
	
	public TowerType type;
	
	public TowerButton(float x, float y, float w, float h, TowerType type) {
		super(x, y, w, h);
		this.type=type;
		addButtonListener(() -> {buyThing(type);});
	}
	
	@Override
	public void update(){
		if(Level.money<type.cost)grey=1;
		else grey=0;
	}
	
	private void buyThing(TowerType type){
		if(Level.money>=type.cost){
			BuyMenu.buyingTower=type;
			Level.money-=type.cost;
			TexturedImage image =
					new TexturedImage((float) MouseInput.X, (float) MouseInput.Y, type.size, type.size, -0.7f, type.path) {
						@Override
						public void update(float interp) {
							position.x = (float) MouseInput.X;
							position.y = (float) MouseInput.Y;
							if(BuyMenu.buyingTower==null)kill();
						}
					};
		}
	}
}

