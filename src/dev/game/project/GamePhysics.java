package dev.game.project;


public class GamePhysics {
	
	/* 
	 * Horrid efficiency, creates a pair of rectangles for each check... terribly expensive
	 * better solution would be to reuse rectangles and move them about
	 */
	/**
	 * 
	 * Function used to determine if two game objects have collided
	 * @param a The first game object to be checked for collision
	 * @param b The second game object to be checked for collision
	 * @return Function returns true if the two passed objects have indeed collided
	 */
//	public static boolean hit(GameObject a, GameObject b) {
//		Rectangle r1 = new Rectangle((int)(a.coordX-((a.dimX)/2)),(int)(a.coordY+((a.dimY)/2)),(int)a.dimX,(int)a.dimY);
//		Rectangle r2 = new Rectangle((int)(b.coordX-((b.dimX)/2)),(int)(b.coordY+((b.dimY)/2)),(int)b.dimX,(int)b.dimY);
//		return r1.intersects(r2);
//	}
	
	public static boolean hit(GameObject a, GameObject b){
		GameObject left=(a.coordX<b.coordX)?a:b;
		GameObject right=(a.coordX<b.coordX)?b:a;
		if((left.coordX+left.dimX/2)<(right.coordX-right.dimX/2)){//if right edge of left object 
			return false;
		}
		GameObject upper=(a.coordY<b.coordY)?b:a;
		GameObject lower=(a.coordY<b.coordY)?a:b;
		if((lower.coordY+lower.dimY/2)<(upper.coordY-upper.dimY/2)){
			return false;
		}
		return true;
	}
	
}
