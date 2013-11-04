package Sounds;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackListener;


public class Instance extends Thread{
	private AdvancedPlayer mySound;
	public Instance(AdvancedPlayer Sound){
		mySound=Sound;
		}
	
	
	public boolean play(){start();return true;}
	
	public void Stop(){mySound.stop();}
	
	
	
	public void run(){
		try {
			mySound.play();
		} catch (JavaLayerException e)
		  {e.printStackTrace();}

		if (mySound!=null){
			mySound.close();
			mySound=null;
			
		}
		
	}



	
	
}
