package dev.game.project;

public abstract class Movable extends GameObject{

	@Override
	public abstract void update();
	
	public abstract void move(int i);
}
