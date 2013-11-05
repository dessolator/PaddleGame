package dev.game.project.gameObjects;

import java.io.File;
import static org.lwjgl.opengl.GL11.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import dev.game.project.engine.Collidable;
import dev.game.project.engine.DrawObject;
import dev.game.project.gameMechanics.PaddleGame;
import dev.game.project.gameObjects.bonuses.Bonus;

public class Brick extends GameObject implements Collidable {
	private int hitPoints;//field used to keep track of the block's h.
	private boolean droppsBonus;//field used to check if the block drops a bonus on destruction.\
	private static Texture [] hpTextures;
	private boolean destroyed=false;
	static{
		try {
			hpTextures=new Texture[4];
			hpTextures[3]=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/veryHeavyBrick.png")));
			hpTextures[2]=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/heavyBrick.png")));	
			hpTextures[1]=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/mediumBrick.png")));
			hpTextures[0]=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/lightBrick.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * THe Constructor for bircks.
	 * @param cordX	The x coordinate of the brick's center.
	 * @param cordY The y coordinate of the brick's center.
	 * @param dimX The x dimension of the brick.
	 * @param dimY The y dimension of the brick.
	 * @param hitPoints The brick's hp.
	 * @param droppsBonus Field indicating if the brick dropps a bonus or not.
	 */
	public Brick(float cordX, float cordY, float dimX, float dimY,int hitPoints,boolean droppsBonus) {
		this.setCoordX(cordX);//Set the x coordinate to the passed value.
		this.setCoordY(cordY);//Set the y coordinate to the passed value.
		this.setDimX(dimX);//Set the x dimension to the passed value.
		this.setDimY(dimY);//Set the y dimension to the passed value.
		this.hitPoints=hitPoints;//Set the hit points to the passed value.
		this.droppsBonus=droppsBonus;//Set the bonus drop to the passed value.
		this.setTexture(hpTextures[hitPoints-1]);
	}
	/* (non-Javadoc)
	 * @see dev.game.project.GameObject#update()
	 */
	@Override 
	public void update() {//bricks do not update, but if a regenerating brick were to be implemented...
	}

	/* (non-Javadoc)
	 * @see dev.game.project.Collidable#collided(dev.game.project.GameObject)
	 */
	@Override
	public void collided(GameObject o) {
		if(!((Ball)o).isFlipped()){//if the ball hasn't been flipped this frame,
			int tempHP=hitPoints;//store current hp.
			hitPoints-=((Ball)o).getDamageThisFrame();//decrease hp by ball damage.
			((Ball)o).setDamageThisFrame(((Ball)o).getDamageThisFrame()-tempHP);//decrease ball damage left this frame.
			if(((Ball)o).getDamageThisFrame()<=0){//if the ball used up all it's damage,
			((Ball)o).setFlipped(true);//flip it.
			/*
			 * For the following if statement please consult the bounce logic appendix (bounceLogic.md)
			 */
			if(((o.getCoordX()>=(getCoordX()-(getDimX()/2+o.getDimX()/2)))&&(o.getCoordX()<=(getCoordX()+(getDimX()/2+o.getDimX()/2))))&&
			(((o.getCoordY()<=(getCoordY()-getDimY()/2))&&(o.getCoordY()>=(getCoordY()-(getDimY()/2+o.getDimX()/2)))) ||
			((o.getCoordY()<=(getCoordY()+(getDimY()/2+o.getDimX()/2)))&&(o.getCoordY()>=(getCoordY()+getDimY()/2)))))
			{
				((Ball)o).setDirection(((Ball)o).getDirection() * -1);//if the ball hits something, bounce it back
				
			}
			else{
				((Ball)o).setSpeedX(((Ball)o).getSpeedX() * -1);
			}}
			if(hitPoints<=0){//if the ball's hitpoints are 0 or below it has been destroyed,
				setDestroyed(true);//mark it as destroyed.
				if(droppsBonus){//if the brick was supposed to drop a bonus,
					Bonus.drop(this);//drop a bonus.
				}
			}
			else{
				setTexture(hpTextures[hitPoints-1]);
			}
		}
		
	}
	/* (non-Javadoc)
	 * @see dev.game.project.GameObject#render()
	 */
	@Override
	public void render() {
		if(!PaddleGame.isDrawTextures()){
			if(!PaddleGame.isVoodooMode()){
				switch(hitPoints){
				case 1:
					glColor3f(1f, 1f, 0f);//set drawing color to yellow.
					break;
				case 2:
					glColor3f(1f, 0.25f, 0f);//set drawing color to orange.
					break;
				case 3:
					glColor3f(0.25f, 1f, 0.25f);//set drawing color to green.			
					break;
				default:
					glColor3f(0.25f, 0.75f, 0.5f);//set drawing color to cyan.
					break;
					
				}
			}
			DrawObject.drawColoredRect(getCoordX(), getCoordY(), getDimX(), getDimY());//draw the brick	
		}else
		DrawObject.draw(this);
	}
	/**
	 * @return the destroyed
	 */
	public boolean isDestroyed() {
		return destroyed;
	}
	/**
	 * @param destroyed the destroyed to set
	 */
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
	/**
	 * @return the hitPoints
	 */
	public int getHitPoints() {
		return hitPoints;
	}
	/**
	 * @param hitPoints the hitPoints to set
	 */
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}
	/**
	 * @return the droppsBonus
	 */
	public boolean isDroppsBonus() {
		return droppsBonus;
	}
	/**
	 * @param droppsBonus the droppsBonus to set
	 */
	public void setDroppsBonus(boolean droppsBonus) {
		this.droppsBonus = droppsBonus;
	}


}
