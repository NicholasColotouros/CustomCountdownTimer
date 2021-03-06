package cct.model;

import static org.junit.Assert.*;

import org.junit.Test;

import cct.exceptions.NegativeInputException;
import cct.exceptions.TimerOverflowException;

public class TestTimeInterval {
	@Test
	public void testTimeInterval(){
		try{
			TimeInterval simpleInterval = new TimeInterval(1, 2);
			assertTrue(simpleInterval.getMinutes() == 2);
			assertTrue(simpleInterval.getSeconds() == 1);
		
			TimeInterval interestingInterval = new TimeInterval(183, 1);
			assertTrue(interestingInterval.getSeconds() == 3);
			assertTrue(interestingInterval.getMinutes() == 4);		
		}catch(TimerOverflowException e){fail();}
	}
	
	@Test
	public void testBadTimeInterval(){
		try{
			TimeInterval badInterval = new TimeInterval(-1, 0);
		}
		catch(NegativeInputException e){}
		catch(TimerOverflowException e){fail();}
		
		try{
			TimeInterval badInterval2 = new TimeInterval(0, -1);
		}
		catch(NegativeInputException e){}
		catch(TimerOverflowException e){fail();}
	}
}
