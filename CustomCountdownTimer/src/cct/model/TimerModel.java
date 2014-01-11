package cct.model;

import java.util.Observable;

import java.util.Timer;

//use this to run the new thread, make observable or whatever the final implementation is
//make sure to support pausing, adding, resetting, starting
public class TimerModel extends Observable
{
	CountdownTimer timer;
	
	private int timeRemaining;
	private int timeStopped;
	private boolean isRunning;
	
	private TimerModel()
	{
		timer = new CountdownTimer(new TimeInterval());
		timeRemaining = 0;
		isRunning = false;
	}
	
	public void setTimer(CountdownTimer aTimer)
	{
		
	}
}

