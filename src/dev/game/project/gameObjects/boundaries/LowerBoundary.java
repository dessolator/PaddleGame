package dev.game.project.gameObjects.boundaries;

import dev.game.project.gameMechanics.PaddleGame;
import dev.game.project.gameObjects.Ball;
import dev.game.project.gameObjects.GameObject;

public class LowerBoundary extends Boundary{

	public LowerBoundary(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY);
		}

	@Override
	public void collided(GameObject o) {
		PaddleGame.getLevel().getBalls().remove((Ball)o);
		Ball.reset();//reset the ball.
		
	}

}
