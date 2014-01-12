package cct.model;

import java.util.Observable;

import java.util.Timer;

//use this to run the new thread, make observable or whatever the final implementation is
//make sure to support pausing, adding, resetting, starting
public class TimerModel extends Observable
{
	private static final TimerModel INSTANCE = new TimerModel();
	
	private CountdownTimer timer;
	private int timeRemaining;
	private int timeStopped;
	private boolean isRunning;
	
	private TimerModel()
	{
		timer = new CountdownTimer(new TimeInterval());
		timeRemaining = 0;
		isRunning = false;
	}
	
	public static TimerModel getInstance()
	{
		return INSTANCE;
	}
	
	public void setTimer(CountdownTimer aTimer)
	{
		timer = aTimer;
	}
	
	public void start()
	{
		//TODO
	}
	
	public void stop()
	{
		//TODO
	}
	
	public void pause()
	{
		//TODO
	}
}

