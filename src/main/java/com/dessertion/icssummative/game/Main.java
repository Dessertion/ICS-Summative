package com.dessertion.icssummative.game;

import com.dessertion.icssummative.engine.Engine;

public class Main {

	public static void main(String[] args) {
		boolean vSync = false;
		try {
			Engine engine = new Engine("Bloons",1000,600,vSync);
			engine.start();
		} catch (Exception e) {
			//if error encountered, exit with error
			e.printStackTrace();
			System.exit(-1);
		}


	}
	
}