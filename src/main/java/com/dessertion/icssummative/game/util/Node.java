package com.dessertion.icssummative.game.util;

import org.joml.Vector3f;

import java.io.*;
import java.util.*;

/**
 * @author Dessertion
 */
public class Node {
	
	public static List<Node> nodes = Collections.synchronizedList(new ArrayList<>());
	public static Node BEGIN;
	public static Node END;
	
	private final float x,y;
	
	private final Vector3f v;
	
	private Node(float x, float y){
		this.x=x;
		this.y=y;
		v =new Vector3f(x,y,0);
		nodes.add(this);
	}
	
	public static void init(){
		Scanner sc = new Scanner(Node.class.getResourceAsStream("/data/nodes.dat"));
		while(sc.hasNext()){
			new Node(sc.nextFloat(),sc.nextFloat());
		}
		System.out.println("NumImage of initialized nodes: " + nodes.size());
		BEGIN = nodes.get(0);
		END = nodes.get(nodes.size()-1);
	}
	
	public static void releaseAll(){
		nodes.clear();
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public Vector3f getV() {
		return v;
	}
	
	
}

