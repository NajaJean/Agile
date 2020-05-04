package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.ArraySearch;
import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Content;
import core.DatabaseData;
import core.Environment;
import core.Location;

public class ContainerJourneyTest {
	
	ArraySearch search;
	
	@Before
	public void setUp() {
		search = new ArraySearch(new ContainerJourney());
		ContainerJourney.resetCount();
	}

	@Test
	public void testContent() {
		Client[] clients = DatabaseData.getClients();
		ContainerJourney[] cJ = DatabaseData.getJournies(); 
		Location[] locations = DatabaseData.getLocations();
		Container[] containers = DatabaseData.getContainers();
		
		ContainerJourney[] emptyCJs = new ContainerJourney[0];
/*		Client client = new Client("M", "1234", "Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		Environment environment = new Environment(5.3,1.1,0.85);
		Content content = new Content("Banana",new Environment(5.0,1.0,0.85), 0.1);
		double[] cphgps = {55.1, 12.6};
		Location loc = new Location("Copenhagen",cphgps);
		
		Container c = new Container(client, environment, content,loc);
		
		double[] gpsStart = {55.67594, 12.56553};
		double[] gpsEnd = {52.370216, 4.895168};
		
		Location start = new Location("Copenhagen", gpsStart);
		Location end = new Location("Amsterdam", gpsEnd);
		
		ContainerJourney cj = new ContainerJourney(start, end, c); */
		
		assertEquals(1, cJ[0].getJourneyID());
		
		assertEquals(locations[0], cJ[0].getStartLocation());
		assertEquals(locations[15], cJ[0].getEndLocation());
		
		assertEquals(containers[2].toString(), cJ[0].getContaineronJourney().toString());
		
		assertTrue(locations[0].getGPScoordX() == cJ[0].getStartLocX());
		assertTrue(locations[0].getGPScoordY() == cJ[0].getStartLocY());
		
		assertTrue(locations[15].getGPScoord()[0] == cJ[0].getCurrentLocationDoubleA()[0]);
		assertTrue(locations[15].getGPScoord()[1] == cJ[0].getCurrentLocationDoubleA()[1]);
		
		assertTrue(locations[15].getGPScoordX() == cJ[0].getEndLocX());
		assertTrue(locations[15].getGPScoordY()== cJ[0].getEndLocY());
		
		assertEquals("'2', '15', '5', '1', '410.0', '910.0', '2020-03-28', '2020-04-15'", cJ[1].toString());
		
		assertEquals(cJ[1], cJ[search.findIDX(2, cJ)]);
		assertEquals(-1, search.findIDX("99", cJ));
		assertEquals(-1, search.findIDX("4", emptyCJs)); 

		assertEquals(cJ[1], cJ[search.findIDX("1", cJ)]); // Find from containerID
		assertEquals(-1, search.findIDX("29", cJ));
		assertEquals(-1, search.findIDX("4", emptyCJs));
	}
	
/*		@Test
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
			
//			assertEquals("'1', ''Copenhagen', '2', '55.67594', '12.56553'', ''Amsterdam', '3', '52.370216', '4.895168'', " + "''1', '1', '1', '1',"	+ " '1'', '55.67594', '12.56553'", cStart.toString());
			assertEquals("'1', '2', '3', '1', '55.67594', '12.56553'", cStart.toString());
			
			assertEquals(cStart, ContainerJourney.findJourney("1", cjArray));
			assertEquals(null, ContainerJourney.findJourney("99", cjArray));
			assertEquals(null, ContainerJourney.findJourney("4", cjArrayEmpty));
			
			
		}*/
}
