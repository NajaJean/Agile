package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Content;
import core.Environment;

public class ContentTest {

	@Test
	public void testContent() {
		Content con1 = new Content("Banana",new Environment(5,1,85), 1.3f);
		
		assertEquals(con1.getName(),"Banana");
		assertEquals(con1.getEnvironment().getTemp(),5);
		assertEquals(con1.getEnvironment().getPressure(),1);
		assertEquals(con1.getEnvironment().getHumidity(),85);
		assertEquals(con1.getThreshold(),1.3f, 0.0001);
		
	}

}
