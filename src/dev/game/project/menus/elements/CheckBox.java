package dev.game.project.menus.elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import dev.game.project.menus.buttons.Button;

public abstract class CheckBox extends Button{
	


	boolean isChecked=false;
	Texture checked;
	public CheckBox(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY,"");
		try {
			myTexture=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/checkBox.png")));
			checked=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/checkBoxChecked.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	@Override
	public Texture getTexture() {
		if(isChecked)
			return checked;
		return myTexture;
	}



	@Override
	public void pressed() {
		isChecked=!isChecked;
		
	}
	
}
