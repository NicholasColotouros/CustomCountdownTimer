package cct.model;

import java.util.ArrayList;

//the model that will be saved, loaded and used when making the timer
//TODO: implement serializability
public class CountdownTimer 
{
	TimeInterval duration;
	ArrayList<TimeInterval> reminders;
	
	CountdownTimer(TimeInterval pDuration)
	{
		duration = pDuration;
		reminders = new ArrayList<TimeInterval>();
		reminders.add(new TimeInterval());
	}
	
	CountdownTimer(TimeInterval pDuration, ArrayList<TimeInterval> pReminders)
	{
		duration = pDuration;
		//TODO: sort reminders starting at 0:0, remove duplicates
		reminders = pReminders;
	}
	
}
