package dev.game.project;

import java.util.ArrayList;

public class Timer {
	private static ArrayList<Timer> timers;
	long time;
	BonusType bt;
	
	static{
		timers= new ArrayList<Timer>();
	}
	
	
	
	public Timer(int seconds,BonusType bt){
		this.bt=bt;
		time=System.nanoTime();
		time+=(long)seconds*1000000000;
	}
	public boolean isPassed(){
		return (System.nanoTime()>time?true:false);
	}
	public static ArrayList<Timer> getTimers() {
		return timers;
	}
	public static void removeTypedTimer(BonusType bonus) { 
		for (int i=0; i<Timer.getTimers().size();i++) {
			if(Timer.getTimers().get(i).bt==bonus){
				Timer.getTimers().remove(i);
				i--;
			}
		}		
	
		
	}

	public static void update(){
		for (int i=0; i<timers.size();i++) {
			if(timers.get(i).isPassed()){
				switch(timers.get(i).bt){
				case PADDLE_WIDEN:
					PaddleGame.getLevel().getPaddle().dimX/=1.5f;
					PaddleGame.getLevel().getPaddle().setWidened(false);
					break;
				case BALL_DAMAGE:
					break;
				case BALL_SPEED:
					if(PaddleGame.getLevel().getBall().isSpedUp()){
						PaddleGame.getLevel().getBall().setSpedUp(false);
						PaddleGame.getLevel().getBall().setSpeedY(PaddleGame.getLevel().getBall().getSpeedY()/2);
					}
					break;
				case MULTI_BALL:
					break;
				case PADDLE_INVERT:
					PaddleGame.getLevel().getPaddle().setInverted(1);
					break;
				case PADDLE_NARROW:
					PaddleGame.getLevel().getPaddle().dimX*=1.5f;
					PaddleGame.getLevel().getPaddle().setNarrowed(false);
					break;
				case PADDLE_SPEED:
					PaddleGame.getLevel().getPaddle().setPaddleSpeedUp(1f);
					break;
				default:
					break;
				}
				Timer.getTimers().remove(i);
				i--;
				
			}
		}
	}
	
}
