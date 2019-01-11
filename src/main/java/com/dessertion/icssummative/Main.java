package com.dessertion.icssummative;

import com.dessertion.icssummative.engine.GameEngine;

public class Main {

	public static void main(String[] args) {
		boolean vSync = true;
		try {
			GameEngine engine = new GameEngine("Bloons",800,600,vSync);
			engine.start();
		} catch (Exception e) {
			//if error encountered, exit with error
			e.printStackTrace();
			System.exit(-1);
		}


	}
	
}