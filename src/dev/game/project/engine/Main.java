package dev.game.project.engine;
import dev.game.project.gameMechanics.PaddleGame;

public class Main {

	
	/*
	 * TODO TEXTURES
	 * TODO METHINKS BALL CAN COLLIDE WITH BONUS...INVESTIGATE
	 * TODO SQUISH BUG REAPPEARED WHEN IMPLEMENTING MULTIBALL
	 * TODO COMMENT BONUS
	 * TODO GAME MENU
	 * TODO LEVEL GENERATION AND PROGRESSION
	 * TODO SCORING
	 */
	
	
	
	
	
	public static void main(String[] args) {
		Engine.init();//start the engine.
		PaddleGame.startGame(false);//start the game with voodooMode off.
		Engine.kill();//kill the engine.
	}
	
	
	
	
	
	
	

	

	

}
