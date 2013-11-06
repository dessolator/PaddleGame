package dev.game.project.menus.buttons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.TextureLoader;

import dev.game.project.gameMechanics.PaddleGame;


public class CancelButton extends Button {

	public CancelButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY);
		try {
			myTexture=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/cancelButton.png")));
			pressedTexture=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/cancelButton.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void pressed() {
		PaddleGame.setCurrentGameState(0);

	}

}
