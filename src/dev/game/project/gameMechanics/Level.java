package dev.game.project.gameMechanics;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import dev.game.project.bonuses.Bonus;
import dev.game.project.bonuses.Timer;
import dev.game.project.boundaries.LeftBoundary;
import dev.game.project.boundaries.LowerBoundary;
import dev.game.project.boundaries.RightBoundary;
import dev.game.project.boundaries.UpperBoundary;
import dev.game.project.gameObjects.Ball;
import dev.game.project.gameObjects.Brick;
import dev.game.project.gameObjects.Collidable;
import dev.game.project.gameObjects.PlayerPaddle;

public class Level {
	ArrayList<Collidable> blocks;//arraylist keeping track of bricks and boundaries.
	ArrayList<Bonus> bonuses;//arraylist keeping track of spawned bonuses.
	int num = 17;//variable used for brick generation.
	float coordx = Display.getWidth()/16;//first brick coordinate.
	float coordy = (Display.getHeight()*(5.5f))/6;//first brick coordinate.
	private PlayerPaddle myPaddle;//the player paddle.
	private ArrayList<Ball> myBalls;//array of balls.
	

	public Level(int levelNumber) {
		setMyBalls(new ArrayList<Ball>());
		blocks=new ArrayList<Collidable>();//init blocks.
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
				
				blocks.add(new Brick(
						coordx,
						coordy,
						Display.getWidth()/20,
						Display.getHeight()/30,
						(j+1),
						(Math.random()<0.05?true:false))
				);
				
				coordx+=(int)Display.getWidth()/(18.6f);
			}
			coordy-=(int)Display.getHeight()/(27.27f);//reset brick coordinates.
			coordx-=num*(int)Display.getWidth()/(18.6f);
		}
		
		blocks.add(new RightBoundary(
				Display.getWidth()+0.5f,
				Display.getHeight()/2,
				1,
				Display.getHeight())
		);//add right boundary.
		
		blocks.add(new UpperBoundary(
				Display.getWidth()/2,
				Display.getHeight()+0.5f,
				Display.getWidth(),
				1)
		);//add top boundary.
		
		blocks.add(new LeftBoundary(
				-0.5f,
				Display.getHeight()/2,
				1,
				Display.getHeight())
		);//add left boundary.
		
		blocks.add(new LowerBoundary(
				Display.getWidth()/2,
				0-0.5f,
				Display.getWidth(),
				1)
		);//add bottom boundary.
		
		blocks.add(myPaddle);
		
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
	 * Getter for arrayList of blocks.
	 * @return Blocks list.
	 */
	public ArrayList<Collidable> getBlocks() {
		return blocks;
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
		for(Ball b:myBalls){
			b.update();//update the ball array.
		}
		Timer.update();//update the timer for bonuses.
		for (int i=0; i<bonuses.size();i++) {
			bonuses.get(i).update();//update the bonuses.
		}
		
		
	}



	/**
	 * Function used to render the level.
	 */
	public void render() {
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
			
			temp=new Ball(
					getBalls().get(0).getCoordX(),
					getBalls().get(0).getCoordX(),
					getBalls().get(0).getRadius()
					);
			
			temp.setSpeedX((getBalls().get(0).getSpeedX())-2f);
			getBalls().get(0).setSpeedX((getBalls().get(0).getSpeedX())+2f);
			
		}
		else
		{
			
			temp=new Ball(
					Display.getWidth()/2,
					Display.getHeight()/6,
					Display.getHeight()/60
					);
			
		}
		getBalls().add(temp);
		
	}





	/**
	 * Setter for array of balls.
	 * @param myBalls The array to set.
	 */
	public void setMyBalls(ArrayList<Ball> myBalls) {
		this.myBalls = myBalls;
	}

}
