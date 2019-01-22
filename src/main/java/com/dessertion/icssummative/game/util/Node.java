package com.dessertion.icssummative.game.util;

import java.io.*;
import java.util.*;

/**
 * @author Dessertion
 */
public class Node {
	
	public static List<Node> nodes = Collections.synchronizedList(new ArrayList<>());
	
	private float x,y;
	
	private Node(float x, float y){
		this.x=x;
		this.y=y;
		nodes.add(this);
	}
	
	public static void init(){
		Scanner sc = new Scanner(Node.class.getResourceAsStream("/data/nodes.dat"));
		while(sc.hasNext()){
			new Node(sc.nextFloat(),sc.nextFloat());
		}
		System.out.println(nodes.size());
	}
	
	private void release(){
		nodes.remove(this);
	}
	
	public static void releaseAll(){
		nodes.clear();
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

