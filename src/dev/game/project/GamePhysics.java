package dev.game.project;


public class GamePhysics {
	
	
	/**
	 * 
	 * Function used to determine if two game objects have collided.
	 * @param a The first game object to be checked for collision.
	 * @param b The second game object to be checked for collision.
	 * @return Function returns true if the two passed objects have indeed collided.
	 */
	public static boolean hit(GameObject a, GameObject b){
		GameObject left=(a.coordX<b.coordX)?a:b;
		GameObject right=(a.coordX<b.coordX)?b:a;
		if((left.coordX+left.dimX/2)<(right.coordX-right.dimX/2)){//if right edge of left object is to the left of the left edge of the right object they can't possibly be colliding.
			return false;
		}
		GameObject upper=(a.coordY<b.coordY)?b:a;
		GameObject lower=(a.coordY<b.coordY)?a:b;
		if((lower.coordY+lower.dimY/2)<(upper.coordY-upper.dimY/2)){//same logic as above.
			return false;
		}
		return true;
	}
	
}
