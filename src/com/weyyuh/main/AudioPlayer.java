package com.weyyuh.main;
//import org.newdawn.slick.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
//import org.newdawn.slick.*;
//import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.Sound;

public class AudioPlayer {

	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load() {
		
		try {
			Sound s =new Sound("res/click.ogg");
			soundMap.put("menu_sound", s);
//			File f = new File("res/click.ogg");
//			if (f.exists()) {
//				System.out.print("Exist");
//			}
			
			
			musicMap.put("music", new Music("res/background.ogg"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
}
