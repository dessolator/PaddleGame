package dev.game.project.menus.buttons;

import dev.game.project.gameMechanics.PaddleGame;




public class ExitGameButton extends Button {

	public ExitGameButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY,"Exit");		
	}

	@Override
	public void pressed() {
		PaddleGame.setTerminate(true);
	}

}
