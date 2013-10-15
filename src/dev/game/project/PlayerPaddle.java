package dev.game.project;

import org.lwjgl.opengl.Display;

public class PlayerPaddle extends Collidable implements Movable{
	
	
	public void update(){
		DrawObject.drawRect(coordX, coordY, dimX, dimY);
	}
	public PlayerPaddle(float cordX, float cordY, float dimX, float dimY) {
		this.coordX = cordX;
		this.coordY = cordY;
		this.dimX = dimX;
		this.dimY = dimY;
	}
	public void move(int i) {
		coordX+=7.2*i;//move left or right
		if(coordX+dimX/2>Display.getWidth()){//check if boundary was hit
			coordX=Display.getWidth()-dimX/2;//if it was, stop
		}
		if(coordX-dimX/2<0){
			coordX=dimX/2;
		}
	}
	@Override
	public void collided(Ball o) {
		
		o.flipped=true;
		o.speedY*=((coordY-o.coordY<0)?-1:1);//bounce the ball back
		o.speedX+=(o.coordX-coordX)*0.8;//taking the angle into account
		if(o.speedX>Ball.MAX_SPEED){//make sure ball speed doesn't exceed max
			o.speedX=Ball.MAX_SPEED;
		}
		if(o.speedX<-Ball.MAX_SPEED){
			o.speedX=-Ball.MAX_SPEED;
		}
		// TODO Auto-generated method stub
		
	}

}
