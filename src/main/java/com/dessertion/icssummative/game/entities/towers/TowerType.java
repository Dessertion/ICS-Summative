package com.dessertion.icssummative.game.entities.towers;

/**
 * @author Dessertion
 */
public enum TowerType {
	DART_TOWER(150,1.5f,0.65f,"/textures/dart_monkey1.png"),
	TACK_TOWER(180,1f,0.5f,"/textures/tack_tower1.png"),
	BOMB_TOWER(500,2f,0.7f, "/textures/bomb_tower1.png"),
	SUPER_TOWER(2000,3f,0.7f, "/textures/super_tower1.png");
	
	public final int cost;
	public final float range;
	public final float size;
	public final String path;
	TowerType(int cost, float range, float size, String path){
		this.cost=cost;
		this.range=range;
		this.size=size;
		this.path=path;
	}
	
	
}

