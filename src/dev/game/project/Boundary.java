package dev.game.project;

public class Boundary extends Collidable{
	Sides side;
	public void update(){
		DrawObject.drawRect(coordX, coordY, dimX, dimY);
	}
	
	
	public Boundary(float cordX, float cordY, float dimX, float dimY,Sides side) {
		this.side=side;
		this.coordX = cordX;
		this.coordY = cordY;
		this.dimX = dimX;
		this.dimY = dimY;
	}


	@Override
	public void collided(Ball o) {
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
			o.coordY=PaddleGame.myPaddle.coordY+PaddleGame.myPaddle.dimY/2+o.radius;
			o.speedX=0;
			o.speedY=0.1f;
			break;
		default:
			//BROKEN
			break;
				
		}
		
	}
}
