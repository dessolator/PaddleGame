package dev.game.project.engine;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Engine {
	/**
	 * GL initialization function.Sets display mode, creates a display,
	 * clears the projection, sets up the perspective,blacks out the background and disables depth test. 
	 */
	public static void init() {
		try {			
			
			Display.setDisplayMode(getDisplayModeFromFile());
			
			Display.setFullscreen(false);//set to fullscreen.
			Display.create();//init the Display object.
			System.out.println(Display.getDisplayMode().getBitsPerPixel());
			System.out.println(Display.getDisplayMode().getFrequency());
			Display.setVSyncEnabled(true);//enable Vsync to avoid visual glitches.
			
			/*
			 * GL initialization.
			 */
			glMatrixMode(GL_PROJECTION);//choose the GL_PROJECTION matrix,
			glLoadIdentity();//and clear it.
			glOrtho(0,Display.getWidth(),Display.getHeight(),0,-1,1);//set the view to the initial plane.
			glMatrixMode(GL_MODELVIEW);//choose the GL_MODELVIEW matrix.
			glClearColor(0,0,0,1);//set background to black.
			glDisable(GL_DEPTH_TEST);//disable GL_DEPTH_TEST because the z axis is unused.
			glClear(GL_COLOR_BUFFER_BIT);//wipe random data from color buffer.
			glColor3f(0.25f, 0.75f, 0.5f);//set drawing color to cyan.
			glLoadIdentity();//clear GL_MODELVIEW matrix.
			glEnable(GL_TEXTURE_2D);//enable textures
			
			/*
			 * Keyboard initialization.
			 */
			Keyboard.create();//init Keyboard object.
			
			/*
			 * Mouse initialization.
			 */
			Mouse.create();//init Mouse object.
			
		} catch (LWJGLException e) {
			
			e.printStackTrace();//if GLinit went south, tell me where.
			
		} 		
	}
	/**
	 * Function used for post-game cleanup. Simply destroys the display.
	 */
	public static void kill() {
		
		Display.destroy();//destroy Display object.
		Keyboard.destroy();//destroy Keyboard object.
		Mouse.destroy();//destroy Mouse object.
		
	}
	private static DisplayMode getDisplayModeFromFile() throws LWJGLException{
		Scanner s = null;
		int targetWidth;
		int targetHeight;
		try {
			s = new Scanner(new BufferedReader(new FileReader("Settings.ini")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(!s.next().contentEquals("Resolution="));
		while(!s.hasNextInt()){
			s.next();
		}
		targetWidth=s.nextInt();
		while(!s.hasNextInt()){
			s.next();
		}
		targetHeight=s.nextInt();
		s.close();
		//TODO add if fullscreen check here
		DisplayMode [] available=Display.getAvailableDisplayModes();
		for(DisplayMode d:available){
			if(d.getWidth()==targetWidth && d.getHeight()==targetHeight){//TODO add refreshrate check
				return d;
			}
		}
		return new DisplayMode(800,600);
		
		
		
		
	}
	

}
