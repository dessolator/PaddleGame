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
		if(!o.flipped){
			o.flipped=true;
			if(((o.coordX>=(coordX-(dimX/2+o.radius)))&&(o.coordX<=(coordX+(dimX/2+o.radius))))&&
			(((o.coordY<=(coordY-dimY/2))&&(o.coordY>=(coordY-(dimY/2+o.radius)))) ||
			((o.coordY<=(coordY+(dimY/2+o.radius)))&&(o.coordY>=(coordY+dimY/2)))))
			{
				o.speedY*=-1;//if the ball hits something, bounce it back
				
			}
			else{
				o.speedX*=-1;
			}
			destroyed=true;
		}
		
	}


}
