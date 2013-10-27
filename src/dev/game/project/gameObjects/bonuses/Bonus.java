package dev.game.project.gameObjects.bonuses;

import org.lwjgl.opengl.Display;
import dev.game.project.engine.Collidable;
import dev.game.project.engine.DrawObject;
import dev.game.project.engine.Movable;

import dev.game.project.gameMechanics.PaddleGame;

import dev.game.project.gameObjects.Brick;
import dev.game.project.gameObjects.GameObject;


public abstract class Bonus extends GameObject implements Movable,Collidable{
	private static final int MAX_BONUSES_PER_LEVEL=6;
	private static int bonusesDropped=0;
	
	
	public Bonus(float coordX, float coordY) {
		this.setCoordX(coordX);
		this.setCoordY(coordY);
		this.setDimX(Display.getHeight()/30);
		this.setDimY(Display.getHeight()/30);
	}

	public static void drop(Brick b) {
		if(bonusesDropped<MAX_BONUSES_PER_LEVEL){
			double factor=Math.random();
			if(factor>0.86)
				PaddleGame.getLevel().addBonus(new BallDamage(b.getCoordX(),b.getCoordY()));
			else if(factor>0.72)
				PaddleGame.getLevel().addBonus(new BallSpeed(b.getCoordX(),b.getCoordY()));
			else if(factor>0.58)
				PaddleGame.getLevel().addBonus(new MultiBall(b.getCoordX(),b.getCoordY()));
			else if(factor>0.44)
				PaddleGame.getLevel().addBonus(new PaddleWiden(b.getCoordX(),b.getCoordY()));
			else if(factor>0.30)
				PaddleGame.getLevel().addBonus(new PaddleNarrow(b.getCoordX(),b.getCoordY()));
			else if(factor>0.16)
				PaddleGame.getLevel().addBonus(new PaddleInvert(b.getCoordX(),b.getCoordY()));
			else if(factor>0)
				PaddleGame.getLevel().addBonus(new PaddleSpeed(b.getCoordX(),b.getCoordY()));			
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
	public void update() {
		move(0);
		collided(PaddleGame.getLevel().getPaddle());
		
	}

	@Override
	public void render() {
//		glColor3f(1f, 0f, 1f);
//		DrawObject.drawColoredRect(getCoordX(), getCoordY(), getDimX(), getDimY());
		DrawObject.draw(this);
				
	}
	public abstract void undo();

}
