package dev.game.project.menus.buttons;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import dev.game.project.engine.DrawObject;
import dev.game.project.engine.Drawable;
import dev.game.project.engine.Updateable;
import dev.game.project.menus.text.Text;
import dev.game.project.menus.text.TimesNewRomanText;


public abstract class Button implements Drawable, Updateable{
	protected Texture myTexture;
	protected Texture pressedTexture;
	Text myText;
	protected boolean clicked=false;
	private boolean previousMouseState=false;
	private float coordX;
	private float coordY;
	private float dimX;
	private float dimY;
	
	
	public Button(float coordX, float coordY, float dimX, float dimY,String myText) {
		this.myText=new TimesNewRomanText(coordX,coordY,myText);
		this.coordX = coordX;
		this.coordY = coordY;
		this.dimX = dimX;
		this.dimY = dimY;
		try {
			myTexture=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/Button.png")));
			pressedTexture=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/pressedButton.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(){
		DrawObject.draw(this);
		myText.render();
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
	@Override
	public void update() {
		if(Mouse.getEventButtonState()&&isInBounds(Mouse.getX(), Mouse.getY())){
			click();
			previousMouseState=true;
			return;
		}
		if(previousMouseState&&!Mouse.getEventButtonState()&&isInBounds(Mouse.getX(), Mouse.getY()) && isClicked()){
			pressed();
			previousMouseState=false;
			return;
		}
		unClick();
		
	}

}
