package dev.game.project.gameObjects;

import static org.lwjgl.opengl.GL11.glColor3f;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import dev.game.project.engine.Collidable;
import dev.game.project.engine.DrawObject;
import dev.game.project.engine.GameObject;
import dev.game.project.gameMechanics.PaddleGame;
import dev.game.project.gameObjects.bonuses.Bonus;

public class Brick extends Collidable {
	private int hitPoints;//field used to keep track of the block's h.
	private boolean droppsBonus;//field used to check if the block drops a bonus on destruction.\
	Texture [] hpTextures;
	
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
		hpTextures=new Texture[4];
		this.setCoordX(cordX);//Set the x coordinate to the passed value.
		this.setCoordY(cordY);//Set the y coordinate to the passed value.
		this.setDimX(dimX);//Set the x dimension to the passed value.
		this.setDimY(dimY);//Set the y dimension to the passed value.
		this.hitPoints=hitPoints;//Set the hit points to the passed value.
		this.droppsBonus=droppsBonus;//Set the bonus drop to the passed value.
		try {
			switch(hitPoints){
				case 4:
					hpTextures[0]=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/veryHeavyBrick.png")));
					break;
				case 3:
					hpTextures[1]=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/heavyBrick.png")));
					break;
				case 2:
					hpTextures[2]=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/mediumBrick.png")));
					break;
				case 1:
					hpTextures[3]=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/lightBrick.png")));
					break;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				destroyed=true;//mark it as destroyed.
				if(droppsBonus){//if the brick was supposed to drop a bonus,
					Bonus.drop(this);//drop a bonus.
				}
			}
			else{
					switch(hitPoints){
						case 4:
							setTexture(hpTextures[0]);
							break;
						case 3:
							setTexture(hpTextures[1]);
							break;
						case 2:
							setTexture(hpTextures[2]);
							break;
						case 1:
							setTexture(hpTextures[3]);
							break;
					}
			}
		}
		
	}
	/* (non-Javadoc)
	 * @see dev.game.project.GameObject#render()
	 */
	@Override
	public void render() {
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
		//DrawObject.draw(this);
	}


}
