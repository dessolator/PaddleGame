package dev.game.project;

import static org.lwjgl.opengl.GL11.glColor3f;

public class Bonus extends Collidable implements Movable{
	private BonusType myType;
	private static final int MAX_BONUSES_PER_LEVEL=6;
	private static int bonusesDropped=0;
	
	
	public Bonus(BonusType bt, float coordX, float coordY) {
		this.setCoordX(coordX);
		this.setCoordY(coordY);
		myType=bt;
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
		DrawObject.drawRect(getCoordX(), getCoordY(), 10, 10);
				
	}

}
