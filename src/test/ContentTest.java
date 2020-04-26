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
		assertEquals(19, con1.getContentID()); //19
	}
	
	@Test
	public void testFindContent() {
		Content con1 = new Content("Banana",new Environment(5.0,1.0,0.85), 0.1);
		Content con2 = new Content("Apples",new Environment(6.0,2.0,0.95), 0.2);
		Content[] cons = {con1, con2};
		Content[] consEmpty = null;
		
		assertEquals(con1, Content.findContent(20, cons));
		assertEquals(con1, Content.findContent("Banana", cons));
		assertEquals(null, Content.findContent(1, consEmpty));
		assertEquals(null, Content.findContent("Banana", consEmpty));
		assertEquals(null, Content.findContent("Bana", cons));
		assertEquals("'20', 'Banana', '30', '0.1'", con1.toString());
		
		
	}
}
