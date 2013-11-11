package dev.game.project.menus.buttons;

import dev.game.project.gameMechanics.PaddleGame;



public class ResumeGameButton extends Button {

	public ResumeGameButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY,"Resume Game");
	}

	@Override
	public void pressed() {
		PaddleGame.setCurrentGameState(1);

	}

}
