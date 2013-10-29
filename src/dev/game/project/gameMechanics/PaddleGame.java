package dev.game.project.gameMechanics;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import dev.game.project.engine.Drawable;
import dev.game.project.menus.MainMenu;

public class PaddleGame {
	private static boolean voodooMode=false;//@credit Jovan Davidovic\
	private static long voodooTriggered=0l;//field uset to keep track of when voodooMode was last triggered
	static Level myLevel;//Level field containing all the game objects
	private static MainMenu myMainMenu;
	private static boolean terminate=false;//variable used to check if the user hit ESCAPE
	private static int currentLevel=1;
	static{
		
		myLevel=new Level(currentLevel);
		myMainMenu=new MainMenu();
		
	}
	
	/**
	 * The main game loop takes care of pretty much everything,
	 * from user input to collisions.
	 * @param voodoo Parameter for voodooMode. If set to true, the game objects will render in a different color each frame.
	 */
	public static void startGame(boolean voodoo) {
		setVoodooMode(voodoo);//read voodooMode param
		while((!Display.isCloseRequested())&&!isTerminate()) {//if ESCAPE hasn't been hit and the display hasn't been closed otherwise
			glClear(GL_COLOR_BUFFER_BIT);//for each frame clear the screen
			//displayFPS();//print framerate for debug purposes
			processInput();//read player input
			getUpdate().update();//update the level
			getDraw().render();//then render it
			Display.sync(60);//force the framerate to 60 FPS or thereabouts
			Display.update();//refresh the display
		}
		
	}
	
	private static int currentGameState=1;
	static Drawable getDraw(){
		switch(getCurrentGameState()){
			case 0:
				return myLevel;
			case 1:
				return myMainMenu;
			default:
				return null;
		}
	}
	static Updateable getUpdate(){
		switch(getCurrentGameState()){
		case 0:
			return myLevel;
		case 1:
			return myMainMenu;
		default:
			return null;
		}
	}
	private static long frameStart=0;//Variable used to keep track of the frame start time
	private static int frames=1;//number of frames in the current second
	/**
	 * Function used to calculate and display the framerate.
	 */
	@SuppressWarnings("unused")
	private static void displayFPS() {
		long currentTime=System.nanoTime();//read current time
		if(currentTime-frameStart>=1000000000){//if more than a second has passed
			System.out.println(frames);//print framerate
			frames=1;//reset frames
			frameStart=currentTime;//set start time of new frame
			return;
		}
		frames++;//if not increment number of frames
		
	}
	/**
	 * Function used to evaluate user input and move the paddle accordingly.
	 */
	private static void processInput() {
		if(Keyboard.isKeyDown(Keyboard.KEY_A)||Keyboard.isKeyDown(Keyboard.KEY_LEFT)){//if left was pressed
			myLevel.movePaddle(-1);//move left
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)||Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){//else if right was pressed
			myLevel.movePaddle(1);//move right
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			goBack();//set terminate flag
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_V)){
			if(!isVoodooMode()&&(((long)System.nanoTime()-voodooTriggered)>250000000)){//if voodooMode is off and the break time passed
				voodooTriggered=System.nanoTime();
				setVoodooMode(true);//turn on voodooMode
			}
			else{
				if(isVoodooMode()&&(((long)System.nanoTime()-voodooTriggered)>250000000)){//if voodooMode is on and break time passed
					voodooTriggered=System.nanoTime();
					setVoodooMode(false);//turn voodooMode off
				}
			}
		}
		
	}
	private static void goBack() {
		//switch(currentGameState){
		//case:
		//}
		currentGameState=1;		
	}
	/**
	 * Getter for the paddle object.
	 * @return Player paddle.
	 */

	public static Level getLevel() {
		return myLevel;
	}
	/**
	 * Getter for voodooMode.
	 * @return is voodooMode turned on.
	 */
	public static boolean isVoodooMode() {
		return voodooMode;
	}
	/**
	 * Setter for voodooMode.
	 * @param voodooMode  value to set.
	 */
	public static void setVoodooMode(boolean voodooMode) {
		PaddleGame.voodooMode = voodooMode;
	}
	/**
	 * @return the terminate
	 */
	public static boolean isTerminate() {
		return terminate;
	}
	/**
	 * @param terminate the terminate to set
	 */
	public static void setTerminate(boolean terminate) {
		PaddleGame.terminate = terminate;
	}
	/**
	 * @return the currentGameState
	 */
	public static int getCurrentGameState() {
		return currentGameState;
	}
	/**
	 * @param currentGameState the currentGameState to set
	 */
	public static void setCurrentGameState(int currentGameState) {
		PaddleGame.currentGameState = currentGameState;
	}
	
}
