package cct.model;

import java.util.ArrayList;
import java.util.Collections;

import cct.exceptions.ReminderLongerThanDurationException;

//TODO: implement serializability
/**
 * This is the timer that the model relies on. This is not the model
 * itself because the model contains properties which do not need
 * to be serialized.
 * 
 * Contains a duration, which will how long the timer counts down
 * and a list of time intervals for which an alert will be triggered.
 */
public class CountdownTimer 
{
	public TimeInterval duration;
	public ArrayList<TimeInterval> reminders;
	
	/**
	 * Constructor for when there are no defined reminders.
	 * @param pDuration how long the timer will be counting down once started.
	 */
	CountdownTimer(TimeInterval pDuration)
	{
		duration = pDuration;
		reminders = new ArrayList<TimeInterval>();
		reminders.add(new TimeInterval());
	}
	
	/**
	 * @param pDuration how long the timer will be counting down once started.
	 * @param pReminders list of times for when the timer will alert the user.
	 */
	CountdownTimer(TimeInterval pDuration, ArrayList<TimeInterval> pReminders) 
			throws ReminderLongerThanDurationException
	{
		duration = pDuration;
		Collections.sort(pReminders);

		//removes duplicates
		java.util.Stack<Integer> deletionStack = new java.util.Stack<Integer>();
		for(int i = 0; i < pReminders.size()-1; i++)
		{
			if(pReminders.get(i).getTotalTimeInSeconds() == pReminders.get(i).getTotalTimeInSeconds())
			{
				deletionStack.push(i);
			}
		}
		
		while(!deletionStack.isEmpty())
		{
			pReminders.remove(deletionStack.pop());
		}
		
		//adds 00:00 if not already in the list
		if(pReminders.get(pReminders.size()-1).getTotalTimeInSeconds() != 0)
		{
			pReminders.add(new TimeInterval());
		}
		
		//if the first reminder (which would be the one with the longest time in seconds)
		//is greater than the length of the timer itself, throw an exception
		if(pReminders.get(0).getTotalTimeInSeconds() >= pDuration.getTotalTimeInSeconds())
		{
			throw new ReminderLongerThanDurationException();
		}
		
		reminders = pReminders;
	}	
}
