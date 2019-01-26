package com.dessertion.icssummative.game.entities;

import java.util.*;

/**
 * @author Dessertion
 */
public class BloonWave {
	private Queue<BloonInfo> wave = new LinkedList<>();
	private int waveNum;
	
	public BloonWave(int waveNum, String in) {
		this.waveNum = waveNum;
		parseInput(in);
	}
	
	public class BloonInfo{
		public Bloon.BloonType bloonType;
		public int             num;
		public double          delay = 0.25;
		public BloonInfo(Bloon.BloonType bloonType, int num){
			this.bloonType =bloonType;
			this.num=num;
		}
		public BloonInfo(){}
	}
	
	private void parseInput(String in) {
		StringTokenizer st = new StringTokenizer(in, " ");
		while (st.hasMoreTokens()) {
			String    temp    = st.nextToken();
			char      c       = temp.charAt(0);
			BloonInfo current = new BloonInfo();
			
			//set delay
			if (temp.substring(1).contains("d")) {
				int idx = temp.indexOf('d');
				current.delay = Double.parseDouble(temp.substring(idx + 1));
				current.num = Integer.parseInt(temp.substring(1, idx));
			} else current.num = Integer.parseInt(temp.substring(1));
			
			switch(c){
				case 'r': current.bloonType = Bloon.BloonType.RED; break;
				case 'b': current.bloonType = Bloon.BloonType.BLUE; break;
				case 'g': current.bloonType = Bloon.BloonType.GREEN; break;
				case 'y': current.bloonType = Bloon.BloonType.YELLOW; break;
				case 'p': current.bloonType = Bloon.BloonType.PINK; break;
				case 'n': current.bloonType = Bloon.BloonType.RAINBOW; break;
				case 't': current.bloonType = Bloon.BloonType.TEST; break;
				case 'l': current.bloonType = Bloon.BloonType.LEAD; break;
				case 'm': System.out.println("IMPLEMENT MOAB HAHA"); break;
				default: current.bloonType = Bloon.BloonType.TEST; break;
			}
			
			wave.add(current);
			
		}
	}
	
	public Queue<BloonInfo> getWave() {
		return wave;
	}
	
	public boolean done(){
		return wave.isEmpty();
	}
	
	public BloonInfo getNext(){
		return wave.isEmpty()? null: wave.poll();
	}
	
	public int getWaveNum() {
		return waveNum;
	}
	
}

