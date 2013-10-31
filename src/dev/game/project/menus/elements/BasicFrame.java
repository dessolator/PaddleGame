package dev.game.project.menus.elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.TextureLoader;

public class BasicFrame extends Frame {

	public BasicFrame(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY);
		try {
			frame=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/basicFrame.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
