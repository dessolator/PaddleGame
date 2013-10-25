package dev.game.project.gameObjects.bonuses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.TextureLoader;

import dev.game.project.engine.GameObject;
import dev.game.project.engine.GamePhysics;
import dev.game.project.gameMechanics.PaddleGame;

public class MultiBall extends Bonus {

	public MultiBall(float coordX, float coordY) {
		super(coordX, coordY);
		try {
			setTexture(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/multiBallBonus.png"))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void collided(GameObject o) {
		if(GamePhysics.hit(this, o)){
			PaddleGame.getLevel().removeBonus(this);
			PaddleGame.getLevel().spawnBall();
		}
	}
	@Override
	public void undo() {}

}
