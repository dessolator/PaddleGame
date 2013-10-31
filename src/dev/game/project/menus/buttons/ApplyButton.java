package dev.game.project.menus.buttons;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import dev.game.project.gameMechanics.PaddleGame;
import dev.game.project.menus.elements.ResEntry;


public class ApplyButton extends Button {

	public ApplyButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pressed() {
		try {
			FileWriter fstreamWrite;
			fstreamWrite = new FileWriter("Settings.ini");
			BufferedWriter out = new BufferedWriter(fstreamWrite);
			out.write("Resolution= "+ResEntry.getSetRes().getWidth()+" x "+ResEntry.getSetRes().getHeight()+" ;\n");
			//out.write("Color = "+ColorsBox.value+";\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PaddleGame.setCurrentGameState(0);

	}

}
