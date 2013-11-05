package dev.game.project.menus.buttons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.TextureLoader;

import Sounds.Sound;

import dev.game.project.gameMechanics.PaddleGame;


public class NewGameButton extends Button {


	public NewGameButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY);
		try {
			myTexture=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/newGameButton.png")));
			pressedTexture=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/newGameButton.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void pressed() {
		PaddleGame.setCurrentGameState(1);
		new Thread(){
			public void run()
			{
				Sound s=new Sound("/res/Trololo.mp3");
				s.play();
		//		try{
		//		sleep(10000);
		//		}catch(Exception e){}
		//		s.stop();
			}
		}.start();
		
	}

}
