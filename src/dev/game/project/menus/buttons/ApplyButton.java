package dev.game.project.menus.buttons;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.newdawn.slick.opengl.TextureLoader;

import dev.game.project.gameMechanics.PaddleGame;
import dev.game.project.menus.elements.ColorsBox;
import dev.game.project.menus.elements.ResEntry;


public class ApplyButton extends Button {

	public ApplyButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY);
		try {
			myTexture=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/applyButton.png")));
			pressedTexture=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/applyButton.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void pressed() {
		try {
			FileWriter fstreamWrite;
			fstreamWrite = new FileWriter("Settings.ini");
			BufferedWriter out = new BufferedWriter(fstreamWrite);
			out.write("Resolution= "+ResEntry.getSetRes().getWidth()+" x "+ResEntry.getSetRes().getHeight()+" ;\n");
			out.write("Color= "+ColorsBox.isColorChecked()+" ;\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PaddleGame.updateInGameSettings();
		PaddleGame.setCurrentGameState(0);

	}

}
