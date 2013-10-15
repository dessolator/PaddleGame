package dev.game.project;

public class Boundary extends Collidable{
	Sides side;
	public void update(){
		DrawObject.drawRect(coordX, coordY, dimX, dimY);
	}
	
	
	public Boundary(float coordX, float coordY, float dimX, float dimY,Sides side) {
		this.side=side;
		this.coordX = coordX;
		this.coordY = coordY;
		this.dimX = dimX;
		this.dimY = dimY;
	}


	@Override
	public void collided(Ball o) {
		o.flipped=true;
		switch(side){
		case LEFT:
			o.speedX*=-1;//bounce the ball off
			break;
		case RIGHT:
			o.speedX*=-1;//bounce the ball off
			break;
		case TOP:
			o.speedY*=-1;// bounce the ball back down
			break;
		case BOTTOM:
			o.coordX=PaddleGame.myPaddle.coordX;
			o.coordY=PaddleGame.myPaddle.coordY+PaddleGame.myPaddle.dimY/2+o.radius+3;
			o.speedX=0;
			o.speedY=8f;
			break;
		default:
			//BROKEN
			break;
				
		}
		
	}
}
