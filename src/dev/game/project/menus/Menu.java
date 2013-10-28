package dev.game.project.menus;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
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
		Mouse.setGrabbed(false);
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
		while(Mouse.next()){
			if(Mouse.getEventButtonState()){
				if(Mouse.getEventButton()==0){
					for(Button b:myButtons){
						if(b.isInBounds(Mouse.getX(), Mouse.getY())){
							b.click();
						}
					}
				}
			}else
			{
				if(Mouse.getEventButton()==0){
					for(Button b:myButtons){
						if(b.isInBounds(Mouse.getX(), Mouse.getY()) && b.isClicked()){
							b.pressed();
						}
						else{
							b.unClick();
						}
					}
				}
			}
		}
	}

}
