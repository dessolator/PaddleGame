package dev.game.project.boundaries;

import dev.game.project.gameMechanics.PaddleGame;
import dev.game.project.gameObjects.Ball;
import dev.game.project.gameObjects.GameObject;

public class LowerBoundary extends Boundary{

	public LowerBoundary(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void collided(GameObject o) {
		PaddleGame.getLevel().getMyBalls().remove((Ball)o);
		Ball.reset();//reset the ball.
		
	}

}
