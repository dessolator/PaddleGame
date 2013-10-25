package dev.game.project.gameObjects;

import static org.lwjgl.opengl.GL11.glColor3f;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.opengl.TextureLoader;

import dev.game.project.bonuses.BonusType;
import dev.game.project.bonuses.Timer;
import dev.game.project.gameMechanics.DrawObject;
import dev.game.project.gameMechanics.PaddleGame;

public class Ball extends GameObject implements Movable {
	
	/*
	 * Static Variables for Ball:
	 * 
	 * Maximum horizontal speed constant.
	 * Ball radius.
	 * Field keeping track whether the BALL_SPEED bonus was picked up.
	 * Current vertical speed of all balls.
	 */
	//====================================================================================================
	public static final float MAX_SPEED = 8f;//constant used to keep track of the maximum ball speed
	private static float radius;//radius of the ball
	private static boolean spedUp=false;//flag keeping track if the ball was sped up or not
	private static int damage=1;//ball damage multiplier
	private static float speedY=8f;//vertical ball speed common to all balls
	//====================================================================================================
	
	/*
	 * Instance Variables for Ball:
	 * 
	 * Current horizontal speed of the ball.
	 * Flag used to avoid multiple block collisions per frame.
	 * Direction of the ball (Up or Down).
	 * Damage the ball can still cause in the given frame.
	 */
	//====================================================================================================
	private float speedX=0.0f;//horizontal ball speed individual to all balls
	private boolean flipped=false;//variable used to stop the ball from hitting multiple blocks in a frame
	private int direction=1;//vertical direction of a ball individual to all balls
	private int damageThisFrame=getDamage();//damage the ball can still do in any given frame
	//====================================================================================================
	
	/**
	 * The Constructor for the ball.
	 * @param cordX	The x coordinate of the ball's center.
	 * @param cordY	The y coordinate of the ball's center.
	 * @param radius The radius of the ball.
	 */
	public Ball( float cordX, float cordY, float radius) {
		this.setCoordX(cordX);//Set coordX to the passed value.
		this.setCoordY(cordY);//Set coordY to the passed value.
		this.setRadius(radius);//Set radius to the passed value.
		this.setDimX(radius*2);//Calculate the x dimension.
		this.setDimY(radius*2);//Calculate the y dimension.
		try {
			setTexture(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/ball.png"))));//load the texture for the Ball
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	
	/* (non-Javadoc)
	 * @see dev.game.project.GameObject#update()
	 */
	@Override
	public void update() {
		
		if(flipped){//if the ball was flipped,
			damageThisFrame=getDamage();//reset the frameDamage,
			setFlipped(false);//and "unflip" the ball
		}
		
		move(0);//move the ball.
	}
	
	/* (non-Javadoc)
	 * @see dev.game.project.Movable#move(int)
	 */
	@Override
	public void move(int i) {
		
		setCoordX(getCoordX() + getSpeedX());//increase the x position
		setCoordY(getCoordY() + (getSpeedY()*getDirection()));//increase the y position
		
	}


	/* (non-Javadoc)
	 * @see dev.game.project.GameObject#render()
	 */
	@Override
	public void render() {
		
		if(!PaddleGame.isVoodooMode()){//if voodooMode is off,
			glColor3f(0.25f, 0.75f, 0.5f);//set drawing color to cyan.
		}
		
		//DrawObject.drawcoloredCirclef(getCoordX(),getCoordY(), getRadius());//draw the ball.
		DrawObject.drawGameObject(this);//draw the Ball using textures
	}


	/**
	 * Function used to trigger the ball speed bonus.
	 */
	public static void speedUp() {
		
		if(!isSpedUp()){//if ball was not yet sped up,
			setSpedUp(true);//set the flag,
			speedY*=1.5f;//and speed it up.
			
		}
		
		Timer.reset(BonusType.BALL_SPEED);//if the ball was already sped up the timer needs to be reset, if not make a new one.
		
	}


	/**
	 * Function used to trigger the ball damage bonus.
	 */
	public static void increaseDamage() {
		
		if(getDamage()==1){//if the ball damage was not yet increased,
			setDamage(getDamage() * 3);//increase the damage.
		}
		
		Timer.reset(BonusType.BALL_DAMAGE);//if the ball's damage was already increased the timer needs to be reset, if not make a new one.

		
	}


	/**
	 * Getter for speedX.
	 * @return The value of speedX.
	 */
	public float getSpeedX() {
		
		return speedX;
		
	}


	/**
	 * Setter for speedX.
	 * @param speedX The value of speedX to set.
	 */
	public void setSpeedX(float speedX) {
		
		this.speedX = speedX;
		
	}


	/**
	 * Getter for speedY.
	 * @return The value of speedY.
	 */
	public static float getSpeedY() {
		
		return speedY;
		
	}


	/**
	 * Setter for speedY.
	 * @param speedY The value of speedY to set.
	 */
	public static void setSpeedY(float speedY) {
		
		Ball.speedY = speedY;
		
	}


	/**
	 * Getter for flipped.
	 * @return The value of flipped.
	 */
	public boolean isFlipped() {
		
		return flipped;
		
	}


	/**
	 * Setter for flipped.
	 * @param flipped The value of flipped to set.
	 */
	public void setFlipped(boolean flipped) {
		
		this.flipped = flipped;
		
	}


	/**
	 * Getter for spedUp.
	 * @return The value of spedUp.
	 */
	public static boolean isSpedUp() {
		
		return spedUp;
		
	}


	/**
	 * Setter for spedUp.
	 * @param spedUp The value of spedUp to set.
	 */
	public static void setSpedUp(boolean spedUp) {
		
		Ball.spedUp = spedUp;
		
	}




	/**
	 * Function used to reset the ball.
	 */
	public static void reset() {
		
		ArrayList<Ball> temp=PaddleGame.getLevel().getBalls();
		if(temp.isEmpty()){//if there are no balls in the level,
			
			PaddleGame.getLevel().spawnBall();//make a new one,
			setDamage(1);//reset the ball damage,
			temp.get(0).setDamageThisFrame(getDamage());//ball frame damage,
			temp.get(0).speedX=0f;//ball x speed,
			temp.get(0).setDirection(1);//ball direction,
			speedY=8f;//ball y speed,
			spedUp=false;//ball flags,
			temp.get(0).setCoordX(PaddleGame.getLevel().getPaddle().getCoordX());//and ball coordinates.
			temp.get(0).setCoordY(PaddleGame.getLevel().getPaddle().getCoordY()+PaddleGame.getLevel().getPaddle().getDimY()/2+temp.get(0).getDimX()/2+3);
			
		}
		
	}


	/**
	 * Getter for damageThisFrame.
	 * @return The value of damageThisFrame.
	 */
	public int getDamageThisFrame() {
		
		return damageThisFrame;
		
	}


	/**
	 * Setter for damageThisFrame.
	 * @param damageThisFrame The value of damageThisFrame to set.
	 */
	public int setDamageThisFrame(int damageThisFrame) {
		
		this.damageThisFrame = damageThisFrame;
		return damageThisFrame;
		
	}


	/**
	 * Getter for the damage.
	 * @return The value of damage.
	 */
	public static int getDamage() {
		
		return damage;
		
	}


	/**
	 * Setter for the damage.
	 * @param damage The value of damage to set.
	 */
	public static void setDamage(int damage) {
		
		Ball.damage = damage;
		
	}


	/**
	 * Getter for direction.
	 * @return The value of direction.
	 */
	public int getDirection() {
		
		return direction;
		
	}


	/**
	 * Setter for direction.
	 * @param direction The value of direction to set.
	 */
	public void setDirection(int direction) {
		
		this.direction = direction;
		
	}


	/**
	 * Getter for the radius.
	 * @return The value of the radius.
	 */
	public float getRadius() {
		
		return radius;
		
	}


	/**
	 * Setter for the radius.
	 * @param radius The value of the radius to set.
	 */
	public void setRadius(float radius) {
		
		Ball.radius = radius;
		
	}

}
