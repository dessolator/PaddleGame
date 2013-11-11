package dev.game.project.menus.text;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;

public abstract class Text{
	TrueTypeFont myFont;
	float coordX;
	float coordY;
	String myString;
	Color myColor;

	public Text(float coordX, float coordY, String myString, String myFont, Color myColor) {
		super();
		this.myFont=new TrueTypeFont(new Font(myFont,Font.PLAIN,15),false);
		this.coordX = coordX;
		this.coordY = coordY;
		this.myString = myString;
		this.myColor = myColor;
	}

	public void render() {
		myFont.drawString(coordX, coordY, myString, myColor);
		
	}
	

}
