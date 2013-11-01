package dev.game.project.engine;
import dev.game.project.gameMechanics.PaddleGame;

public class Main {

	
	/*
	 * TODO SQUISH BUG REAPPEARED WHEN IMPLEMENTING MULTIBALL
	 * TODO SOME MISSING TEXTURES (APPLY BUTTON,SETTINGS FRAME, CANCEL BUTTON)
	 * TODO COMMENTS...AGAIN
	 * TODO ANIMATE BUTTONS AS CLICKABLE
	 * TODO MAKE LEVEL SELECTION MENU
	 * TODO MAKE SCORE VIEW SCREEN
	 * TODO SOME FILE IO FOR SAVE PROGRESS
	 * TODO FIX SETTINGS.INI READ LOGIC
	 * TODO SOMETHING
	 * TODO IMPROVE TEXTURE EFFICIENCY,TEXTURE LOADER GETTING CALLED WAY TOO OFTEN
	 * TODO LEVEL GENERATION AND PROGRESSION
	 * TODO SCORING
	 * TODO SORT RESOLUTIONS
	 * TODO SOUND-Kraftwerk Popcorn?
	 * TODO MERGE TEXTURES INTO SINGLE LARGE FILE
	 */
	
	
	
	
	
	public static void main(String[] args) {
		Engine.init();//start the engine.
		PaddleGame.startGame(false);//start the game with voodooMode off.
		Engine.kill();//kill the engine.
	}
	
	
	
	
	
	
	

	

	

}
