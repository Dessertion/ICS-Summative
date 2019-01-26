package com.dessertion.icssummative.game.util;

import com.dessertion.icssummative.game.entities.Bloon;

import java.io.*;
import java.util.*;

/**
 * @author Dessertion
 */
public final class BloonFactory {

	private static Queue<BloonWave> waves = new LinkedList<>();
	
	public static void init(){
		File file = new File(BloonFactory.class.getResource("/data/waves.dat").getFile());
		int waveNum = 1;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for(String in=br.readLine(); in!=null; in=br.readLine()){
				BloonWave temp = new BloonWave(waveNum++, in);
				waves.add(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BloonWave getNextWave(){
		if(!waves.isEmpty())return waves.poll();
		else return null;
	}
	
	public static boolean done(){
		return waves.isEmpty();
	}
	
	public static Bloon createBloon(Bloon.BloonType type){
		Bloon ret = new Bloon(type);
		Bloon.bloons.add(ret);
		return ret;
	}

}

