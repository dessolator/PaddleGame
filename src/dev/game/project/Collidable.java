package dev.game.project;

public abstract class Collidable extends GameObject{
	public abstract void collided(Ball o);
	public boolean destroyed=false;

}
