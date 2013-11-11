package dev.game.project.menus.buttons;

import dev.game.project.gameMechanics.PaddleGame;





public class NewGameButton extends Button {

	
	public NewGameButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY,"New Game");
	}

	@Override
	public void pressed() {
		PaddleGame.setCurrentGameState(1);
	}

}
