package test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import core.*;

public class CalendarTest {	
	
	@Before
	public void prep() {	}
		
	
	@Test
	public void testGetSetSysDate() {
		
		LocalDate testSet = LocalDate.of(2020,03,28);
		
		Calendar.setSystemDate(testSet);

		assertTrue(testSet.toString().equals(Calendar.getSystemDate().toString()));
	}
	
	@Test
	public void testGoTomorrow() {
		
		Calendar c = new Calendar();
		
		
		LocalDate testSet = LocalDate.of(2020,03,28);
		
		Calendar.setSystemDate(testSet);
		 
		c.goTomorrow();

		assertTrue(testSet.plusDays(1).toString().equals(Calendar.getSystemDate().toString()));
	}
	
	@Test
	public void testGoIntoTheFurute() {
		
		Calendar c = new Calendar();
		
		LocalDate testSet = LocalDate.of(2020,03,28);
		
		Calendar.setSystemDate(testSet);
		
		int xDays = 7;
		
		c.goIntoTheFutureXDays(xDays);

		assertTrue(testSet.plusDays(xDays).toString().equals(Calendar.getSystemDate().toString()));
	}
}
