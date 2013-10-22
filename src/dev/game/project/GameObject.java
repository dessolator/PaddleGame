package dev.game.project;

public abstract class GameObject {
	protected float coordX;//Variable used to store onscreen location in X.
	protected float coordY;//Variable used to store onscreen location in Y.
	protected float dimX;//Variable used to store the size of the game object in X.
	protected float dimY;//Variable used to store the size of the game object in Y.
	public abstract void update();//function used to track game changes frame by frame.
	public abstract void render();//function used to render game objects.

}
