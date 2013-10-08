package dev.game.project;

public class Brick extends GameObject {
	
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
	public boolean isBrick(){//function returns true only for bricks but is present everywhere (easier and cheaper than class check)
		return true;
	}


}
