package dev.game.project.gameObjects.boundaries;

import dev.game.project.engine.GameObject;
import dev.game.project.gameObjects.Ball;

public class RightBoundary extends Boundary {

	public RightBoundary(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY);
	}

	@Override
	public void collided(GameObject o) {
		((Ball)o).setSpeedX(((Ball)o).getSpeedX() * -1);//bounce the ball off.
		
	}

}
