package dev.game.project;

public class Boundary extends Collidable{
	Sides side;
	public void update(){
	}
	
	
	public Boundary(float coordX, float coordY, float dimX, float dimY,Sides side) {
		this.side=side;
		this.coordX = coordX;
		this.coordY = coordY;
		this.dimX = dimX;
		this.dimY = dimY;
	}


	@Override
	public void collided(GameObject o) {
		((Ball)o).setFlipped(true);
		switch(side){
		case LEFT:
			((Ball)o).setSpeedX(((Ball)o).getSpeedX() * -1);//bounce the ball off
			break;
		case RIGHT:
			((Ball)o).setSpeedX(((Ball)o).getSpeedX() * -1);//bounce the ball off
			break;
		case TOP:
			((Ball)o).setSpeedY(((Ball)o).getSpeedY() * -1);// bounce the ball back down
			break;
		case BOTTOM:
			((Ball)o).reset();
			break;
		default:
			//BROKEN
			break;
				
		}
		
	}


	@Override
	public void render() {
		DrawObject.drawRect(coordX, coordY, dimX, dimY);		
	}
}
