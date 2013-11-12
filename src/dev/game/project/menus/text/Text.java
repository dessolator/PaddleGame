package dev.game.project.menus.text;

import java.awt.Font;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public abstract class Text{
	TrueTypeFont myFont;
	float coordX;
	float coordY;
	String myString;
	Color myColor;

	public Text(float coordX, float coordY, String myString, String myFont, Color myColor) {
		super();
		this.myFont=new TrueTypeFont(new Font(myFont,Font.PLAIN,25),true);
		this.coordX = coordX;
		this.coordY = coordY;
		this.myString = myString;
		this.myColor = myColor;
	}

	public void render() {
		myFont.drawString(
				coordX-myFont.getWidth(myString)/2,
				(Display.getHeight()-coordY)-myFont.getHeight(myString)/2,
				myString,
				myColor);
		
	}
	

}
