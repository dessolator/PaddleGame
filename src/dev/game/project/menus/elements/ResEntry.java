package dev.game.project.menus.elements;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.TextureLoader;

import dev.game.project.engine.DrawObject;

public class ResEntry extends DropDownEntry {
	private int width;
	private int height;
	private static ResEntry setRes;

	public ResEntry(int res1,int res2,float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY);
		width=res1;
		height=res2;
		try {
			myTexture=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/"+width+'x'+height+".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void pressed() {
		setRes=this;
	}
	@Override
	public void render() {
//		glColor3f(0.25f,0.25f,0.5f);
//		DrawObject.drawColoredRect(getCoordX(), getCoordY(), getDimX(), getDimY());
		DrawObject.draw(this);
	}
	/**
	 * @return the setRes
	 */
	public static ResEntry getSetRes() {
		return setRes;
	}
	/**
	 * @param setRes the setRes to set
	 */
	public static void setSetRes(ResEntry setRes) {
		ResEntry.setRes = setRes;
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

}
