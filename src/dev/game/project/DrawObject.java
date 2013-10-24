package dev.game.project;
import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class DrawObject {
	private static final int CIRCLE_ITER=8;//constant used to keep track of the number of iterations for circle drawing.
	static Texture veryHeavyBrick=null;
	static Texture heavyBrick=null;
	static Texture mediumBrick=null;
	static Texture lightBrick=null;
	static Texture ballSpeedBonus=null;
	static Texture ballDamageBonus=null;
	static Texture multiBallBonus=null;
	static Texture paddleSpeedBonus=null;
	static Texture paddleInvertBonus=null;
	static Texture paddleWidenBonus=null;
	static Texture paddleNarrowBonus=null;
	static Texture playerPaddle=null;
	static Texture ball=null;
	static{
		try {
			veryHeavyBrick=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/veryHeavyBrick.png")));
			heavyBrick=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/heavyBrick.png")));
			mediumBrick=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/mediumBrick.png")));
			lightBrick=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/lightBrick.png")));
			ballSpeedBonus=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/ballSpeedBonus.png")));
			ballDamageBonus=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/ballDamageBonus.png")));
			multiBallBonus=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/multiBallBonus.png")));
			paddleSpeedBonus=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/paddleSpeedBonus.png")));
			paddleInvertBonus=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/paddleInvertBonus.png")));
			paddleWidenBonus=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/paddleWidenBonus.png")));
			paddleNarrowBonus=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/paddleNarrowBonus.png")));
			playerPaddle=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/playerPaddle.png")));
			ball=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/ball.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Function used to draw a rectangle.
	 * @param x The X coordinate of the center of the rectangle.
	 * @param y The Y coordinate of the center of the rectangle.
	 * @param dimX The X dimension of the rectangle.
	 * @param dimY The Y dimension of the rectangle.
	 */
	public static void drawGameObject(GameObject g){
		g.getTexture().bind();
		glPushMatrix();//create new matrix for manipulation of the given rectangle.
		glTranslatef(g.getCoordX(),g.getCoordY(),0);//set starting point to the coordinates needed.
		glColor3f(0,0,0);//set color to random.
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 0);
			glVertex2f(-g.getDimX()/2, -g.getDimY()/2);
			glTexCoord2f(1, 0);
			glVertex2f(g.getDimX()/2,-g.getDimY()/2);
			glTexCoord2f(1, 1);
			glVertex2f(g.getDimX()/2, g.getDimY()/2);
			glTexCoord2f(0, 1);
			glVertex2f(-g.getDimX()/2, g.getDimY()/2);			
		}
		glEnd();
		glPopMatrix();//when done with object manipulation merge model matrices.
		
	}
	public static void drawRect(float x, float y, float dimX, float dimY){
		glPushMatrix();//create new matrix for manipulation of the given rectangle.
		glTranslatef(x,y,0);//set starting point to the coordinates needed.
		
		if(PaddleGame.isVoodooMode()){//if voodoMode is on,
		float r=(float)Math.random();//randomize red component.
		float g=(float)Math.random();//randomize blue component.
		float b=(float)Math.random();//randomize green component.
		glColor3f(r,g,b);//set color to random.
		}
		
		/*
		 * Draw Rectangle using GL,
		 * values adjusted so that coordinates are now,
		 * at the center of the rectangle.
		 */
		glBegin(GL_QUADS);
		{

			glVertex2f(-dimX/2, -dimY/2);

			glVertex2f(dimX/2,-dimY/2);

			glVertex2f(dimX/2, dimY/2);

			glVertex2f(-dimX/2, dimY/2);			
		}
		glEnd();
		glPopMatrix();//when done with object manipulation merge model matrices.
		
	}
	
	/**
	 * Function used to draw a circle.
	 * @param x The X coordinate of the center of the circle to be drawn.
	 * @param y The y coordinate of the center of the circle to be drawn.
	 * @param radius The radius of the circle to be drawn.
	 */
	public static void drawCirclef(float x, float y, float radius) {
		glPushMatrix();//create new manipulation matrix.
		glTranslatef(x,y,0);//set starting point to the coordinates needed.
		/*
		 * Draw Circle using GL.
		 */
	    glBegin(GL_TRIANGLE_FAN);
	    {
	        for (float angle = 0; angle < CIRCLE_ITER; angle += 0.5) {//draw the circle by iterating triangles around the center.
	            glVertex2d(Math.sin(angle) * radius, Math.cos(angle) * radius);
	        }
	    }    
	    glEnd();
		glPopMatrix();//when manipulation is done merge the model matrices.
    }
}
