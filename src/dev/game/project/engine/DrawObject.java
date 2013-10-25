package dev.game.project.engine;
import static org.lwjgl.opengl.GL11.*;
import dev.game.project.gameMechanics.PaddleGame;


public class DrawObject {
	private static final int CIRCLE_ITER=8;//constant used to keep track of the number of iterations for circle drawing	
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
		glColor3f(1,1,1);//set color to random.
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
	public static void drawColoredRect(float x, float y, float dimX, float dimY){
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
	public static void drawcoloredCirclef(float x, float y, float radius) {
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
