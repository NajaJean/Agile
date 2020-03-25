package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Content;
import core.Environment;
import core.Location;

public class ContainerJourneyTest {

	@Test
	public void testContent() {
		Client client = new Client("M", "1234", "Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		Environment environment = new Environment(5.3,1.1,0.85);
		Content content = new Content("Banana",new Environment(5.0,1.0,0.85), 0.1);
		
		Container c = new Container(client, environment, content);
		
		double[] gpsStart = {55.67594, 12.56553};
		double[] gpsEnd = {52.370216, 4.895168};
		
		Location start = new Location("Copenhagen", gpsStart);
		Location end = new Location("Amsterdam", gpsEnd);
		
		ContainerJourney cj = new ContainerJourney(start, end, c);
		
		assertEquals(0, cj.getJourneyID());
		
		assertEquals(start, cj.getStartLocation());
		assertEquals(end, cj.getEndLocation());
		
		assertEquals(c, cj.getContaineronJourney());
	}
}
