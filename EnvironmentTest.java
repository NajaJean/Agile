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
		assertEquals(e.getTemp(),5.3, 0.0001);
		assertEquals(e.getPressure(),1.1, 0.0001);
		assertEquals(e.getHumidity(),0.85, 0.0001);
	}
	
	@Test
	public void testConfigureEnvironment() {
		e.setTemp(5.4);
		assertEquals(e.getTemp(), 5.4, 0.0001);
		
		e.setPressure(1.3);
		assertEquals(e.getPressure(), 1.3, 0.0001);
		
		e.setHumidity(0.89);
		assertEquals(e.getHumidity(), 0.89, 0.0001);
	}
	
	@Test
	public void testEnvironmentValidity() {
		boolean test1 = e.validEnvironment(new Content("Banana",new Environment(5.0,1.0,0.9), 0.1));
		assertTrue("Should be valid",test1);
		
		boolean test2 = e.validEnvironment(new Content("Banana",new Environment(2.3,0.2,0.3), 0.1));
		assertFalse("Should not be valid",test2);
	}
}
