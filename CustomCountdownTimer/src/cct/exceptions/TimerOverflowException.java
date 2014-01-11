package cct.exceptions;

import javax.naming.SizeLimitExceededException;

/**
 * Denotes when a timer interval was created that exceeds the maximum supported length of the timer.
 */
@SuppressWarnings("serial")
public class TimerOverflowException extends SizeLimitExceededException 
{
}
