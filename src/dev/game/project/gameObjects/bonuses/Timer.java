package dev.game.project.gameObjects.bonuses;

import java.util.ArrayList;

public class Timer {
	private static final int BONUS_DURATION = 10;//constant for bonus durations.
	private static ArrayList<Timer> timers;//arraylist of active for active bonuses.
	long time;//time the bonus expires.
	private Bonus b;
	
	static{
		timers= new ArrayList<Timer>();//initialize list of timers.
	}
	
	
	
	/**
	 * Constructor for timer.
	 * @param seconds The time in seconds that this bonus lasts.
	 * @param bt The type of bonus this timer represents.
	 */
	public Timer(int seconds,Bonus b){
		this.b=b;//set bonus type to the passed value.
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
	private static void removeTypedTimer(Bonus bonus) { 
		for (int i=0; i<Timer.getTimers().size();i++) {
			if(Timer.getTimers().get(i).b==bonus){
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
				timers.get(i).b.undo();
				Timer.getTimers().remove(i);//remove the expired timer,
				i--;//and correct the index.
				
			}
		}
	}
	/**
	 * Function used to reset the timer for a given bonus type.
	 * @param bonus The bonus type for which to reset the timer.
	 */
	public static void reset(Bonus bonus) {
		Timer.removeTypedTimer(bonus);//remove the old timer if it exists.
		timers.add(new Timer(BONUS_DURATION,bonus));//add a new one.
		
	}
	
}
