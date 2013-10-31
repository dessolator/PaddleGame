package dev.game.project.menus.elements;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


public class DisplayDropDown extends DropDown {

	

	public DisplayDropDown(float coordX, float coordY,float dimX, float dimY) {
		super( coordX, coordY,dimX, dimY);
	}

	@Override
	protected void populate() {
		Scanner s = null;
		DisplayMode[] modes=null;
		int first;
		int second;
		try {
			s = new Scanner(new BufferedReader(new FileReader("Settings.ini")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("moo");
		System.out.println(s.next());
		System.out.println(s.next());
		System.out.println(s.next());
		System.out.println(s.next());
		System.out.println(s.next());
//		while(s.next()!="Resolution");
		first=s.nextInt();
		System.out.println(s.next());
		second=s.nextInt();
		def=new ResEntry(first,second,getCoordX(),getCoordY(),getDimX(),getDimY());
		def.pressed();
		try {
			modes = Display.getAvailableDisplayModes();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=1;i<modes.length;i++){
			myEntries.add(new ResEntry(modes[i].getWidth(),modes[i].getHeight(),getCoordX(),(getCoordY()-i*getDimY()),getDimX(),getDimY()));
		}
	}

	@Override
	public void update() {
		def=new ResEntry(ResEntry.getSetRes().getWidth(),ResEntry.getSetRes().getHeight(),getCoordX(),getCoordY(),getDimX(),getDimY());
		super.update();
	}

}
