package cct.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Timer;

/**
 * The model that is used by the GUI to track how much time
 * is remaining and trigger the alerts.
 */
public class TimerModel extends Observable
{
	private static final TimerModel INSTANCE = new TimerModel();
	
	private CountdownTimer timer;
	private int timeRemaining;
	private int timeStopped;
	private boolean isRunning;
	
	private Timer cdTimer;
	
	private TimerModel()
	{
		timer = new CountdownTimer(new TimeInterval());
		timeRemaining = 0;
		timeStopped = 0;
		isRunning = false;
	}
	
	/**
	 * Returns the instance of the timer.
	 * 
	 * @return the instance of TimerModel.
	 */
	public static TimerModel getInstance()
	{
		return INSTANCE;
	}
	
	/**
	 * Sets the timer. If there is currently a timer running
	 * it will be stopped.
	 * 
	 * @param aTimer The timer to be used. This will likely
	 * have been given by the user from the GUI.
	 */
	public void setTimer(CountdownTimer aTimer)
	{
		if(cdTimer.isRunning())
		{
			cdTimer.stop();
		}
		
		timer = aTimer;
		timeRemaining = aTimer.duration.getTotalTimeInSeconds();
		//TODO: update the time remaining on GUI
	}
	
	/**
	 * Starts the timer as specified by the user.
	 */
	public void start()
	{
		//TODO
		if(timeRemaining == 0) return; //do nothing if there is no time remaining
		
		int delay = 1000; //milliseconds
		cdTimer = new Timer(delay, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent pEvent)
			{
				//TODO: check how much time is remaining, update the timer as needed.
				//This is messy code, clean it up
			}
		});
		
		cdTimer.start();
	}
	
	/**
	 * Stops the timer if it is running and resets it.
	 */
	public void stopAndReset()
	{
		//TODO
		if(cdTimer.isRunning())
		{
			cdTimer.stop();
		}		
		timeRemaining = timer.duration.getTotalTimeInSeconds();
		//TODO: reset the next reminder

	}
	
	/**
	 * Pauses the timer. When the timer is resumed it will
	 * pick up where it left off.
	 */
	public void pause()
	{
		//TODO
	}
}

