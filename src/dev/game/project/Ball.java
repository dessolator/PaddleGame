package dev.game.project;

import static org.lwjgl.opengl.GL11.glColor3f;

public class Ball extends GameObject implements Movable {
	public static final float MAX_SPEED = 8f;//constant used to keep track of the maximum ball speed
	private float radius;//radius of the ball
	private float speedX=0.0f;//horizontal ball speed
	private float speedY=8f;//vertical ball speed
	private boolean flipped=false;
	private boolean spedUp=false;
	private int damage=1;
	private int damageThisFrame=getDamage();
	
	public Ball( float cordX, float cordY, float radius) {
		this.coordX = cordX;
		this.coordY = cordY;
		this.radius = radius;
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
		coordY+=getSpeedY();//increase the y position
	}


	@Override
	public void render() {
		if(!PaddleGame.isVoodooMode()){
			glColor3f(0.25f, 0.75f, 0.5f);//set drawing color to cyan
		}
		DrawObject.drawCirclef(coordX,coordY, radius);//and draw it
		
	}


	public void speedUp() {
		if(!isSpedUp()){
			setSpedUp(true);
			speedY*=2;
			
		}
		Timer.reset(BonusType.BALL_SPEED);
		
	}


	public void increaseDamage() {
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
	public float getSpeedY() {
		return speedY;
	}


	/**
	 * @param speedY the speedY to set
	 */
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
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
	public boolean isSpedUp() {
		return spedUp;
	}


	/**
	 * @param spedUp the spedUp to set
	 */
	public void setSpedUp(boolean spedUp) {
		this.spedUp = spedUp;
	}




	public void reset() {
		setDamage(1);
		setDamageThisFrame(getDamage());
		speedX=0f;
		speedY=8f;
		spedUp=false;
		coordX=PaddleGame.getLevel().getPaddle().coordX;
		coordY=PaddleGame.getLevel().getPaddle().coordY+PaddleGame.getLevel().getPaddle().dimY/2+dimX/2+3;
		
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
	public int getDamage() {
		return damage;
	}


	/**
	 * @param damage the damage to set
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

}
