package dev.game.project;

import org.lwjgl.opengl.Display;

public class PlayerPaddle extends GameObject{
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

		
		//if paddle is moving left, collidng with the ball and the ball is coliding with the wall.... do not move the paddle
		if(i>0 && GamePhysics.hit(this, PaddleGame.getBall())&& GamePhysics.hit(PaddleGame.getRight(), PaddleGame.getBall())){
			return;
		}
		if(i<0 && GamePhysics.hit(this, PaddleGame.getBall())&& GamePhysics.hit(PaddleGame.getLeft(), PaddleGame.getBall())){
			System.out.println("left squish");
			return;
		}
		coordX+=0.1*i;//move left or right
		if(coordX+dimX/2>Display.getWidth()){//check if boundary was hit
			coordX=Display.getWidth()-dimX/2;//if it was, stop
		}
		if(coordX-dimX/2<0){
			coordX=dimX/2;
		}
	}

}
