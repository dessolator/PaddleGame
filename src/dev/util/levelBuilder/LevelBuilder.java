package dev.util.levelBuilder;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.io.File;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import dev.game.project.engine.Engine;
import dev.game.project.engine.GamePhysics;
import dev.game.project.gameMechanics.Level;
import dev.game.project.gameObjects.Brick;



public class LevelBuilder {

	
	
	private static Level myLevel;
	private static int selHP=1;
	private static float selCoordX;
	private static float selCoordY;
	private static float selDimX;
	private static float selDimY;
	private static boolean selDB=false;
	private static boolean terminate=false;
	private static long keyTriggered=0l;
        /**
         * @param args
         */
    public static void main(String[] args) {
        
    	Engine.init();
    	myLevel=new Level(0);
    	selDimX=Display.getWidth()/20;
    	selDimY=Display.getHeight()/30;
    	selCoordX=Mouse.getX();
    	selCoordY=Mouse.getY();
    	startLevelBuilder();
    	Engine.kill();
    	// TODO Auto-generated method stub
        //load/new level
        //if new level read level name
        //start gui for level builder
        //display selected block
        //if mouse click and no collision, place new block at mouse location
        //save

    }
    
    
    public static void startLevelBuilder() {
		while((!Display.isCloseRequested())&&!terminate) {//if ESCAPE hasn't been hit and the display hasn't been closed otherwise
			glClear(GL_COLOR_BUFFER_BIT);//for each frame clear the screen
			processInput();//read player input
			myLevel.render();//then render it
			Mouse.setGrabbed(false);
			renderSelection();
			Display.sync(60);//force the framerate to 60 FPS or thereabouts
			Display.update();//refresh the display
		}
		
	}

        
	private static void renderSelection() {
		Brick temp=new Brick(selCoordX, selCoordY, selDimX, selDimY, selHP, selDB);
		temp.render();		
	}


	private static void processInput() {
		if(Mouse.isButtonDown(0)){
			deployBlock(new Brick(selCoordX, selCoordY, selDimX, selDimY, selHP, selDB));
		}

		selCoordX=Mouse.getX();
		selCoordY=Mouse.getY();
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			if((((long)System.nanoTime()-keyTriggered)>150000000)){//if time passed
				keyTriggered=System.nanoTime();
				terminate=true;//turn on voodooMode
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_ADD)){
			if((((long)System.nanoTime()-keyTriggered)>150000000)){//if time passed
				keyTriggered=System.nanoTime();
				selHP=selHP==4?1:++selHP;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT)){
			if((((long)System.nanoTime()-keyTriggered)>150000000)){//if time passed
				keyTriggered=System.nanoTime();
				selHP=selHP==1?4:--selHP;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			if((((long)System.nanoTime()-keyTriggered)>150000000)){//if time passed
				keyTriggered=System.nanoTime();
				selDimX=selDimX>=(Display.getWidth()/5)?selDimX:(selDimX+10);
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			if((((long)System.nanoTime()-keyTriggered)>150000000)){//if time passed
				keyTriggered=System.nanoTime();
				selDimX=selDimX<=(Display.getWidth()/50)?selDimX:(selDimX-10);
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			if((((long)System.nanoTime()-keyTriggered)>150000000)){//if time passed
				keyTriggered=System.nanoTime();
				myLevel.save(new File("level1.xml"));
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_L)){
			if((((long)System.nanoTime()-keyTriggered)>150000000)){//if time passed
				keyTriggered=System.nanoTime();
				myLevel.load(new File("level1.xml"));
			}
		}
		
		
	}


	private static void deployBlock(Brick brick) {
		boolean collision=false;
		for(int i=0;i<myLevel.getBricks().size()&&!collision;i++){
			if(GamePhysics.hit(myLevel.getBricks().get(i), brick)){
				collision=true;
			}
		}
		if(!collision&&selCoordY>(float)Display.getHeight()/4.5){
			myLevel.getBricks().add(brick);
		}
		
	}
        
        
        
        
        //display
        //use level class from game
        //just don't call update
}