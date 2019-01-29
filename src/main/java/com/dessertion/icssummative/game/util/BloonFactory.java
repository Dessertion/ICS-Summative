package com.dessertion.icssummative.game.util;

import com.dessertion.icssummative.engine.Timer;
import com.dessertion.icssummative.game.Level;
import com.dessertion.icssummative.game.entities.Bloon;
import com.dessertion.icssummative.game.state.MainGameState;

import java.io.*;
import java.util.*;

/**
 * @author Dessertion
 */
public final class BloonFactory {

	private static Queue<BloonWave> waves;
	private static BloonWave currentWave;
	private static BloonWave.BloonInfo currentInfo;
	private static Timer timer;
	
	public static int waveNum;
	public static boolean startWave;
	
	public static void init(){
		waves = new LinkedList<>();
		Scanner sc = new Scanner(BloonFactory.class.getResourceAsStream("/data/waves.dat"));
		int waveNum = 1;
		while(sc.hasNext()){
			BloonWave temp = new BloonWave(waveNum++,sc.nextLine());
			waves.add(temp);
		}
		timer = new Timer();
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
	
	public static void beginWaveSpawning(){
		if(!startWave&&Bloon.bloons.isEmpty()) {
			if (BloonFactory.done()) {
				Level.gameWin = true;
				return;
			}
			startWave = true;
			currentWave = BloonFactory.getNextWave();
			waveNum = currentWave.getWaveNum();
			currentInfo = currentWave.getNext();
			
			timer.setTime();
		}
	}
	
	
	private static int numSpawned = 0;
	public static void spawnWave() {
		if(currentInfo.num<=numSpawned){
			currentInfo = currentWave.getNext();
			timer.setTime();
			numSpawned=0;
			if(currentInfo==null){
				startWave=false;
				return;
			}
		}
		if(timer.getElapsedSinceSetTime()>=currentInfo.delay){
			timer.setTime();
			BloonFactory.createBloon(currentInfo.bloonType);
			numSpawned++;
		}
	}
	
	public static void release(){
		waves.clear();
		
	}

}

