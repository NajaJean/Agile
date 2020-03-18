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
		Client client = new Client("Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		Environment environment = new Environment(5.3,1.1,0.85);
		Content content = new Content("Banana",new Environment(5.0,1.0,0.85), 0.1);
		
		Container c = new Container(client, environment, content);
		
		double[] gpsStart = {55.67594, 12.56553};
		double[] gpsEnd = {52.370216, 4.895168};
		
		Location start = new Location("Copenhagen", gpsStart);
		Location end = new Location("Amsterdam", gpsEnd);
		
		ContainerJourney cj = new ContainerJourney(start, end, c);
		
		assertEquals(cj.getJourneyID(),1);
		
		assertEquals(cj.getStartLocation(), start);
		assertEquals(cj.getEndLocation(), end);
		
		assertEquals(cj.getContaineronJourney(), c);
	}
}
