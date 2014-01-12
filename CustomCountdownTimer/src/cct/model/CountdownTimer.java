package cct.model;

import java.util.ArrayList;
import java.util.Collections;

import cct.exceptions.ReminderLongerThanDurationException;

//the model that will be saved, loaded and used when making the timer
//TODO: implement serializability
//This is the class to be serialized -- I don't want to serialize when it was stopped
public class CountdownTimer 
{
	protected TimeInterval duration;
	protected ArrayList<TimeInterval> reminders;
	
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
