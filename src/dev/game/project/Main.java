package dev.game.project;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Main {

	
	/*
	 * TODO TEXTURES
	 * TODO SQUISH BUG REAPPEARED WHEN IMPLEMENTING MULTIBALL
	 * TODO COMMENT BONUS
	 * TODO GAME MENU
	 * TODO LEVEL GENERATION AND PROGRESSION
	 * TODO SCORING
	 */
	
	
	
	
	
	public static void main(String[] args) {
		initEngine();//start the engine.
		PaddleGame.startGame(false);//start the game with voodooMode off.
		killEngine();//kill the engine.
	}
	
	
	
	
	
	
	

	/**
	 * GL initialization function.Sets display mode, creates a display,
	 * clears the projection, sets up the perspective,blacks out the background and disables depth test. 
	 */
	private static void initEngine() {
		try {			
			DisplayMode[] modes = Display.getAvailableDisplayModes();
//			for (int i=0;i<modes.length;i++) {//loop to print out all the possible fullscreen display modes.
//			    DisplayMode current = modes[i];
//			    if(modes[i].isFullscreenCapable())
//			    System.out.println(i+":"+current.getWidth() + "x" + current.getHeight() + "x" +
//			                        current.getBitsPerPixel() + " " + current.getFrequency() + "Hz");
//			}
			
			/*
			 * Display initialization.
			 */
			Display.setDisplayMode(modes[75]);//set to my native resolution.
			Display.setFullscreen(true);//set to fullscreen.
			Display.create();//init the Display object.
			Display.setVSyncEnabled(true);//enable Vsync to avoid visual glitches.
			
			/*
			 * GL initialization.
			 */
			glMatrixMode(GL_PROJECTION);//choose the GL_PROJECTION matrix,
			glLoadIdentity();//and clear it.
			glOrtho(0,Display.getWidth(),0,Display.getHeight(),-1,1);//set the view to the initial plane.
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
			Mouse.setGrabbed(true);//hide mouse pointer.
			
		} catch (LWJGLException e) {
			
			e.printStackTrace();//if GLinit went south, tell me where.
			
		}		
	}
	/**
	 * Function used for post-game cleanup. Simply destroys the display.
	 */
	private static void killEngine() {
		
		Display.destroy();//destroy Display object.
		Keyboard.destroy();//destroy Keyboard object.
		Mouse.destroy();//destroy Mouse object.
		
	}

	

}
