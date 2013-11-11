package dev.game.project.menus.buttons;

import dev.game.project.gameMechanics.PaddleGame;



public class MainMenuButton extends Button {

	public MainMenuButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY, "Main Menu");
	}

	@Override
	public void pressed() {
		PaddleGame.setCurrentGameState(0);
	}

}
