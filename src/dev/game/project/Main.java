package dev.game.project;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Main {

	
	public static void main(String[] args) {
		initDisplay();
		PaddleGame.startGame(false);
		clearDisplay();
	}

	/**
	 * GL initialization function.Sets display mode, creates a display,
	 * clears the projection, sets up the perspective,blacks out the background and disables depth test. 
	 */
	private static void initDisplay() {
		try {			
			DisplayMode[] modes = Display.getAvailableDisplayModes();
			 
			for (int i=0;i<modes.length;i++) {
			    DisplayMode current = modes[i];
			    if(modes[i].isFullscreenCapable())
			    System.out.println(i+":"+current.getWidth() + "x" + current.getHeight() + "x" +
			                        current.getBitsPerPixel() + " " + current.getFrequency() + "Hz");
			}
			Display.setDisplayMode(modes[75]);
			Display.setFullscreen(true);
			Display.create();
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0,Display.getWidth(),0,Display.getHeight(),-1,1);
			glMatrixMode(GL_MODELVIEW);
			glClearColor(0,0,0,1);
			glDisable(GL_DEPTH_TEST);
			glClear(GL_COLOR_BUFFER_BIT);//GL init
			glColor3f(0.25f, 0.75f, 0.5f);
			glLoadIdentity();
			Display.setVSyncEnabled(true); 
		} catch (LWJGLException e) {
			
			e.printStackTrace();
			
		}		
	}
	/**
	 * Function used for post-game cleanup. Simply destroys the display
	 */
	private static void clearDisplay() {
		
		Display.destroy();
		
	}

	

}
