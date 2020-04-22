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
		double[] cphgps = {55.1, 12.6};
		Location loc = new Location("Copenhagen",cphgps);
		
		Container c = new Container(client, environment, content,loc);
		
		double[] gpsStart = {55.67594, 12.56553};
		double[] gpsEnd = {52.370216, 4.895168};
		
		Location start = new Location("Copenhagen", gpsStart);
		Location end = new Location("Amsterdam", gpsEnd);
		
		ContainerJourney cj = new ContainerJourney(start, end, c);
		
		assertEquals(3, cj.getJourneyID());
		
		assertEquals(start, cj.getStartLocation());
		assertEquals(end, cj.getEndLocation());
		
		assertEquals(c, cj.getContaineronJourney());
		
		assertTrue(gpsStart[0] == cj.getStartLocX());
		assertTrue(gpsStart[1] == cj.getStartLocY());
		
		assertTrue(start.getGPScoord()[0] == cj.getCurrentLocationDoubleA()[0]);
		assertTrue(start.getGPScoord()[1] == cj.getCurrentLocationDoubleA()[1]);
		
		assertTrue(gpsEnd[0] == cj.getEndLocX());
		assertTrue(gpsEnd[1] == cj.getEndLocY());
		

	}
	
		@Test
		public void testFindJourney() {
			Client client = new Client("M", "1234", "Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
			Environment environment = new Environment(5.3,1.1,0.85);
			Content content = new Content("Banana",new Environment(5.0,1.0,0.85), 0.1);
			double[] cphgps = {55.1, 12.6};
			Location loc = new Location("Copenhagen",cphgps);
			
			Container c = new Container(client, environment, content,loc);
			
			double[] gpsStart = {55.67594, 12.56553};
			double[] gpsEnd = {52.370216, 4.895168};
			
			Location start = new Location("Copenhagen", gpsStart);
			Location end = new Location("Amsterdam", gpsEnd);
			
			ContainerJourney cStart = new ContainerJourney(start, end, c);
			ContainerJourney cEnd = new ContainerJourney(end, start, c);
			ContainerJourney[] cjArray = {cStart, cEnd};
			ContainerJourney[] cjArrayEmpty = null;
			
			assertEquals("'1', ''Copenhagen', '2', '55.67594', '12.56553'', ''Amsterdam', '3', '52.370216', '4.895168'', " + "''1', '1', '1', '1',"
					+ " '1'', '55.67594', '12.56553'", cStart.toString());
			
			assertEquals(cStart, ContainerJourney.findJourney("1", cjArray));
			assertEquals(null, ContainerJourney.findJourney("99", cjArray));
			assertEquals(null, ContainerJourney.findJourney("4", cjArrayEmpty));
			
			
		}
}
