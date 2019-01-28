package com.dessertion.icssummative.game.util;

import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * @author Dessertion
 */
public class FloatRect {
	public float x,y, width, height;
	
	/**
	 * Default constructor for float rectangle
	 * @param x x coordinate of bottom-left corner
	 * @param y y coordinate of bottom-left corner
	 * @param width width of rectangle
	 * @param height height of rectangle
	 */
	public FloatRect(float x, float y, float width, float height){
		this.x=x;
		this.y=y;
		this.width =width;
		this.height =height;
	}
	
	/**
	 * Creates a float rectangle based on another float rectangle
	 * @param r the to-be-copied float rectangle
	 */
	public FloatRect(FloatRect r){
		this.x=r.x;
		this.y=r.y;
		this.width =r.width;
		this.height =r.height;
	}
	
	
	/**
	 * Checks if a pair of coordinates (x,y) is located within this rectangle
	 * @param X The specified x coordinate
	 * @param Y The specified y coordinate
	 * @return True if (x,y) is within the rectangle, false otherwise
	 */
	public boolean contains(float X, float Y){
		//check if either dimension is negative
		if(width <0|| height <0)return false;
		
		//check if less than either bound
		if(X<x||Y<y)return false;
		
		//return if within bounds
		return (X<x+width&&Y<y+height);
	}
	
	/**
	 * Checks if a specified point is located within this rectangle
	 * @param p The specified point
	 * @return True if the point lies within the rectangle, false otherwise
	 */
	public boolean contains(FloatPoint p){
		return contains(p.x,p.y);
	}
	
	/**
	 * Checks if this rectangle intersects with another specified rectangle
	 * @param X x coordinate of bottom-left corner of the specified rectangle
	 * @param Y y coordinate of bottom-left corner of the specified rectangle
	 * @param W width of the rectangle
	 * @param H height of the rectangle
	 * @return True if they intersect, false otherwise
	 */
	public boolean intersects(float X, float Y, float W, float H){
		if(W < 0 || H < 0 || width < 0 || height < 0)return false;
		return (X+W >= x && Y-H <= y && x+width >= X && y-height<=Y);
	}
	
	/**
	 * Checks if this rectangle intersects with another specified rectangle
	 * @param r the specified rectangle
	 * @return True if they intersect, false otherwise
	 */
	public boolean intersects(FloatRect r){
		return intersects(r.x,r.y,r.width,r.height);
	}
	
	public FloatRect translate(float dx, float dy){
		this.x+=dx;
		this.y+=dy;
		return this;
	}
	
	public FloatRect translate(Vector3f vec){
		return translate(vec.x,vec.y);
	}
}

