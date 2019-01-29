package com.dessertion.icssummative.engine.sound;

/**
 * @author Dessertion
 */
public enum Sound {
	
	BGM("/snd/bgm.wav"),
	EXPLOSION("/snd/explosion.wav"),
	DINK("/snd/dink.wav"),
	POP("/snd/pop.wav"),
	TITLE("/snd/title.wav");
	
	private String path;
	Sound(String path){
		this.path=path;
	}
	
	public static SoundClip playSound(Sound sound){
		SoundClip p = new SoundClip(sound.path);
		p.play();
		return p;
	}
	
	public static SoundClip loopSound(Sound sound){
		SoundClip p = new SoundClip(sound.path);
		p.loop();
		return p;
	}
}
