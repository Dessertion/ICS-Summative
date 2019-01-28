package com.dessertion.icssummative.engine.sound;

/**
 * @author Dessertion
 */
public enum Sound {
	
	BGM("/snd/bgm.wav"),
	POP("/snd/pop.wav");
	
	private String path;
	Sound(String path){
		this.path=path;
	}
	
	public static void playSound(Sound sound){
		SoundClip p = new SoundClip(sound.path);
		p.play();
	}
	
	public static void loopSound(Sound sound){
		SoundClip p = new SoundClip(sound.path);
		p.loop();
	}
}
