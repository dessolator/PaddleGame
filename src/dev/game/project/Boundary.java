package dev.game.project;

public class Boundary extends GameObject{
	
	public void update(){
		DrawObject.drawRect(coordX, coordY, dimX, dimY);
	}
	
	
	public Boundary(float cordX, float cordY, float dimX, float dimY) {
		this.coordX = cordX;
		this.coordY = cordY;
		this.dimX = dimX;
		this.dimY = dimY;
	}
}
