package com.dessertion.icssummative.engine;

public final class Timer {
	
	private double lastCallTime;
	private double lastSetTime;
	
	/**
	 * For first time init
	 */
	public void init() {
		lastSetTime = lastCallTime = getTime();
	}
	
	/**
	 * Get elapsed time since last call in seconds
	 *
	 * @return Time elapsed in seconds
	 */
	public float getElapsedTime() {
		double current = getTime();
		float  elapsed = (float) (current - lastCallTime);
		lastCallTime = current;
		return elapsed;
	}
	
	/**
	 * Return system time in seconds
	 *
	 * @return Time in seconds
	 */
	public double getTime() {
		return System.nanoTime() / 1e9;
	}
	
	/**
	 * Gets the last call time of <code>getElapsedTime()</code>
	 *
	 * @return Time in seconds;
	 */
	public double getLastCallTime() {
		return lastCallTime;
	}
	
	public double getElapsedSinceSetTime(){
		return getTime() - lastSetTime;
	}
	
	public void setTime(){
		lastSetTime=getTime();
	}
	
}
