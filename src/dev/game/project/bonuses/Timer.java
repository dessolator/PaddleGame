package dev.game.project.bonuses;

import java.util.ArrayList;

import dev.game.project.gameMechanics.PaddleGame;
import dev.game.project.gameObjects.Ball;

public class Timer {
	private static final int BONUS_DURATION = 10;//constant for bonus durations.
	private static ArrayList<Timer> timers;//arraylist of active for active bonuses.
	long time;//time the bonus expires.
	BonusType bt;//the type of bonus this timer represents.
	
	static{
		timers= new ArrayList<Timer>();//initialize list of timers.
	}
	
	
	
	/**
	 * Constructor for timer.
	 * @param seconds The time in seconds that this bonus lasts.
	 * @param bt The type of bonus this timer represents.
	 */
	public Timer(int seconds,BonusType bt){
		this.bt=bt;//set bonus type to the passed value.
		time=System.nanoTime();//set time to current system time.
		time+=(long)seconds*1000000000;//add the number of seconds to be waited.
	}
	/**
	 * Function used to check if the timer expired.
	 * @return Returns true if the timer expired, false otherwise.
	 */
	public boolean isPassed(){
		return (System.nanoTime()>time?true:false);
	}
	
	/**
	 * Getter for array of timers
	 * @return	The array of timers.
	 */
	private static ArrayList<Timer> getTimers() {
		return timers;
	}
	/**
	 * Function used to remove timers for a given bonus type from timers.
	 * @param bonus	The bonus type for which to remove timers.
	 */
	private static void removeTypedTimer(BonusType bonus) { 
		for (int i=0; i<Timer.getTimers().size();i++) {
			if(Timer.getTimers().get(i).bt==bonus){
				Timer.getTimers().remove(i);
				i--;
			}
		}		
	
		
	}

	/**
	 * Function used to update timer list.
	 */
	public static void update(){
		for (int i=0; i<timers.size();i++) {//for each timer in the list,
			if(timers.get(i).isPassed()){//if the timer expired, revert bonus.
				switch(timers.get(i).bt){
				case PADDLE_WIDEN:
					PaddleGame.getLevel().getPaddle().setDimX(PaddleGame.getLevel().getPaddle().getDimX() / 1.5f);
					PaddleGame.getLevel().getPaddle().setWidened(false);
					break;
				case BALL_DAMAGE:
					if(Ball.getDamage()!=1){
						Ball.setDamage(1);
					}
					break;
				case BALL_SPEED:
					if(Ball.isSpedUp()){
						Ball.setSpedUp(false);
						Ball.setSpeedY(Ball.getSpeedY()/2);
					}
					break;
				case MULTI_BALL:
					break;
				case PADDLE_INVERT:
					PaddleGame.getLevel().getPaddle().setInverted(1);
					break;
				case PADDLE_NARROW:
					PaddleGame.getLevel().getPaddle().setDimX(PaddleGame.getLevel().getPaddle().getDimX() * 1.5f);
					PaddleGame.getLevel().getPaddle().setNarrowed(false);
					break;
				case PADDLE_SPEED:
					PaddleGame.getLevel().getPaddle().setPaddleSpeedUp(1f);
					break;
				default:
					break;
				}
				Timer.getTimers().remove(i);//remove the expired timer,
				i--;//and correct the index.
				
			}
		}
	}
	/**
	 * Function used to reset the timer for a given bonus type.
	 * @param bonus The bonus type for which to reset the timer.
	 */
	public static void reset(BonusType bonus) {
		Timer.removeTypedTimer(bonus);//remove the old timer if it exists.
		timers.add(new Timer(BONUS_DURATION,bonus));//add a new one.
		
	}
	
}
