package dev.game.project.menus.buttons;

import static org.lwjgl.opengl.GL11.glColor3f;

import org.newdawn.slick.opengl.Texture;

import dev.game.project.engine.DrawObject;
import dev.game.project.engine.Drawable;

public abstract class Button implements Drawable{
	protected Texture myTexture;
	protected Texture pressedTexture;
	protected boolean clicked=false;
	public Button(float coordX, float coordY, float dimX, float dimY) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.dimX = dimX;
		this.dimY = dimY;
	}
	private float coordX;
	private float coordY;
	private float dimX;
	private float dimY;
	public void render(){
		glColor3f(0.25f, 0.25f, 0.5f);
		DrawObject.drawColoredRect(getCoordX(), getCoordY(), getDimX(), getDimY());
		//DrawObject.draw(this);
	}
	public void click(){
		clicked=true;
	}
	public void unClick(){
		clicked=false;
	}
	public boolean isClicked(){
		return clicked;
	}
	public boolean isInBounds(float x,float y){
		if (x<(coordX-(dimX/2)))
			return false;
		if (x>(coordX+(dimX/2)))
			return false;
		if (y>(coordY+(dimY/2)))
			return false;
		if (y<(coordY-(dimY/2)))
			return false;
		return true;
	}
	@Override
	public Texture getTexture() {
		if(clicked)
			return pressedTexture;
		return myTexture;
	}
	@Override
	public float getCoordX() {
		return coordX;
	}
	@Override
	public float getDimX() {
		return dimX;
	}
	@Override
	public float getCoordY() {
		return coordY;
	}
	@Override
	public float getDimY() {
		return dimY;
	}
	public abstract void pressed();

}
