package dev.game.project;

import static org.lwjgl.opengl.GL11.glColor3f;

import org.lwjgl.opengl.Display;

public class PlayerPaddle extends Collidable implements Movable{
	
	
	public void update(){
		
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
	public void collided(GameObject o) {
		((Ball)o).flipped=true;
		((Ball)o).speedY*=((coordY-o.coordY<0)?-1:1);//bounce the ball back
		((Ball)o).speedX+=(o.coordX-coordX)*0.8;//taking the angle into account
		if(((Ball)o).speedX>Ball.MAX_SPEED){//make sure ball speed doesn't exceed max
			((Ball)o).speedX=Ball.MAX_SPEED;
		}
		if(((Ball)o).speedX<-Ball.MAX_SPEED){
			((Ball)o).speedX=-Ball.MAX_SPEED;
		}
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render() {
		if(!PaddleGame.voodooMode){
			glColor3f(0.25f, 0.75f, 0.5f);//set drawing color to cyan
		}
		DrawObject.drawRect(coordX, coordY, dimX, dimY);
		
	}
	public void widen() {
		// TODO Auto-generated method stub
		
	}
	public void invert() {
		// TODO Auto-generated method stub
		
	}
	public void speedUp() {
		// TODO Auto-generated method stub
		
	}
	public void narrow() {
		// TODO Auto-generated method stub
		
	}

}
