package cct.exceptions;

/**
 * Denotes an input reminder which is greater than the duration of the timer itself.
 */
@SuppressWarnings("serial")
public class ReminderLongerThanDurationException extends
		IllegalArgumentException 
{

}
