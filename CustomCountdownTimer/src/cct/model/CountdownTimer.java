package cct.model;

import java.util.ArrayList;

//the model that will be saved, loaded and used when making the timer
//TODO: implement serializability
//This is the class to be serialized -- I don't want to serialize when it was stopped
public class CountdownTimer 
{
	TimeInterval duration;
	ArrayList<TimeInterval> reminders;
	
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
	 * 
	 * @param pDuration how long the timer will be counting down once started
	 * @param pReminders list of times for when the timer will alert the user
	 */
	CountdownTimer(TimeInterval pDuration, ArrayList<TimeInterval> pReminders)
	{
		duration = pDuration;
		//TODO: sort reminders starting at 0:0, remove duplicates
		reminders = pReminders;
	}
}
