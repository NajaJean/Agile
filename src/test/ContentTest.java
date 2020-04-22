package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Content;
import core.Environment;

public class ContentTest {

	@Test
	public void testContent() {
		Content con1 = new Content("Banana",new Environment(5.0,1.0,0.85), 0.1);
		
		assertEquals("Banana", con1.getName());
		assertEquals(5.0, con1.getEnvironment().getTemp(), 0.0001);
		assertEquals(1.0, con1.getEnvironment().getPressure(), 0.0001);
		assertEquals(0.85, con1.getEnvironment().getHumidity(), 0.0001);
		assertEquals(0.1, con1.getThreshold(), 0.0001);
		assertEquals(6, con1.getContentID());
	}
}
