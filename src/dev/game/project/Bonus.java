package dev.game.project;

import static org.lwjgl.opengl.GL11.glColor3f;

public class Bonus extends Collidable implements Movable{
	private BonusType myType;
	private static final int MAX_BONUSES_PER_LEVEL=6;
	private static int bonusesDropped=0;
	
	
	public Bonus(BonusType bt, float coordX, float coordY) {
		this.coordX=coordX;
		this.coordY=coordY;
		myType=bt;
	}

	public static void drop(Brick b) {
		if(bonusesDropped<MAX_BONUSES_PER_LEVEL){
			PaddleGame.getLevel().addBonus(new Bonus(BonusType.random(),b.coordX,b.coordY));
			bonusesDropped++;
		}
		
	}

	@Override
	public void move(int i) {
		coordY-=4;
		if(coordY<0){
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
					System.out.println("picked up widen");
					break;
				case BALL_SPEED:
					PaddleGame.getLevel().getBall().speedUp();
					System.out.println("picked up ball speed");
					break;
				case PADDLE_INVERT:
					PaddleGame.getLevel().getPaddle().invert();
					System.out.println("picked up invert");
					break;
				case PADDLE_SPEED:
					PaddleGame.getLevel().getPaddle().speedUp();
					System.out.println("picked up paddle speed");
					break;
				case PADDLE_NARROW:
					PaddleGame.getLevel().getPaddle().narrow();
					System.out.println("picked up narrow");
					break;
				case BALL_DAMAGE:
					PaddleGame.getLevel().getBall().increaseDamage();
					System.out.println("picked up ball damage");
					break;
				case MULTI_BALL:
					PaddleGame.getLevel().spawnBall();
					System.out.println("picked up multi ball");
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
		DrawObject.drawRect(coordX, coordY, 10, 10);
				
	}

}
