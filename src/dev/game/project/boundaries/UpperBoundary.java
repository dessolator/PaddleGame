package dev.game.project.boundaries;

import dev.game.project.engine.GameObject;
import dev.game.project.gameObjects.Ball;

public class UpperBoundary extends Boundary{

	public UpperBoundary(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY);
	}

	@Override
	public void collided(GameObject o) {
		((Ball)o).setDirection(((Ball)o).getDirection() * -1);// bounce the ball back down.
		
	}

}
