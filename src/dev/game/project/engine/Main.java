package dev.game.project.engine;
import dev.game.project.gameMechanics.PaddleGame;

public class Main {

	
	/*
	 * TODO TEXTURES
	 * TODO METHINKS BALL CAN COLLIDE WITH BONUS...INVESTIGATE
	 * TODO SQUISH BUG REAPPEARED WHEN IMPLEMENTING MULTIBALL
	 * TODO COMMENT BONUSES
	 * TODO GAME MENU
	 * TODO LEVEL GENERATION AND PROGRESSION
	 * TODO SCORING
	 * TODO SOUND-Kraftwerk Popcorn?
	 * TODO THOROUGHLY TEST BONUSES JAVA CONCURRENTMODIFICATIONEXCEPTION seeems to be fixed now....
	 * TODO MULTIBALL SEEMS TO TRIGGER OTHER RANDOM BONUSES
	 * TODO CHECK SPAWN LOCATION FOR NEW BALLS
	 */
	
	
	
	
	
	public static void main(String[] args) {
		Engine.init();//start the engine.
		PaddleGame.startGame(false);//start the game with voodooMode off.
		Engine.kill();//kill the engine.
	}
	
	
	
	
	
	
	

	

	

}
