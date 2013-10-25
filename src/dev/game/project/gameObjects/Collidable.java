package dev.game.project.gameObjects;

public abstract class Collidable extends GameObject{
	public abstract void collided(GameObject o);//collidable objects need to collide with something
	public boolean destroyed=false;//collidable objects might be destroyed (in case of bricks for example)

}
