package dev.game.project;

public class Ball extends GameObject implements Movable {
	public static final float MAX_SPEED = 0.1f;//constant used to keep track of the maximum ball speed
	float radius;//radius of the ball
	float speedX=0.0f;//horizontal ball speed
	float speedY=0.1f;//vertical ball speed
	
	public Ball( float cordX, float cordY, float radius) {
		this.coordX = cordX;
		this.coordY = cordY;
		this.radius = radius;
		this.dimX=radius*2;
		this.dimY=radius*2;
	}
	
	
	@Override
	public void update() {
		move(0);//each frame, move the ball
		DrawObject.drawCirclef(coordX,coordY, radius);//and draw it
	}
	/**
	 * Function used to move the ball in a preset direction.
	 */
	public void move(int i) {
		coordX+=speedX;//increase the x position
		coordY+=speedY;//increase the y position
	}

}
