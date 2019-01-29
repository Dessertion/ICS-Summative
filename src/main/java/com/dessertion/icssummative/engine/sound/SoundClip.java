package com.dessertion.icssummative.engine.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Dessertion
 */
public class SoundClip{
	private Clip clip;
	public SoundClip(String path){
		loadClip(path);
	}
	
	private void loadClip(String path) {
		
		try {
			URL                  url     = getClass().getResource(path);
				AudioInputStream sstream = AudioSystem.getAudioInputStream(url);
				clip = AudioSystem.getClip();
				clip.open(sstream);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		
	}
	
	public void play() {
		Runnable run = ()->{clip.setFramePosition(0);clip.start();};
		run.run();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
	
}

