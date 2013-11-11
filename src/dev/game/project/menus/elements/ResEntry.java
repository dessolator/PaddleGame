package dev.game.project.menus.elements;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import dev.game.project.engine.DrawObject;

public class ResEntry extends DropDownEntry {
	private int width;
	private int height;
	private static ResEntry setRes;
	static{
		Scanner s = null;
		int first;
		int second;
		try {
			s = new Scanner(new BufferedReader(new FileReader("Settings.ini")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String c=s.next();
//		while(!c.contentEquals("Resolution")){
//			c=s.next();
		while(!s.hasNextInt()){
			s.next();
		}
		first=s.nextInt();
		while(!s.hasNextInt()){
			s.next();
		}
		second=s.nextInt();
		setRes=new ResEntry(first,second,0,0,0,0);
	}

	public ResEntry(int res1,int res2,float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY,(""+res1+"x"+res2));
		width=res1;
		height=res2;
		
		
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
