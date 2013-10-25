package dev.game.project.boundaries;

import dev.game.project.gameObjects.Ball;
import dev.game.project.gameObjects.GameObject;

public class RightBoundary extends Boundary {

	public RightBoundary(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void collided(GameObject o) {
		((Ball)o).setSpeedX(((Ball)o).getSpeedX() * -1);//bounce the ball off.
		
	}

}
