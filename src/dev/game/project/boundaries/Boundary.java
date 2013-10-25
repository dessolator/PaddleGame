package dev.game.project.boundaries;

import dev.game.project.engine.Collidable;

public abstract class Boundary extends Collidable{
	
	
	/* (non-Javadoc)
	 * @see dev.game.project.GameObject#update()
	 */
	public void update(){
	}
	
	
	/**
	 * The Constructor for the boundaries.
	 * @param coordX The x coordinate of the boundary center.
	 * @param coordY The y coordinate of the boundary center.
	 * @param dimX The x dimension of the boundary.
	 * @param dimY The y dimension of the boundary.
	 * @param side The side the boundary is on.
	 */
	public Boundary(float coordX, float coordY, float dimX, float dimY) {
		this.setCoordX(coordX);//set coordX to passed value.
		this.setCoordY(coordY);//set coordY to passed value.
		this.setDimX(dimX);//set dimX to passed value.
		this.setDimY(dimY);//set dimY to passed value.
	}


	


	/* (non-Javadoc)
	 * @see dev.game.project.GameObject#render()
	 */
	@Override
	public void render() {}
}
