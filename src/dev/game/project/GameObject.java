package dev.game.project;

public abstract class GameObject {
	float coordX;//Variable used to store onscreen location in X
	float coordY;//Variable used to store onscreen location in Y
	float dimX;//Variable used to store the size of the game object in X
	float dimY;//Variable used to store the size of the game object in Y
	public abstract void update();//function used to track game changes frame by frame
	/**
	 * Function used to determine if an object is a brick
	 * @return Returns True if the the object is not boolean
	 */
	public boolean isBrick(){//function used to determine whether an object is destructable 
		return false;
	}

}
