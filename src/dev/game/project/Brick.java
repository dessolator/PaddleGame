package dev.game.project;

public class Brick extends Collidable {
	
	public Brick(float cordX, float cordY, float dimX, float dimY) {
		this.coordX = cordX;
		this.coordY = cordY;
		this.dimX = dimX;
		this.dimY = dimY;
	}
	@Override 
	public void update() {
		DrawObject.drawRect(coordX, coordY, dimX, dimY);
		

	}

	@Override
	public void collided(Ball o) {
		o.speedY*=-1;//if the ball hits something, bounce it back
		destroyed=true;
		
	}


}
