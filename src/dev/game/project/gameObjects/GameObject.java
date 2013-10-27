package dev.game.project.gameObjects;

import org.newdawn.slick.opengl.Texture;
import dev.game.project.engine.Drawable;
import dev.game.project.gameMechanics.Updateable;

public abstract class GameObject implements Drawable, Updateable{
	private float coordX;//Variable used to store onscreen location in X.
	private float coordY;//Variable used to store onscreen location in Y.
	private float dimX;//Variable used to store the size of the game object in X.
	private float dimY;//Variable used to store the size of the game object in Y.
	private Texture texture=null;
	/**
	 * @return the texture
	 */
	public Texture getTexture() {
		return texture;
	}
	/**
	 * @param texture the texture to set
	 */
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	/**
	 * @return the coordX
	 */
	public float getCoordX() {
		return coordX;
	}
	/**
	 * @param coordX the coordX to set
	 */
	public void setCoordX(float coordX) {
		this.coordX = coordX;
	}
	/**
	 * @return the coordY
	 */
	public float getCoordY() {
		return coordY;
	}
	/**
	 * @param coordY the coordY to set
	 */
	public void setCoordY(float coordY) {
		this.coordY = coordY;
	}
	/**
	 * @return the dimY
	 */
	public float getDimY() {
		return dimY;
	}
	/**
	 * @param dimY the dimY to set
	 */
	public void setDimY(float dimY) {
		this.dimY = dimY;
	}
	/**
	 * @return the dimX
	 */
	public float getDimX() {
		return dimX;
	}
	/**
	 * @param dimX the dimX to set
	 */
	public void setDimX(float dimX) {
		this.dimX = dimX;
	}

}
