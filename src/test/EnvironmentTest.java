package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.Content;
import core.Environment;
import core.NotifyObject;

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
	
	@Test
	public void testFindEnviro() {
		Environment e1 = new Environment(5.3,1.1,0.85);
		Environment e2 = new Environment(5.4,1.1,0.85);
		Environment[] enviros = {e1,e2};
		Environment[] enviros2 = null;
		
		assertEquals(e1, Environment.findEnviro("50", enviros)); //50
		assertEquals(null, Environment.findEnviro("55", enviros2)); 	
		assertEquals("'50', '5.3', '1.1', '0.85'" ,e1.toString());
	}
	
	@Test
	public void testCheckEnvironment() {
		Content con1 = new Content("Banana",new Environment(5.0,1.0,0.85), 0.1);
		NotifyObject N = new NotifyObject(0, "Container environment is valid");
		assertEquals(N.getNotifyCode(), e.checkEnvironment(con1).getNotifyCode());
		assertEquals(N.getNotifyMessage(), e.checkEnvironment(con1).getNotifyMessage());
	}
}
