package dev.game.project;

import static org.lwjgl.opengl.GL11.glColor3f;

public class Brick extends Collidable {
	private int hitPoints;
	private boolean droppsBonus;
	
	public Brick(float cordX, float cordY, float dimX, float dimY,int hitPoints,boolean droppsBonus) {
		this.coordX = cordX;
		this.coordY = cordY;
		this.dimX = dimX;
		this.dimY = dimY;
		this.hitPoints=hitPoints;
		this.droppsBonus=droppsBonus;
	}
	@Override 
	public void update() {
	}

	@Override
	public void collided(GameObject o) {
		if(!((Ball)o).isFlipped()){
			int tempHP=hitPoints;
			hitPoints-=((Ball)o).getDamageThisFrame();
			((Ball)o).setDamageThisFrame(((Ball)o).getDamageThisFrame()-tempHP);
			if(((Ball)o).getDamageThisFrame()<=0){
			((Ball)o).setFlipped(true);
			if(((o.coordX>=(coordX-(dimX/2+o.dimX/2)))&&(o.coordX<=(coordX+(dimX/2+o.dimX/2))))&&
			(((o.coordY<=(coordY-dimY/2))&&(o.coordY>=(coordY-(dimY/2+o.dimX/2)))) ||
			((o.coordY<=(coordY+(dimY/2+o.dimX/2)))&&(o.coordY>=(coordY+dimY/2)))))
			{
				((Ball)o).setSpeedY(((Ball)o).getSpeedY() * -1);//if the ball hits something, bounce it back
				
			}
			else{
				((Ball)o).setSpeedX(((Ball)o).getSpeedX() * -1);
			}}
			if(hitPoints<=0){
				destroyed=true;
				if(droppsBonus){
					Bonus.drop(this);
				}
			}
		}
		
	}
	@Override
	public void render() {
		if(!PaddleGame.isVoodooMode()){
			switch(hitPoints){
			case 1:
				glColor3f(1f, 1f, 0f);//set drawing color to yellow
				break;
			case 2:
				glColor3f(1f, 0.25f, 0f);//set drawing color to orange
				break;
			case 3:
				glColor3f(0.25f, 1f, 0.25f);//set drawing color to green				
				break;
			default:
				glColor3f(0.25f, 0.75f, 0.5f);//set drawing color to cyan
				break;
				
			}
		}
		DrawObject.drawRect(coordX, coordY, dimX, dimY);		
	}


}
