package com.dessertion.icssummative.game.util;

import java.util.ArrayList;

/**
 * @author Dessertion
 */
public class TrackAvoid {
	
	public static ArrayList<FloatRect> track = new ArrayList<>();
	
	public static void init(){
		track.add(new FloatRect(-4,0.13f,-1.75f+4f,0.58f-0.13f));
		track.add(new FloatRect(-2.3f,-2.9f,-1.75f+2.3f,0.6f+2.9f));
		track.add(new FloatRect(-2.3f,-2.9f,0.8f+2.3f,-2.4f+2.9f));
		track.add(new FloatRect(0.24f,-2.9f,0.8f-0.24f,1.9f+2.9f));
		track.add(new FloatRect(-3.95f,1.4f,0.79f+3.95f,1.9f-1.4f));
		track.add(new FloatRect(-3.95f,1.4f,-3.35f+3.95f,2.87f-1.4f));
		track.add(new FloatRect(-3.95f,2.35f,2.2f+3.95f,2.85f-2.35f));
		track.add(new FloatRect(1.71f,1.35f,2.2f-1.71f,2.85f-1.35f));
		track.add(new FloatRect(1.71f,1.35f,3.45f-1.71f,1.75f-1.35f));
		track.add(new FloatRect(3f,0.14f,3.45f-3f,1.75f-0.14f));
		track.add(new FloatRect(1.75f,0.14f,3.5f-1.75f,0.65f-0.14f));
		track.add(new FloatRect(1.75f,-2.7f,2.23f-1.75f,0.65f+2.7f));
		track.add(new FloatRect(1.75f,-2.7f,3.8f-1.75f,-2.21f+2.7f));
		track.add(new FloatRect(3.25f,-2.7f,3.8f-3.25f,-0.83f+2.7f));
		track.add(new FloatRect(-3.72f,-1.32f,3.8f+3.72f,-0.83f+1.32f));
		track.add(new FloatRect(-3.72f,-3f,-3.2f+3.72f,-0.83f+3f));
	}
	
}

