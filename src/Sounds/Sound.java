package Sounds;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Sound {
	private FileInputStream myStream;
	private AdvancedPlayer mp3;
	public Sound(String Path){
		try {
			myStream=new FileInputStream(Path);
			mp3=new AdvancedPlayer(myStream);
			} catch (Exception e) {e.printStackTrace();}
	}
	
	public void play(){
		new Instance(mp3);
	}
		
	
			
}
