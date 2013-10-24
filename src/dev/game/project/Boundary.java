package dev.game.project;

public class Boundary extends Collidable{
	Sides side;//field used to keep track of what side the boundary is on.
	
	/* (non-Javadoc)
	 * @see dev.game.project.GameObject#update()
	 */
	public void update(){
	}
	
	
	/**
	 * The Constructor for the boundaries.
	 * @param coordX The x coordinate of the boundary center.
	 * @param coordY The y coordinate of the boundary center.
	 * @param dimX The x dimension of the boundary.
	 * @param dimY The y dimension of the boundary.
	 * @param side The side the boundary is on.
	 */
	public Boundary(float coordX, float coordY, float dimX, float dimY,Sides side) {
		this.side=side;//set side to passed value.
		this.setCoordX(coordX);//set coordX to passed value.
		this.setCoordY(coordY);//set coordY to passed value.
		this.setDimX(dimX);//set dimX to passed value.
		this.setDimY(dimY);//set dimY to passed value.
	}


	/* (non-Javadoc)
	 * @see dev.game.project.Collidable#collided(dev.game.project.GameObject)
	 */
	@Override
	public void collided(GameObject o) {
		switch(side){
		case LEFT:
			((Ball)o).setSpeedX(((Ball)o).getSpeedX() * -1);//bounce the ball off.
			break;
		case RIGHT:
			((Ball)o).setSpeedX(((Ball)o).getSpeedX() * -1);//bounce the ball off.
			break;
		case TOP:
			((Ball)o).setDirection(((Ball)o).getDirection() * -1);// bounce the ball back down.
			break;
		case BOTTOM:
			PaddleGame.getLevel().getMyBalls().remove((Ball)o);
			Ball.reset();//reset the ball.
			break;
		default:
			//BROKEN
			break;
				
		}
		
	}


	/* (non-Javadoc)
	 * @see dev.game.project.GameObject#render()
	 */
	@Override
	public void render() {
		DrawObject.drawRect(getCoordX(), getCoordY(), getDimX(), getDimY());//simply draw the side as it's color is entirely irrelevant, it's always out of view.	
	}
}
