package dev.game.project.menus;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import dev.game.project.engine.DrawObject;
import dev.game.project.engine.Drawable;
import dev.game.project.gameMechanics.Updateable;
import dev.game.project.menus.buttons.Button;

public abstract class Menu implements Drawable, Updateable{
	protected ArrayList<Button> myButtons;
	protected Texture background;
	protected Frame myFrame;
	
	@Override
	public void render() {
		DrawObject.draw(this);
		myFrame.render();
		for (Button b:myButtons)
			DrawObject.draw(b);		
	}
	@Override
	public Texture getTexture() {
		return background;
	}
	@Override
	public float getCoordX() {
		return (float)Display.getWidth()/2;
	}
	@Override
	public float getDimX() {
		return (float)Display.getWidth();
	}
	@Override
	public float getCoordY() {
		return (float)Display.getHeight()/2;
	}
	@Override
	public float getDimY(){
		return (float)Display.getHeight();
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
