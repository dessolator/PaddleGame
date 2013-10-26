package dev.game.project.engine;

import org.newdawn.slick.opengl.Texture;

public interface TextureDrawable extends Drawable {
	public Texture getTexture();
	public float getCoordX();
	public float getDimX();
	public float getCoordY();
	public float getDimY();

}
