package dev.game.project.gameObjects.bonuses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.TextureLoader;

import dev.game.project.engine.GamePhysics;
import dev.game.project.gameMechanics.PaddleGame;
import dev.game.project.gameObjects.Ball;
import dev.game.project.gameObjects.GameObject;

public class BallSpeed extends Bonus {

	public BallSpeed(float coordX, float coordY) {
		super(coordX, coordY);
		try {
			setTexture(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/ballSpeedBonus.png"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void collided(GameObject o) {
		if(GamePhysics.hit(this, o)){
			PaddleGame.getLevel().removeBonus(this);
			Ball.speedUp();
			Timer.reset(this);
		}
	}
	@Override
	public void undo() {
		if(Ball.isSpedUp()){
			Ball.setSpedUp(false);
			Ball.setSpeedY(Ball.getSpeedY()/2);
		}
		
	}

}
