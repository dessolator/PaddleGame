package dev.game.project.gameObjects.bonuses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.TextureLoader;

import dev.game.project.engine.GamePhysics;
import dev.game.project.gameMechanics.PaddleGame;
import dev.game.project.gameObjects.GameObject;

public class PaddleNarrow extends Bonus {

	public PaddleNarrow(float coordX, float coordY) {
		super(coordX, coordY);
		try {
			setTexture(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/paddleNarrowBonus.png"))));
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
			PaddleGame.getLevel().getPaddle().narrow();
			Timer.reset(this);
		}
	}
	@Override
	public void undo() {
		PaddleGame.getLevel().getPaddle().setDimX(PaddleGame.getLevel().getPaddle().getDimX() * 1.5f);
		PaddleGame.getLevel().getPaddle().setNarrowed(false);
		
	}

}
