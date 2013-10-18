package dev.game.project;

public abstract class Collidable extends GameObject{
	public abstract void collided(GameObject o);
	public boolean destroyed=false;

}
