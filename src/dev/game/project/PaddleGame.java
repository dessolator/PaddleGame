package dev.game.project;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class PaddleGame {
	static boolean voodooMode=false;
	static int num = 16;//variable used for brick generation
	static float coordx = 50;//first brick coordinate
	static float coordy = 500;//first brick coordinate
	static ArrayList<Collidable> gameBlocks = new ArrayList<Collidable>();//List of all blocks used by the game
	private static long frameStart=0;
	private static int frames;
	public static PlayerPaddle myPaddle= new PlayerPaddle(Display.getWidth()/2,50,100,20);//create player paddle
	private static Ball myBall = new Ball(Display.getWidth()/2,100,10);//create the ball
	private static Boundary left=new Boundary(-0.5f,Display.getHeight()/2,1,Display.getHeight(),Sides.LEFT);//add left boundary
	private static Boundary right=new Boundary(Display.getWidth()-0.5f,Display.getHeight()/2,1,Display.getHeight(),Sides.RIGHT);//add right boundary
	private static Boundary bottom =new Boundary(Display.getWidth()/2,0,Display.getWidth(),1,Sides.BOTTOM);//add bottom boundary
	private static Boundary top=new Boundary(Display.getWidth()/2,Display.getHeight(),Display.getWidth(),1,Sides.TOP);//add top boundary
	static{
		/*
		 * Add Boundaries to blocks to check for collisions
		 */
		gameBlocks.add(right);
		gameBlocks.add(top);
		gameBlocks.add(left);
		gameBlocks.add(bottom);
		gameBlocks.add(myPaddle);
		/*
		 * Brick generating loop
		 */
		for (int j = 0; j<4; j++) {
			for (int i = 0; i < num; i++) {
				gameBlocks.add(new Brick(coordx,coordy,40,20));
				coordx+=40;
				coordx+=3;
			}
			coordy-=22;
			coordx-=16*43;
		}
		
	}
	
	/*
	 * TODO
	 * scale element sizes to resolution,
	 * bugchecking.
	 */
	/**
	 * The main game loop takes care of pretty much everything,
	 * from user input to collisions.
	 */
	public static void startGame(boolean voodoo) {
		voodooMode=voodoo;
		while(!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);//for each frame clear the screen
			displayFPS();
			processInput();//read player input
			myBall.update();//draw the ball
			collisionPhysics();
			Display.sync(60);
			Display.update();//refresh the display
		}
		
	}
	private static void displayFPS() {
		long currentTime=System.nanoTime();
		if(currentTime-frameStart>=1000000000){
			System.out.println(frames);
			frames=1;
			frameStart=currentTime;
			return;
		}
		frames++;
		
	}
	private static void collisionPhysics() {
		for (int i=0; i<gameBlocks.size();i++) {
			Collidable o=gameBlocks.get(i);//store current element in var to avoid parsing array
			if(o.destroyed){
				gameBlocks.remove(i);
			}
			else
				o.update();//draw the bricks and paddle
			
			if(GamePhysics.hit(myBall, o)) {//if a collision did occur
				o.collided(myBall);
				
				
			}
		}
		
	}
	/**
	 * Function used to evaluate user input and move the paddle accordingly
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
		
	}
	
}
