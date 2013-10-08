package dev.game.project;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glLoadIdentity;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class PaddleGame {
	
	ArrayList<GameObject> gameBlocks = new ArrayList<GameObject>();//List of all blocks used by the game
	
	PlayerPaddle myPaddle= new PlayerPaddle(Display.getWidth()/2,50,100,20);//create player paddle
	Ball myBall = new Ball(Display.getWidth()/2,100,10);//create the ball
	Boundary left=new Boundary(0,Display.getHeight()/2,1,Display.getHeight());//add left boundary
	Boundary right=new Boundary(Display.getWidth(),Display.getHeight()/2,1,Display.getHeight());//add right boundary
	Boundary bottom =new Boundary(Display.getWidth()/2,0,Display.getWidth(),1);//add bottom boundary
	Boundary top=new Boundary(Display.getWidth()/2,Display.getHeight(),Display.getWidth(),1);//add top boundary
	
	
	/*
	 * TODO scale bricks to resolution,
	 * left boundary problem with high resolution,
	 * add a switch case,
	 * scale element sizes to resolution,
	 * fix ball squish bug (the paddle can push the ball through the boundary),
	 * bugchecking.
	 */
	public  void startGame() {
		int num = 16;//variable used for brick generation
		float coordx = 50;//first brick coordinate
		float coordy = 500;//first brick coordinate
		
		glClear(GL_COLOR_BUFFER_BIT);//GL init
		glColor3f(0.25f, 0.75f, 0.5f);
		glLoadIdentity();
		
		/*
		 * Add Boundaries to blocks to check for collisions
		 */
		gameBlocks.add(right);
		gameBlocks.add(top);		
		gameBlocks.add(bottom);
		gameBlocks.add(myPaddle);
		
		/*
		 * Brick generating loop
		 */
		for (int j = 0; j<4; j++) {
			for (int i = 0; i < num; i++) {
				gameBlocks.add(new Brick(coordx,coordy,40,20));
				coordx1+=40;
				coordx1+=3;
			}
			coordy1-=22;
			coordx1-=16*43;
		}
		/*
		 * jebeno ruzno.... treba mu jedan switch case... ali ja vise nemam zivota
		 */
		while(!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);//for each frame clear the screen
			
			processInput();//read player input
			myBall.update();//draw the ball
			
			/*
			 * Collision detection loop
			 */
			for (int i=0; i<gameBlocks.size();i++) {
				gameBlocks.get(i).update();//draw the bricks and paddle
				if(GamePhysics.hit(myBall, gameBlocks.get(i))) {//if a collision did occur
					if(gameBlocks.get(i)==left || gameBlocks.get(i)==right){//with either of the sides
						myBall.speedX*=-1;//bounce the ball off
					}else{
						if(gameBlocks.get(i)==bottom){//if the collision occured with the bottom boundary reset the ball
							//decrement player lives
							myBall.coordX=myPaddle.coordX;
							myBall.coordY=myPaddle.coordY+myPaddle.dimY/2+myBall.radius;
							myBall.speedX=0;
							myBall.speedY=0.1f;
						}else
							if(gameBlocks.get(i)==top){//if the collision was with the top boundary
								myBall.speedY*=-1;// bounce the ball back down
							}else{
					if(gameBlocks.get(i)==myPaddle){//if the collision was with the player paddle
					myBall.speedY*=-1;//bounce the ball back
					myBall.speedX+=(myBall.coordX-gameBlocks.get(i).coordX)*0.01;//taking the angle into account
					if(myBall.speedX>Ball.MAX_SPEED){//make sure ball speed doesn't exceed max
						myBall.speedX=Ball.MAX_SPEED;
					}
					if(myBall.speedX<-Ball.MAX_SPEED){
						myBall.speedX=-Ball.MAX_SPEED;
					}
					
					}
					
					if(gameBlocks.get(i).isBrick()){//if collision happened with a brick
						myBall.speedY*=-1;//if the ball hits something, bounce it back
						gameBlocks.remove(i);//destroy the brick
					}
					}}
				}
			}
			Display.update();//refresh the display
		}
		
	}

	private  void processInput() {
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
