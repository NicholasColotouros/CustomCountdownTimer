package cct.model;

import cct.exceptions.NegativeInputException;

public class TimeInterval 
{
	private int seconds;
	private int minutes;
	
	TimeInterval()
	{
		seconds = 0;
		minutes = 0;
	}
	
	TimeInterval(int pSeconds, int pMinutes) throws NegativeInputException
	{
		if(pSeconds < 0 || pMinutes < 0) throw new NegativeInputException();
		
		if(pSeconds >= 60)
		{
			int extraMinutes = pSeconds/60;
			pMinutes = pMinutes + extraMinutes;
			pSeconds = pSeconds - extraMinutes*60;
		}
		
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
