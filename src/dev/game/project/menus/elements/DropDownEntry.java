package dev.game.project.menus.elements;

import org.newdawn.slick.opengl.Texture;

import dev.game.project.menus.buttons.Button;

public abstract class DropDownEntry extends Button {

	@Override
	public Texture getTexture() {
		return myTexture;
	}

	public DropDownEntry(float coordX, float coordY, float dimX, float dimY,String myResolution) {
		super(coordX, coordY, dimX, dimY,myResolution);
		// TODO Auto-generated constructor stub
	}

	

}
