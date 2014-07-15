package cct.model;

import cct.exceptions.NegativeInputException;
import cct.exceptions.TimerOverflowException;

/**
 * A class that simplifies taking in data from the user to construct timers.
 */
public class TimeInterval implements Comparable<TimeInterval>{
	private int seconds;
	private int minutes;
	
	/**
	 * Constructor for when a timer of 0 length is needed
	 */
	public TimeInterval(){
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
	public TimeInterval(int pSeconds, int pMinutes) throws NegativeInputException, TimerOverflowException{
		if(pSeconds < 0 || pMinutes < 0) throw new NegativeInputException();
		
		if(pSeconds >= 60){
			int extraMinutes = pSeconds/60;
			pMinutes = pMinutes + extraMinutes;
			pSeconds = pSeconds - extraMinutes*60;
		}
		
		if(pSeconds > 99) throw new TimerOverflowException();
		
		seconds = pSeconds;
		minutes = pMinutes;
	}
	
	/**
	 * @return the total time of the time interval in seconds.
	 */
	public int getTotalTimeInSeconds(){
		return minutes * 60 + seconds;
	}
	
	/**
	 * @return the number of seconds stored in the interval when minutes are ignored.
	 */
	public int getSeconds(){
		return seconds;
	}
	
	/**
	 * @return the number of minutes stored in the interval.
	 */
	public int getMinutes(){
		return minutes;
	}
	
	/**
	 * @return returns a string representation in the format 00:00
	 */
	public String toString(){
		String minString;
		if(minutes < 10) minString = new String("0" + minutes + ":");
		else minString = new String(minutes + ":");
		
		if(seconds < 10) return minString + "0" + seconds;
		else return minString + seconds;
	}

	@Override
	/**
	 * Sorts so that the first item has the longest interval
	 */
	public int compareTo(TimeInterval arg0) {
		int t1 = ((TimeInterval) this).getTotalTimeInSeconds();
		int t2 = ((TimeInterval) arg0).getTotalTimeInSeconds();
		
		if(t1 > t2) return -1;
		else if(t1 < t2) return 1;
		else return 0;
	}
}
