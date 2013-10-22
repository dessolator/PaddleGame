package dev.game.project;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class PaddleGame {
	private static boolean voodooMode=false;//@credit Jovan Davidovic\
	private static long voodooTriggered=0l;
	
	private static Level myLevel;
	
	private static boolean terminate=false;//variable used to check if the user hit ESCAPE
	static{
		/*
		 * Add Boundaries to blocks to check for collisions
		 */
		
		myLevel=new Level(1);

		
	}
	
	/**
	 * The main game loop takes care of pretty much everything,
	 * from user input to collisions.
	 * @param Parameter for voodooMode. If set to true, the game objects will render in a different color each frame.
	 */
	public static void startGame(boolean voodoo) {
		setVoodooMode(voodoo);//read voodooMode param
		while((!Display.isCloseRequested())&&!terminate) {//if ESCAPE hasn't been hit and the display hasn't been closed otherwise
			glClear(GL_COLOR_BUFFER_BIT);//for each frame clear the screen
			//displayFPS();//print framerate for debug purposes
			processInput();//read player input
			myLevel.update();
			myLevel.render();
			collisionPhysics();//do the collision physics and brick drawing
			Display.sync(60);//force the framerate to 60 FPS or thereabouts
			Display.update();//refresh the display
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
	 * Function used to check for collisions and draw blocks.
	 */
	private static void collisionPhysics() {
		for (int i=0; i<myLevel.getBlocks().size();i++) {//for each gameBlock
			
			Collidable o=myLevel.getBlocks().get(i);//store current element in variable to avoid parsing array
			o.update();//update the state of all gameBlocks (currently does nothing
			o.render();//draw the bricks and paddle		
			
			if(GamePhysics.hit(myLevel.getBall(), o)) {//if a collision did occur
				o.collided(myLevel.getBall());//trigger collision function
				if(o.destroyed){//check if the object was destroyed
					myLevel.getBlocks().remove(i);//if so remove it
					i--;//and correct iterator
				}
				
				
			}
		}
		
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
			terminate=true;//set terminate flag
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_V)){
			if(!isVoodooMode()&&(((long)System.nanoTime()-voodooTriggered)>250000000)){
				voodooTriggered=System.nanoTime();
				setVoodooMode(true);
			}
			else{
				if(isVoodooMode()&&(((long)System.nanoTime()-voodooTriggered)>250000000)){
					voodooTriggered=System.nanoTime();
					setVoodooMode(false);
				}
			}
		}
		
	}
	/**
	 * Getter for the paddle object.
	 * @return Player paddle.
	 */

	public static Level getLevel() {
		return myLevel;
	}
	/**
	 * @return the voodooMode
	 */
	public static boolean isVoodooMode() {
		return voodooMode;
	}
	/**
	 * @param voodooMode the voodooMode to set
	 */
	public static void setVoodooMode(boolean voodooMode) {
		PaddleGame.voodooMode = voodooMode;
	}
	
}
