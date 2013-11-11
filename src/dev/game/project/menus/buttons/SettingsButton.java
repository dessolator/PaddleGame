package dev.game.project.menus.buttons;

import dev.game.project.gameMechanics.PaddleGame;


public class SettingsButton extends Button {


	public SettingsButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY,"Settings");		
	}
	@Override
	public void pressed() {
		PaddleGame.setCurrentGameState(4);
		
	}

}
