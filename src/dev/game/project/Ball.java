package dev.game.project;

import static org.lwjgl.opengl.GL11.glColor3f;

public class Ball extends GameObject implements Movable {
	public static final float MAX_SPEED = 8f;//constant used to keep track of the maximum ball speed
	private float radius;//radius of the ball
	private float speedX=0.0f;//horizontal ball speed
	private float speedY=8f;//vertical ball speed
	private boolean flipped=false;
	private boolean spedUp=false;
	
	public Ball( float cordX, float cordY, float radius) {
		this.coordX = cordX;
		this.coordY = cordY;
		this.radius = radius;
		this.dimX=radius*2;
		this.dimY=radius*2;
	}
	
	
	@Override
	public void update() {
		setFlipped(false);
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
		if(!PaddleGame.voodooMode){
			glColor3f(0.25f, 0.75f, 0.5f);//set drawing color to cyan
		}
		DrawObject.drawCirclef(coordX,coordY, radius);//and draw it
		
	}


	public void speedUp() {
		if(!isSpedUp()){
			setSpedUp(true);
			speedY*=2;
			
		}
		Timer.removeTypedTimer(BonusType.BALL_SPEED);
		Timer.getTimers().add(new Timer(10,BonusType.BALL_SPEED));
		
	}


	public void increaseDamage() {
		// TODO Auto-generated method stub
		
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
		speedX=0f;
		speedY=8f;
		spedUp=false;
		coordX=PaddleGame.getLevel().getPaddle().coordX;
		coordY=PaddleGame.getLevel().getPaddle().coordY+PaddleGame.getLevel().getPaddle().dimY/2+dimX/2+3;
		
	}

}
