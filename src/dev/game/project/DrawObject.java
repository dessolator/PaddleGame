package dev.game.project;
import static org.lwjgl.opengl.GL11.*;

public class DrawObject {
	private static final int CIRCLE_ITER=8;//constant used to keep track of the number of iterations for circle drawing
	/**
	 * Function used to draw a rectangle.
	 * @param x The X coordinate of the center of the rectangle
	 * @param y The Y coordinate of the center of the rectangle
	 * @param dimX The X dimension of the rectangle
	 * @param dimY The Y dimension of the rectangle
	 */
	public static void drawRect(float x, float y, float dimX, float dimY){
		glPushMatrix();//create new matrix for manipulation of the given rectangle
		glTranslatef(x,y,0);//set starting point to the coordinates needed
		/*
		 * Draw Rectangle using GL,
		 * values adjusted so that coordinates are now,
		 * at the center of the rectangle
		 */
		glBegin(GL_QUADS);
		{
			glVertex2f(-dimX/2, -dimY/2);
			glVertex2f(dimX/2,-dimY/2);
			glVertex2f(dimX/2, dimY/2);
			glVertex2f(-dimX/2, dimY/2);			
		}
		glEnd();
		glPopMatrix();//when done with object manipulation merge model matrices
		
	}
	
	/**
	 * Function used to draw a circle
	 * @param x The X coordinate of the center of the circle to be drawn
	 * @param y The y coordinate of the center fo the circle to be drawn
	 * @param radius The radius of the circle to be drawn
	 */
	public static void drawCirclef(float x, float y, float radius) {
		glPushMatrix();//create new manipulation matrix
		glTranslatef(x,y,0);//set starting point to the coordinates needed
		/*
		 * Draw Circle using GL
		 */
	    glBegin(GL_TRIANGLE_FAN);
	    {
	        for (float angle = 0; angle < CIRCLE_ITER; angle += 0.5) {//@revision implemented circleIter constant, 360 iterations is a little too expensive for a circle
	            glVertex2d(Math.sin(angle) * radius, Math.cos(angle) * radius);
	        }
	    }    
	    glEnd();
		glPopMatrix();//when manipulation is done merge the model matrices
    }
}
