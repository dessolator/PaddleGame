package dev.game.project.engine;
import dev.game.project.gameMechanics.PaddleGame;

public class Main {

	
	/*
	 * TODO SQUISH BUG REAPPEARED WHEN IMPLEMENTING MULTIBALL
	 * TODO COMMENTS...AGAIN
	 * TODO IMPLEMENT CALLS TO GAME MENU
	 * TODO MAKE BUTTONS CLICKABLE
	 * TODO MAKE LEVEL SELECTION MENU
	 * TODO MAKE SCORE VIEW SCREEN
	 * TODO MAKE SETTINGS SCREEN
	 * TODO SOME FILE IO FOR SAVE PROGRESS
	 * TODO IMPROVE TEXTURE EFFICIENCY,TEXTURE LOADER GETTING CALLED WAY TOO OFTEN
	 * TODO LEVEL GENERATION AND PROGRESSION
	 * TODO SCORING
	 * TODO SOUND-Kraftwerk Popcorn?
	 */
	
	
	
	
	
	public static void main(String[] args) {
		Engine.init();//start the engine.
		PaddleGame.startGame(false);//start the game with voodooMode off.
		Engine.kill();//kill the engine.
	}
	
	
	
	
	
	
	

	

	

}
