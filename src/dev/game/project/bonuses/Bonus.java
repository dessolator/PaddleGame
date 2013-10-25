package dev.game.project.bonuses;

import static org.lwjgl.opengl.GL11.glColor3f;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.TextureLoader;

import dev.game.project.gameMechanics.DrawObject;
import dev.game.project.gameMechanics.GamePhysics;
import dev.game.project.gameMechanics.PaddleGame;
import dev.game.project.gameObjects.Ball;
import dev.game.project.gameObjects.Brick;
import dev.game.project.gameObjects.Collidable;
import dev.game.project.gameObjects.GameObject;
import dev.game.project.gameObjects.Movable;

public class Bonus extends Collidable implements Movable{
	private BonusType myType;
	private static final int MAX_BONUSES_PER_LEVEL=6;
	private static int bonusesDropped=0;
	
	
	public Bonus(BonusType bt, float coordX, float coordY) {
		this.setCoordX(coordX);
		this.setCoordY(coordY);
		this.setDimX(Display.getHeight()/30);
		this.setDimY(Display.getHeight()/30);
		myType=bt;
		try {
			switch(bt){
				case PADDLE_NARROW: 
					setTexture(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/paddleNarrowBonus.png"))));
					break;
				case BALL_DAMAGE:
					setTexture(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/ballDamageBonus.png"))));
					break;
				case BALL_SPEED:
					setTexture(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/ballSpeedBonus.png"))));
					break;
				case MULTI_BALL:
					setTexture(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/multiBallBonus.png"))));
					break;
				case PADDLE_INVERT:
					setTexture(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/paddleInvertBonus.png"))));
					break;
				case PADDLE_SPEED:
					setTexture(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/paddleSpeedBonus.png"))));
					break;
				case PADDLE_WIDEN:
					setTexture(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/paddleWidenBonus.png"))));
					break;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void drop(Brick b) {
		if(bonusesDropped<MAX_BONUSES_PER_LEVEL){
			PaddleGame.getLevel().addBonus(new Bonus(BonusType.random(),b.getCoordX(),b.getCoordY()));
			bonusesDropped++;
		}
		
	}

	@Override
	public void move(int i) {
		setCoordY(getCoordY() - 4);
		if(getCoordY()<0){
			PaddleGame.getLevel().removeBonus(this);
		}
		
	}

	@Override
	public void collided(GameObject o) {
		if(GamePhysics.hit(this, o)){
			PaddleGame.getLevel().removeBonus(this);
			switch (myType){
				case PADDLE_WIDEN:
					PaddleGame.getLevel().getPaddle().widen();
					break;
				case BALL_SPEED:
					Ball.speedUp();
					break;
				case PADDLE_INVERT:
					PaddleGame.getLevel().getPaddle().invert();
					break;
				case PADDLE_SPEED:
					PaddleGame.getLevel().getPaddle().speedUp();
					break;
				case PADDLE_NARROW:
					PaddleGame.getLevel().getPaddle().narrow();
					break;
				case BALL_DAMAGE:
					Ball.increaseDamage();
					break;
				case MULTI_BALL:
					PaddleGame.getLevel().spawnBall();
					break;
				default:
					break;
									
			}
		}
		
	}

	@Override
	public void update() {
		move(0);
		collided(PaddleGame.getLevel().getPaddle());
		
	}

	@Override
	public void render() {
		glColor3f(1f, 0f, 1f);
		DrawObject.drawColoredRect(getCoordX(), getCoordY(), getDimX(), getDimY());
				
	}

}
