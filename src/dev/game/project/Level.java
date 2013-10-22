package dev.game.project;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

public class Level {
	ArrayList<Collidable> blocks;
	ArrayList<Bonus> bonuses;
	int num = 17;//variable used for brick generation
	float coordx = Display.getWidth()/16;//first brick coordinate
	float coordy = (Display.getHeight()*(5.5f))/6;//first brick coordinate
	private PlayerPaddle myPaddle= new PlayerPaddle(Display.getWidth()/2,Display.getHeight()/12,Display.getWidth()/8,Display.getHeight()/30);//create player paddle
	private Ball myBall = new Ball(Display.getWidth()/2,Display.getHeight()/6,Display.getHeight()/60);//create the ball
	
	//public void save(){}
	
	//public static void load(String saveLocation){}
	
	public Level(int levelNumber) {
		blocks=new ArrayList<Collidable>();
		bonuses=new ArrayList<Bonus>();
		for (int j = 0; j<4; j++) {//change row to add bricks to
			for (int i = 0; i < num; i++) {//add bricks to row
				blocks.add(new Brick(coordx,coordy,Display.getWidth()/20,Display.getHeight()/30,(j+1),(Math.random()<0.5?true:false)));
				coordx+=(int)Display.getWidth()/(18.6f);
			}
			coordy-=(int)Display.getHeight()/(27.27f);//reset brick coordinates
			coordx-=num*(int)Display.getWidth()/(18.6f);
		}
		
		blocks.add(new Boundary(Display.getWidth()+0.5f,Display.getHeight()/2,1,Display.getHeight(),Sides.RIGHT));
		blocks.add(new Boundary(Display.getWidth()/2,Display.getHeight()+0.5f,Display.getWidth(),1,Sides.TOP));
		blocks.add(new Boundary(-0.5f,Display.getHeight()/2,1,Display.getHeight(),Sides.LEFT));
		blocks.add(new Boundary(Display.getWidth()/2,0-0.5f,Display.getWidth(),1,Sides.BOTTOM));
		blocks.add(myPaddle);
		
	}



	public void addBonus(Bonus bonus) {
		bonuses.add(bonus);
		
	}

	public void removeBonus(Bonus bonus) {
		bonuses.remove(bonus);
		
	}


	public ArrayList<Collidable> getBlocks() {
		return blocks;
	}



	public void movePaddle(int i) {
		myPaddle.move(i);
		
	}



	public void update() {
		myBall.update();
		Timer.update();
		for (int i=0; i<bonuses.size();i++) {
			bonuses.get(i).update();
		}
		
		
	}



	public void render() {
		myBall.render();
		for (int i=0; i<bonuses.size();i++) {
			bonuses.get(i).render();
		}
		
	}



	public Ball getBall() {
		return myBall;
	}



	public PlayerPaddle getPaddle() {
		return myPaddle;
	}



	public void spawnBall() {
		// TODO Auto-generated method stub
		
	}

}
