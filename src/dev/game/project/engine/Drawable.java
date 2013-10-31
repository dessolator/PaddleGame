package dev.game.project.engine;

import org.newdawn.slick.opengl.Texture;

/**
 * Interface implemented by all drawable game objects.
 *
 */
public interface Drawable {
	/**
	 * The function used to trigger the drawing of THIS object.
	 */
	public void render();
	
	
	/**
	 * The function used to return the texture of THIS object.
	 * @return The texture of THIS object.
	 */
	public Texture getTexture();
	
	
	/**
	 * The function used to return the x coordinate of the center of this object.
	 * @return The x coordinate of the object's center.
	 */
	public float getCoordX();
	
	
	/**
	 * The function used to return the x dimension of this object.
	 * @return The x dimension of the object.
	 */
	public float getDimX();
	
	
	/**
	 * The function used to return the y coordinate of the center of this object.
	 * @return The y coordinate of the object's center.
	 */
	public float getCoordY();
	
	
	/**
	 * The function used to return the y dimension of this object.
	 * @return The y dimension of the object.
	 */
	public float getDimY();

}
