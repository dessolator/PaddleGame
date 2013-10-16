package dev.game.project;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class PaddleGame {
	static boolean voodooMode=false;//@credit Jovan Davidovic\
	/*
	 * Temporary brick generation variables
	 */
	//======================================================
	static int num = 17;//variable used for brick generation
	static float coordx = Display.getWidth()/16;//first brick coordinate
	static float coordy = (Display.getHeight()*(5.5f))/6;//first brick coordinate
	//======================================================
	
	static ArrayList<Collidable> gameBlocks = new ArrayList<Collidable>();//List of all blocks used by the game

	private static PlayerPaddle myPaddle= new PlayerPaddle(Display.getWidth()/2,Display.getHeight()/12,Display.getWidth()/8,Display.getHeight()/30);//create player paddle
	private static Ball myBall = new Ball(Display.getWidth()/2,Display.getHeight()/6,Display.getHeight()/60);//create the ball
	
	private static boolean terminate=false;//variable used to check if the user hit ESCAPE
	static{
		/*
		 * Add Boundaries to blocks to check for collisions
		 */
		gameBlocks.add(new Boundary(Display.getWidth()+0.5f,Display.getHeight()/2,1,Display.getHeight(),Sides.RIGHT));
		gameBlocks.add(new Boundary(Display.getWidth()/2,Display.getHeight()+0.5f,Display.getWidth(),1,Sides.TOP));
		gameBlocks.add(new Boundary(-0.5f,Display.getHeight()/2,1,Display.getHeight(),Sides.LEFT));
		gameBlocks.add(new Boundary(Display.getWidth()/2,0-0.5f,Display.getWidth(),1,Sides.BOTTOM));
		gameBlocks.add(myPaddle);
		/*
		 * Temporary brick generating loop
		 */
		for (int j = 0; j<4; j++) {//change row to add bricks to
			for (int i = 0; i < num; i++) {//add bricks to row
				gameBlocks.add(new Brick(coordx,coordy,Display.getWidth()/20,Display.getHeight()/30));
				coordx+=(int)Display.getWidth()/(18.6f);
			}
			coordy-=(int)Display.getHeight()/(27.27f);//reset brick coordinates
			coordx-=num*(int)Display.getWidth()/(18.6f);
		}
		
	}
	
	/**
	 * The main game loop takes care of pretty much everything,
	 * from user input to collisions.
	 * @param Parameter for voodooMode. If set to true, the game objects will render in a different color each frame.
	 */
	public static void startGame(boolean voodoo) {
		voodooMode=voodoo;//read voodooMode param
		while((!Display.isCloseRequested())&&!terminate) {//if ESCAPE hasn't been hit and the display hasn't been closed otherwise
			glClear(GL_COLOR_BUFFER_BIT);//for each frame clear the screen
			displayFPS();//print framerate for debug purposes
			processInput();//read player input
			myBall.update();//update ball state
			myBall.render();//draw the ball
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
		for (int i=0; i<gameBlocks.size();i++) {//for each gameBlock
			
			Collidable o=gameBlocks.get(i);//store current element in variable to avoid parsing array
			o.update();//update the state of all gameBlocks (currently does nothing
			o.render();//draw the bricks and paddle		
			
			if(GamePhysics.hit(myBall, o)) {//if a collision did occur
				o.collided(myBall);//trigger collision function
				if(o.destroyed){//check if the object was destroyed
					gameBlocks.remove(i);//if so remove it
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
			myPaddle.move(-1);//move left
			return;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)||Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){//else if right was pressed
			myPaddle.move(1);//move right
			return;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			terminate=true;//set terminate flag
			return;
		}
		
	}
	/**
	 * Getter for the paddle object.
	 * @return Player paddle.
	 */
	public static Collidable getPaddle() {
		return myPaddle;
	}
	
}
