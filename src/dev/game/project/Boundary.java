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
		((Ball)o).flipped=true;
		switch(side){
		case LEFT:
			((Ball)o).speedX*=-1;//bounce the ball off
			break;
		case RIGHT:
			((Ball)o).speedX*=-1;//bounce the ball off
			break;
		case TOP:
			((Ball)o).speedY*=-1;// bounce the ball back down
			break;
		case BOTTOM:
			o.coordX=PaddleGame.getLevel().getPaddle().coordX;
			o.coordY=PaddleGame.getLevel().getPaddle().coordY+PaddleGame.getLevel().getPaddle().dimY/2+o.dimX/2+3;
			((Ball)o).speedX=0;
			((Ball)o).speedY=8f;
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
