package dev.game.project.menus.buttons;

import dev.game.project.gameMechanics.PaddleGame;


public class CancelButton extends Button {

	public CancelButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pressed() {
		PaddleGame.setCurrentGameState(0);

	}

}
