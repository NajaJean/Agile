package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Content;
import core.Environment;

public class ContentTest {

	@Test
	public void testContent() {
		Content con1 = new Content("Banana",new Environment(5.0,1.0,0.85), 0.1);
		
		assertEquals(con1.getName(),"Banana");
		assertEquals(con1.getEnvironment().getTemp(),5.0, 0.0001);
		assertEquals(con1.getEnvironment().getPressure(),1.0, 0.0001);
		assertEquals(con1.getEnvironment().getHumidity(),0.85, 0.0001);
		assertEquals(con1.getThreshold(),0.1, 0.0001);
		
	}

}
