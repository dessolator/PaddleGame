package dev.game.project.engine;

import org.newdawn.slick.opengl.Texture;

public interface Drawable {
	public void render();
	public Texture getTexture();
	public float getCoordX();
	public float getDimX();
	public float getCoordY();
	public float getDimY();

}
