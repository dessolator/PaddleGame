package dev.game.project;

import static org.lwjgl.opengl.GL11.glColor3f;



import org.lwjgl.opengl.Display;

public class PlayerPaddle extends Collidable implements Movable{
	
	private boolean widened;
	private boolean narrowed;
	private int inverted=1;
	private float paddleSpeedUp=1;
	private static final float PADDLE_SPEED=7.2f;
	
	public void update(){
		
		
	}
	public PlayerPaddle(float cordX, float cordY, float dimX, float dimY) {
		this.coordX = cordX;
		this.coordY = cordY;
		this.dimX = dimX;
		this.dimY = dimY;
	}
	public void move(int i) {
		coordX+=PADDLE_SPEED*i*getInverted()*getPaddleSpeedUp();//move left or right
		if(coordX+dimX/2>Display.getWidth()){//check if boundary was hit
			coordX=Display.getWidth()-dimX/2;//if it was, stop
		}
		if(coordX-dimX/2<0){
			coordX=dimX/2;
		}
	}
	@Override
	public void collided(GameObject o) {
		((Ball)o).setFlipped(true);
		((Ball)o).setSpeedY(((Ball)o).getSpeedY() * ((coordY-o.coordY<0)?-1:1));//bounce the ball back
		((Ball)o).setSpeedX((float) (((Ball)o).getSpeedX() + (o.coordX-coordX)*0.8));//taking the angle into account
		if(((Ball)o).getSpeedX()>Ball.MAX_SPEED){//make sure ball speed doesn't exceed max
			((Ball)o).setSpeedX(Ball.MAX_SPEED);
		}
		if(((Ball)o).getSpeedX()<-Ball.MAX_SPEED){
			((Ball)o).setSpeedX(-Ball.MAX_SPEED);
		}

		
	}
	@Override
	public void render() {
		if(!PaddleGame.voodooMode){
			glColor3f(0.25f, 0.75f, 0.5f);//set drawing color to cyan
		}
		DrawObject.drawRect(coordX, coordY, dimX, dimY);
		
	}
	public void widen() {
		if(!isWidened()){
			setWidened(true);
			this.dimX*=1.5f;
			}
		Timer.removeTypedTimer(BonusType.PADDLE_WIDEN);
		Timer.getTimers().add(new Timer(10,BonusType.PADDLE_WIDEN));
	}
	
	
	
	public void invert() {
		if(getInverted()==1){
			setInverted(-1);
		}
		Timer.removeTypedTimer(BonusType.PADDLE_INVERT);
		Timer.getTimers().add(new Timer(10,BonusType.PADDLE_INVERT));
		
	}
	public void speedUp() {
		if(getPaddleSpeedUp()==1){
			setPaddleSpeedUp(1.5f);
		}
		Timer.removeTypedTimer(BonusType.PADDLE_SPEED);
		Timer.getTimers().add(new Timer(10,BonusType.PADDLE_SPEED));
	}
	public void narrow() {
		if(!isNarrowed()){
			setNarrowed(true);
			this.dimX/=1.5f;
			}
		Timer.removeTypedTimer(BonusType.PADDLE_NARROW);
		Timer.getTimers().add(new Timer(10,BonusType.PADDLE_NARROW));
	}
	/**
	 * @return the inverted
	 */
	public int getInverted() {
		return inverted;
	}
	/**
	 * @param inverted the inverted to set
	 */
	public void setInverted(int inverted) {
		this.inverted = inverted;
	}
	/**
	 * @return the narrowed
	 */
	public boolean isNarrowed() {
		return narrowed;
	}
	/**
	 * @param narrowed the narrowed to set
	 */
	public void setNarrowed(boolean narrowed) {
		this.narrowed = narrowed;
	}
	/**
	 * @return the paddleSpeedUp
	 */
	public float getPaddleSpeedUp() {
		return paddleSpeedUp;
	}
	/**
	 * @param paddleSpeedUp the paddleSpeedUp to set
	 */
	public void setPaddleSpeedUp(float paddleSpeedUp) {
		this.paddleSpeedUp = paddleSpeedUp;
	}
	/**
	 * @return the widened
	 */
	public boolean isWidened() {
		return widened;
	}
	/**
	 * @param widened the widened to set
	 */
	public void setWidened(boolean widened) {
		this.widened = widened;
	}
	
}
