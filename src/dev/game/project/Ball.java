package dev.game.project;

import static org.lwjgl.opengl.GL11.glColor3f;

import java.util.ArrayList;

public class Ball extends GameObject implements Movable {
	public static final float MAX_SPEED = 8f;//constant used to keep track of the maximum ball speed
	private float radius;//radius of the ball
	private float speedX=0.0f;//horizontal ball speed
	private static float speedY=8f;//vertical ball speed
	private boolean flipped=false;
	private int direction=1;
	private static boolean spedUp=false;
	private static int damage=1;
	private int damageThisFrame=getDamage();
	
	public Ball( float cordX, float cordY, float radius) {
		this.coordX = cordX;
		this.coordY = cordY;
		this.setRadius(radius);
		this.dimX=radius*2;
		this.dimY=radius*2;
	}
	
	
	@Override
	public void update() {
		if(flipped){
			damageThisFrame=getDamage();
			setFlipped(false);
		}
		move(0);//each frame, move the ball
	}
	/**
	 * Function used to move the ball in a preset direction.
	 */
	public void move(int i) {
		coordX+=getSpeedX();//increase the x position
		coordY+=(getSpeedY()*getDirection());//increase the y position
	}


	@Override
	public void render() {
		if(!PaddleGame.isVoodooMode()){
			glColor3f(0.25f, 0.75f, 0.5f);//set drawing color to cyan
		}
		DrawObject.drawCirclef(coordX,coordY, getRadius());//and draw it
		
	}


	public static void speedUp() {
		if(!isSpedUp()){
			setSpedUp(true);
			speedY*=1.5f;
			
		}
		Timer.reset(BonusType.BALL_SPEED);
		
	}


	public static void increaseDamage() {
		if(getDamage()==1){
			setDamage(getDamage() * 3);
		}
		Timer.reset(BonusType.BALL_DAMAGE);

		
	}


	/**
	 * @return the speedX
	 */
	public float getSpeedX() {
		return speedX;
	}


	/**
	 * @param speedX the speedX to set
	 */
	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}


	/**
	 * @return the speedY
	 */
	public static float getSpeedY() {
		return speedY;
	}


	/**
	 * @param speedY the speedY to set
	 */
	public static void setSpeedY(float speedY) {
		Ball.speedY = speedY;
	}


	/**
	 * @return the flipped
	 */
	public boolean isFlipped() {
		return flipped;
	}


	/**
	 * @param flipped the flipped to set
	 */
	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
	}


	/**
	 * @return the spedUp
	 */
	public static boolean isSpedUp() {
		return spedUp;
	}


	/**
	 * @param spedUp the spedUp to set
	 */
	public static void setSpedUp(boolean spedUp) {
		Ball.spedUp = spedUp;
	}




	public static void reset() {
		ArrayList<Ball> temp=PaddleGame.getLevel().getBalls();
		if(temp.isEmpty()){
			PaddleGame.getLevel().spawnBall();
			setDamage(1);
			temp.get(0).setDamageThisFrame(getDamage());
			temp.get(0).speedX=0f;
			temp.get(0).setDirection(1);
			speedY=8f;
			spedUp=false;
			temp.get(0).coordX=PaddleGame.getLevel().getPaddle().coordX;
			temp.get(0).coordY=PaddleGame.getLevel().getPaddle().coordY+PaddleGame.getLevel().getPaddle().dimY/2+temp.get(0).dimX/2+3;
		}
		
	}


	/**
	 * @return the damageThisFrame
	 */
	public int getDamageThisFrame() {
		return damageThisFrame;
	}


	/**
	 * @param damageThisFrame the damageThisFrame to set
	 */
	public int setDamageThisFrame(int damageThisFrame) {
		this.damageThisFrame = damageThisFrame;
		return damageThisFrame;
	}


	/**
	 * @return the damage
	 */
	public static int getDamage() {
		return damage;
	}


	/**
	 * @param damage the damage to set
	 */
	public static void setDamage(int damage) {
		Ball.damage = damage;
	}


	/**
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}


	/**
	 * @param direction the direction to set
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}


	/**
	 * @return the radius
	 */
	public float getRadius() {
		return radius;
	}


	/**
	 * @param radius the radius to set
	 */
	public void setRadius(float radius) {
		this.radius = radius;
	}

}
