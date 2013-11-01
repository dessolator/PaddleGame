package dev.game.project.gameMechanics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import dev.game.project.engine.DrawObject;
import dev.game.project.engine.GamePhysics;
import dev.game.project.engine.Drawable;
import dev.game.project.engine.Updateable;
import dev.game.project.gameObjects.Ball;
import dev.game.project.gameObjects.Brick;
import dev.game.project.gameObjects.PlayerPaddle;
import dev.game.project.gameObjects.bonuses.Bonus;
import dev.game.project.gameObjects.bonuses.Timer;
import dev.game.project.gameObjects.boundaries.Boundary;
import dev.game.project.gameObjects.boundaries.LeftBoundary;
import dev.game.project.gameObjects.boundaries.LowerBoundary;
import dev.game.project.gameObjects.boundaries.RightBoundary;
import dev.game.project.gameObjects.boundaries.UpperBoundary;

public class Level implements Drawable, Updateable{
	private ArrayList<Brick> bricks;//arraylist keeping track of bricks.
	private ArrayList<Boundary> boundaries;//arraylist keeping track of boundaries.
	private ArrayList<Bonus> bonuses;//arraylist keeping track of spawned bonuses.
	int num = 17;//variable used for brick generation.
	float coordx = Display.getWidth()/16;//first brick coordinate.
	float coordy = (Display.getHeight()*(5.5f))/6;//first brick coordinate.
	private PlayerPaddle myPaddle;//the player paddle.
	private ArrayList<Ball> myBalls;//array of balls.
	private Texture myBackground;//level background.
	

	/**
	 * The level constructor.
	 * @param levelNumber The number of the level to be created.
	 */
	public Level(int levelNumber) {
		myBackground=getLevelBackground(levelNumber);//allows for different level backgrounds for different levels.
		myBalls=new ArrayList<Ball>();//init balls
		bricks=new ArrayList<Brick>();//init bricks.
		boundaries=new ArrayList<Boundary>();//init boundaries
		bonuses=new ArrayList<Bonus>();//init bonuses.
		
		myPaddle= new PlayerPaddle(
				Display.getWidth()/2,
				Display.getHeight()/12,
				Display.getWidth()/8,
				Display.getHeight()/30);//create player paddle.
		
		myBalls.add(new Ball(
				Display.getWidth()/2,
				Display.getHeight()/6,
				Display.getHeight()/60)
		);//create the ball.
		
		for (int j = 0; j<4; j++) {//change row to add bricks to.
			for (int i = 0; i < num; i++) {//add bricks to row.
				
				bricks.add(new Brick(
						coordx,
						coordy,
						Display.getWidth()/20,
						Display.getHeight()/30,
						(j+1),
						(Math.random()<0.07?true:false))
				);
				
				coordx+=(int)Display.getWidth()/(18.6f);
			}
			coordy-=(int)Display.getHeight()/(27.27f);//reset brick coordinates.
			coordx-=num*(int)Display.getWidth()/(18.6f);
		}
		
		boundaries.add(new RightBoundary(
				Display.getWidth()+0.5f,
				Display.getHeight()/2,
				1,
				Display.getHeight())
		);//add right boundary.
		
		boundaries.add(new UpperBoundary(
				Display.getWidth()/2,
				Display.getHeight()+0.5f,
				Display.getWidth(),
				1)
		);//add top boundary.
		
		boundaries.add(new LeftBoundary(
				-0.5f,
				Display.getHeight()/2,
				1,
				Display.getHeight())
		);//add left boundary.
		
		boundaries.add(new LowerBoundary(
				Display.getWidth()/2,
				0-0.5f,
				Display.getWidth(),
				1)
		);//add bottom boundary.
		
		
	}



	/**
	 * The function used to add background on a per level basis.
	 * @param levelNumber The number of the level whose background is to be returned.
	 * @return The texture of the given level.
	 */
	private static Texture getLevelBackground(int levelNumber) {
		//TODO switch case here
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/levelBackground.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * Function that adds a bonus to the list of spawned bonuses.
	 * @param bonus Bonus to be added.
	 */
	public void addBonus(Bonus bonus) {
		bonuses.add(bonus);
		
	}

	/**
	 * Function used to remove a bonus from the list of spawned bonuses.
	 * @param bonus Bonus to be removed.
	 */
	public void removeBonus(Bonus bonus) {
		bonuses.remove(bonus);
		
	}



	/**
	 * Function used to move the paddle.
	 * @param i The paddle movement direction.
	 */
	public void movePaddle(int i) {
		myPaddle.move(i);
		
	}



	/**
	 * Function used to update the level.
	 */
	public void update() {
		collisionPhysics();
		Timer.update();//update the timer for bonuses.
		myPaddle.update();
		for(Brick b:bricks){
			b.update();//update the bricks.
		}
		for(Ball b:myBalls){
			b.update();//update the ball array.
		}
		for (int i=0; i<bonuses.size();i++) {
			bonuses.get(i).update();//update the bonuses.
		}
		
		
	}



	/**
	 * Function used to render the level.
	 */
	public void render() {
		Mouse.setGrabbed(true);//hide the mouse
		if(PaddleGame.isDrawTextures())
			DrawObject.draw(this);//draw the level background
		myPaddle.render();
		for(Brick b:bricks){
			b.render();//render the bricks.
		}
		for(Ball b:myBalls){
			b.render();//render the ball.
		}
		for (int i=0; i<bonuses.size();i++) {
			bonuses.get(i).render();//render the bonuses.
		}
		
	}



	/**
	 * Getter for the ball.
	 * @return the Ball.
	 */
	public ArrayList<Ball> getBalls() {
		return myBalls;
	}



	/**
	 * Getter for the paddle.
	 * @return the Paddle.
	 */
	public PlayerPaddle getPaddle() {
		return myPaddle;
	}



	/**
	 * Function used to trigger the multi ball bonus.
	 */
	public void spawnBall() {
		Ball temp;
		if(!myBalls.isEmpty()){
			//if there's a ball, create a new one based on it.
			temp=new Ball(
					myBalls.get(0).getCoordX(),
					myBalls.get(0).getCoordY(),//Fixed Bug was CoordX instead of CoordY @credit Houstor - Sejn
					myBalls.get(0).getRadius()
					);
			temp.setDirection(myBalls.get(0).getDirection());
			if(myBalls.get(0).getSpeedX()==0){//if the ball was moving only in y axis... shove it to the right... a bit.
				myBalls.get(0).setSpeedX(2f);
			}
				temp.setSpeedX(-myBalls.get(0).getSpeedX());//mirror the new ball
			
		}
		else//if there wasn't already a ball... spawn a new one with respect to the paddle.
		{
			
			temp=new Ball(
					Display.getWidth()/2,
					Display.getHeight()/6,
					Display.getHeight()/60
					);
			
		}
		getBalls().add(temp);//add the new ball to the array.
	}





	/**
	 * Setter for array of balls.
	 * @param myBalls The array to set.
	 */
	public void setMyBalls(ArrayList<Ball> myBalls) {
		this.myBalls = myBalls;
	}



	/**
	 * Function used to check for collisions and draw blocks.
	 */
	private void collisionPhysics() {
		for (int i=0; i<bricks.size();i++) {//for each brick
			Brick o=bricks.get(i);//store current element in variable to avoid parsing array		
			
			for(int j=0;j<myBalls.size();j++){
				if(GamePhysics.hit(myBalls.get(j), o)) {//if a collision did occur
					o.collided(myBalls.get(j));//trigger collision function
					if(o.isDestroyed()){//check if the object was destroyed
						bricks.remove(i);//if so remove it
						i--;//and correct iterator
					}
					
					
				}
			}
		}
		for(Boundary b:boundaries){
			for(int j=0;j<myBalls.size();j++){
				if(GamePhysics.hit(myBalls.get(j), b)){
					b.collided(myBalls.get(j));
				}
			}
		}
		for(int j=0;j<myBalls.size();j++){
			if(GamePhysics.hit(myBalls.get(j), myPaddle) && myBalls.get(j).getUpdateCount()>10){ //if a collision did occur AND X updates have passed from the last collision
				myBalls.get(j).setUpdateCount(0); //reset number of updates since last collision with paddle
				myPaddle.collided(myBalls.get(j)); //trigger collision function
			}
		}
		
		
	}



	@Override
	public Texture getTexture() {
		return myBackground;
	}



	@Override
	public float getCoordX() {
		return Display.getWidth()/2;
	}



	@Override
	public float getDimX() {
		return Display.getWidth();
	}



	@Override
	public float getCoordY() {
		return Display.getHeight()/2;
	}



	@Override
	public float getDimY() {
		return Display.getHeight();
	}

}
