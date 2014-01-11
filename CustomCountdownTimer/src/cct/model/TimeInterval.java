package cct.model;

import cct.exceptions.NegativeInputException;
import cct.exceptions.TimerOverflowException;

public class TimeInterval 
{
	private int seconds;
	private int minutes;
	
	/**
	 * Placeholder constructor for when a timer of 0 length is needed
	 */
	TimeInterval()
	{
		seconds = 0;
		minutes = 0;
	}
	
	/**
	 * Constructs a time interval based on the times given
	 * 
	 * @param pSeconds The number of seconds that come after the number of minutes. 
	 * If more than 59 seconds are input additional minute(s) will be added to the minutes 
	 * and the seconds value will be changed accordingly to keep the time interval equivalent.
	 * @param pMinutes The number of minutes.
	 * @throws NegativeInputException when a negative number is passed as an argument.
	 * @throws TimerOverflowException when a time interval greater than what is supported is given. 
	 */
	TimeInterval(int pSeconds, int pMinutes) throws NegativeInputException, TimerOverflowException
	{
		if(pSeconds < 0 || pMinutes < 0) throw new NegativeInputException();
		
		if(pSeconds >= 60)
		{
			int extraMinutes = pSeconds/60;
			pMinutes = pMinutes + extraMinutes;
			pSeconds = pSeconds - extraMinutes*60;
		}
		
		if(pSeconds > 99) throw new TimerOverflowException();
		
		seconds = pSeconds;
		minutes = pMinutes;
	}
	
	public int getSeconds()
	{
		return seconds;
	}
	
	public int getMinutes()
	{
		return minutes;
	}
}
