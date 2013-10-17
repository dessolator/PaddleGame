package dev.game.project;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

public class Level {
	static int num = 17;//variable used for brick generation
	static float coordx = Display.getWidth()/16;//first brick coordinate
	static float coordy = (Display.getHeight()*(5.5f))/6;//first brick coordinate
	
	//public void save(){}
	
	//public static void load(String saveLocation){}
	
	public static ArrayList<Collidable> startLevel(int levelNumber){
		ArrayList<Collidable> ret=new ArrayList<Collidable>();
		for (int j = 0; j<4; j++) {//change row to add bricks to
			for (int i = 0; i < num; i++) {//add bricks to row
				ret.add(new Brick(coordx,coordy,Display.getWidth()/20,Display.getHeight()/30,(j+1),false));
				coordx+=(int)Display.getWidth()/(18.6f);
			}
			coordy-=(int)Display.getHeight()/(27.27f);//reset brick coordinates
			coordx-=num*(int)Display.getWidth()/(18.6f);
		}		
		return ret;
	}

}
