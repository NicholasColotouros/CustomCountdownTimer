package cct.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Timer;

import cct.exceptions.TimerOverflowException;

/**
 * The model that is used by the GUI to track how much time
 * is remaining and trigger the alerts.
 */
//TODO: test
public class TimerModel extends Observable
{
	private static final TimerModel INSTANCE = new TimerModel();
	
	private CountdownTimer timer;
	private int timeRemaining;
	private int currentReminderIndex;
	
	private Timer cdTimer;
	private boolean isRunning;
	
	//TODO: fix bug in initiation of singleton
	private TimerModel()
	{
		timer = new CountdownTimer(new TimeInterval());
		timeRemaining = 0;
		currentReminderIndex = 0;
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
			isRunning = false;
		}
		
		timer = aTimer;
		timeRemaining = aTimer.duration.getTotalTimeInSeconds();
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Starts the timer as specified by the user.
	 */
	public void start()
	{
		//do nothing if there is no time remaining
		if(timer.duration.getTotalTimeInSeconds() == 0) return; 
		
		//if the timer has ended and start button pressed
		//reset the timer and start again
		if(timeRemaining == 0)
		{
			timeRemaining = timer.duration.getTotalTimeInSeconds();
		}
		
		//else if the timer didn't finish, continue from where it left off
		else if(timeRemaining < timer.duration.getTotalTimeInSeconds())
		{
			cdTimer.start();
			isRunning = true;
		}
		
		//else timer starts from the beginning
		else
		{
			int delay = 1000; //milliseconds
			cdTimer = new Timer(delay, new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent pEvent)
				{
					//if the time remaining is the same as the next reminder
					if(timeRemaining == timer.reminders.get(currentReminderIndex).getTotalTimeInSeconds())
					{
						currentReminderIndex++; //move to the next reminder
					}
					
					//When the timer reaches 0
					if(timeRemaining == 0)
					{
						Timer source = (Timer)pEvent.getSource();
						source.stop();
						isRunning = false;
					}					
					timeRemaining--;
					
					setChanged();
					notifyObservers();
				}
			});
			
			cdTimer.start();
			isRunning = true;
			
			setChanged();
			notifyObservers();
		}
	}
	
	/**
	 * Stops the timer if it is running and resets it.
	 */
	public void stopAndReset()
	{
		if(cdTimer.isRunning())
		{
			cdTimer.stop();
			isRunning = false;
		}		
		timeRemaining = timer.duration.getTotalTimeInSeconds();
		currentReminderIndex = 0;
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Pauses the timer. When the timer is resumed it will
	 * pick up where it left off.
	 */
	public void pause()
	{
		cdTimer.stop();
		isRunning = false;
		
		setChanged();
		notifyObservers();
	}
	
	public String getNextAlertAsString()
	{
		return timer.reminders.get(currentReminderIndex).toString();
	}
	
	public boolean isRunning()
	{
		return isRunning;
	}
	
	public String getTimeRemainingAsString()
	{
		try
		{
			TimeInterval ret = new TimeInterval(timeRemaining, 0);
			return ret.toString();			
		}
		catch(TimerOverflowException e)
		{
			return "00:00";
		}
	}
}