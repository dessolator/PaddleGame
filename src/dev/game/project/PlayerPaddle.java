package dev.game.project;

import static org.lwjgl.opengl.GL11.glColor3f;



import org.lwjgl.opengl.Display;

public class PlayerPaddle extends Collidable implements Movable{
	
	private boolean widened;//flag for the widen bonus.
	private boolean narrowed;//flag for the narrow bonus.
	private int inverted=1;//flag and variable for the invert bonus.
	private float paddleSpeedUp=1;//flag and variable for paddleSpeed bonus.
	private static final float PADDLE_SPEED=7.8f;//paddle speed constant.
	

	/* (non-Javadoc)
	 * @see dev.game.project.GameObject#update()
	 */
	public void update(){//left unimplemented, all the paddle updating is done in input processing.
		
		
	}
	/**
	 * The Constructor for the player paddle.
	 * @param cordX The x coordinate of the paddle center.
	 * @param cordY The y coordinate of the paddle center.
	 * @param dimX The x dimension of the paddle.
	 * @param dimY The y dimension of the paddle.
	 */
	public PlayerPaddle(float cordX, float cordY, float dimX, float dimY) {
		this.coordX = cordX;//set coordX to passed value.
		this.coordY = cordY;//set coordY to passed value.
		this.dimX = dimX;//set dimX to passed value.
		this.dimY = dimY;//set dimY to passed value.
	}
	/* (non-Javadoc)
	 * @see dev.game.project.Movable#move(int)
	 */
	public void move(int i) {
		coordX+=PADDLE_SPEED*i*getInverted()*getPaddleSpeedUp();//move left or right
		if(coordX+dimX/2>Display.getWidth()){//check if boundary was hit,
			coordX=Display.getWidth()-dimX/2;//if it was, stop.
		}
		if(coordX-dimX/2<0){//check if boundary was hit,
			coordX=dimX/2;//if it was, stop.
		}
	}
	/* (non-Javadoc)
	 * @see dev.game.project.Collidable#collided(dev.game.project.GameObject)
	 */
	@Override
	public void collided(GameObject o) {
		((Ball)o).setSpeedY(((Ball)o).getSpeedY() * ((coordY-o.coordY<0)?-1:1));//bounce the ball back,
		((Ball)o).setSpeedX((float) (((Ball)o).getSpeedX() + (o.coordX-coordX)*0.8));//taking the angle into account.
		if(((Ball)o).getSpeedX()>Ball.MAX_SPEED){//make sure ball speed doesn't exceed max,
			((Ball)o).setSpeedX(Ball.MAX_SPEED);//if it does, set speed to max.
		}
		if(((Ball)o).getSpeedX()<-Ball.MAX_SPEED){//make sure ball speed doesn't exceed max,
			((Ball)o).setSpeedX(-Ball.MAX_SPEED);//if it does, set speed to max.
		}

		
	}
	/* (non-Javadoc)
	 * @see dev.game.project.GameObject#render()
	 */
	@Override
	public void render() {
		if(!PaddleGame.isVoodooMode()){//if voodooMode is off,
			glColor3f(0.25f, 0.75f, 0.5f);//set drawing color to cyan.
		}
		DrawObject.drawRect(coordX, coordY, dimX, dimY);//draw the rectangle
		
	}
	/**
	 * Function used to trigger the widen bonus.
	 */
	public void widen() {
		if(!isWidened()){//if paddle isn't already widened,
			setWidened(true);//set the flag,
			this.dimX*=1.5f;//widen the paddle.
			}
		Timer.reset(BonusType.PADDLE_WIDEN);//if the paddle was already widened the timer needs to be reset, if not make a new one.
	}
	
	
	
	/**
	 * Function used to trigger the widen bonus.
	 */
	public void invert() {
		if(getInverted()==1){//if paddle isn't already inverted,
			setInverted(-1);//invert the paddle.
		}
		Timer.reset(BonusType.PADDLE_INVERT);//if the paddle was already inverted reset the timer, if not make a new one.
		
	}
	/**
	 * Function used to trigger the paddle speed bonus.
	 */
	public void speedUp() {
		if(getPaddleSpeedUp()==1){//if the paddle isn't already sped up,
			setPaddleSpeedUp(1.5f);//speed it up.
		}
		Timer.reset(BonusType.PADDLE_SPEED);//if the paddle was already sped up reset the timer, if not make a new one.
	}
	/**
	 * Function used to trigger the narrow bonus.
	 */
	public void narrow() {
		if(!isNarrowed()){//if the paddle isn't already narrowed,
			setNarrowed(true);//set the flag,
			this.dimX/=1.5f;//narrow the paddle.
			}
		Timer.reset(BonusType.PADDLE_NARROW);//if the paddle was already narrowed reset the timer, if not make a new one.
	}
	/**
	 * Getter for inverted.
	 * @return The value of inverted.
	 */
	public int getInverted() {
		return inverted;
	}
	/**
	 * Setter for inverted.
	 * @param inverted The value of inverted to set.
	 */
	public void setInverted(int inverted) {
		this.inverted = inverted;
	}
	/**
	 * Getter for narrowed.
	 * @return The value of narrowed.
	 */
	public boolean isNarrowed() {
		return narrowed;
	}
	/**
	 * Setter for narrowed.
	 * @param narrowed The value of narrowed to set.
	 */
	public void setNarrowed(boolean narrowed) {
		this.narrowed = narrowed;
	}
	/**
	 * Getter for paddleSpeedUp.
	 * @return The value of paddleSpeedUp.
	 */
	public float getPaddleSpeedUp() {
		return paddleSpeedUp;
	}
	/**
	 * Setter for paddleSpeedUp.
	 * @param paddleSpeedUp The value of paddleSpeedUp to set.
	 */
	public void setPaddleSpeedUp(float paddleSpeedUp) {
		this.paddleSpeedUp = paddleSpeedUp;
	}
	/**
	 * Getter for widened.
	 * @return The value of widened.
	 */
	public boolean isWidened() {
		return widened;
	}
	/**
	 * Setter for widened.
	 * @param widened The value of widened to set
	 */
	public void setWidened(boolean widened) {
		this.widened = widened;
	}
	
}
