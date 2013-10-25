package dev.game.project.gameMechanics;

import dev.game.project.gameObjects.GameObject;


public class GamePhysics {
	
	
	/**
	 * 
	 * Function used to determine if two game objects have collided.
	 * @param a The first game object to be checked for collision.
	 * @param b The second game object to be checked for collision.
	 * @return Function returns true if the two passed objects have indeed collided.
	 */
	public static boolean hit(GameObject a, GameObject b){
		GameObject left=(a.getCoordX()<b.getCoordX())?a:b;
		GameObject right=(a.getCoordX()<b.getCoordX())?b:a;
		if((left.getCoordX()+left.getDimX()/2)<(right.getCoordX()-right.getDimX()/2)){//if right edge of left object is to the left of the left edge of the right object they can't possibly be colliding.
			return false;
		}
		GameObject upper=(a.getCoordY()<b.getCoordY())?b:a;
		GameObject lower=(a.getCoordY()<b.getCoordY())?a:b;
		if((lower.getCoordY()+lower.getDimY()/2)<(upper.getCoordY()-upper.getDimY()/2)){//same logic as above.
			return false;
		}
		return true;
	}
	
}
