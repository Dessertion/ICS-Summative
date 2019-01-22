package com.dessertion.icssummative.game.util;

import java.util.ArrayList;

/**
 * @author Dessertion
 */
public class Node {
	
	public static ArrayList<Node> nodes = new ArrayList<>();
	
	private float x,y;
	
	public Node(float x, float y){
		this.x=x;
		this.y=y;
		nodes.add(this);
	}
	
	public void release(){
		nodes.remove(this);
	}
	
	public static void releaseAll(){
		nodes.forEach(Node::release);
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	
	
}

