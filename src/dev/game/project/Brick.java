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
		//if hit head on
		o.flipped=true;
		System.out.println(Math.abs(((coordY-o.coordY>0)?(coordY-o.coordY):(o.coordY-coordY)))+3+":"+ (dimY/2+o.radius)+"="+(Math.abs(((coordY-o.coordY>0)?(coordY-o.coordY):(o.coordY-coordY)))+3-(dimY/2+o.radius)));
		if(Math.abs(((coordY-o.coordY>0)?(coordY-o.coordY):(o.coordY-coordY)))+3>=(dimY/2+o.radius)){
			System.out.println("flipping Y");
			o.speedY*=-1;//if the ball hits something, bounce it back
			
		}
		else{
			System.out.println("flipping X");
			o.speedX*=-1;
		}
		
		//if hit from the side
		//if hit diagonally
		destroyed=true;
		
	}


}
