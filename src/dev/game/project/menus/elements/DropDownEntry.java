package dev.game.project.menus.elements;

import org.newdawn.slick.opengl.Texture;

import dev.game.project.menus.buttons.Button;

public abstract class DropDownEntry extends Button {

	@Override
	public Texture getTexture() {
		// TODO Auto-generated method stub
		return myTexture;
	}

	public DropDownEntry(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY);
		// TODO Auto-generated constructor stub
	}

	

}
