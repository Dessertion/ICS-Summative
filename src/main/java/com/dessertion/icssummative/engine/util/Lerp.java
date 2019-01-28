package com.dessertion.icssummative.engine.util;

/**
 * @author Dessertion
 */
public final class Lerp {
	
	public static float lerp(float v0, float v1, double interp){
		return (float)((v1-v0)/interp);
	}
	
	public static double lerp(double v0, double v1, double interp){
		return (v1-v0)/interp;
	}
	
}

