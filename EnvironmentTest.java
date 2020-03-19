package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.Content;
import core.Environment;

public class EnvironmentTest {
	
	private Environment e;
	
	@Before
	public void createEnvironment() {
		e = new Environment(5.3,1.1,0.85);
	}
	
	@Test
	public void testEnvironment() {
		assertEquals(5.3, e.getTemp(), 0.0001);
		assertEquals(1.1, e.getPressure(), 0.0001);
		assertEquals(0.85, e.getHumidity(), 0.0001);
	}
	
	@Test
	public void testConfigureEnvironment() {
		e.setTemp(5.4);
		assertEquals(5.4, e.getTemp(), 0.0001);
		
		e.setPressure(1.3);
		assertEquals(1.3, e.getPressure(), 0.0001);
		
		e.setHumidity(0.89);
		assertEquals(0.89, e.getHumidity(), 0.0001);
	}
	
	@Test
	public void testEnvironmentValidity() {
		boolean test1 = e.validEnvironment(new Content("Banana",new Environment(5.0,1.0,0.9), 0.1));
		assertTrue("Should be valid", test1);
		
		boolean test2 = e.validEnvironment(new Content("Banana",new Environment(2.3,0.2,0.3), 0.1));
		assertFalse("Should not be valid", test2);
	}
}
