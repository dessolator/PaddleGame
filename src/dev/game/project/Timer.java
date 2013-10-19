package dev.game.project;

public class Timer {
	long time;
	BonusType bt;
	public Timer(int seconds,BonusType bt){
		this.bt=bt;
		time=System.nanoTime();
		time+=(long)seconds*1000000000;
	}
	public boolean isPassed(){
		return (System.nanoTime()>time?true:false);
	}

}
