package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.ArraySearch;
import core.Environment;
import core.Location;

public class LocationTest {
	
	int expectedIndex = 1;
	ArraySearch search;
	
	@Before
	public void setUp() {
		search = new ArraySearch(new Location());
		Location.resetCount();
	}

	@Test
	public void testContent() {
	
		double[] gpsAfrica = {55.67594, 12.56553};
		double[] gpsCopenhagen = {25.678, 4.6789}; 
		
		Location l = new Location("Afrika", gpsAfrica);
		Location l2 = new Location("Copenhagen",gpsCopenhagen);

		Location[] Locations = {l,l2};
		Location[] emptyLocations = new Location[0];
		
		assertEquals("Afrika", l.getLocationName());
		assertEquals(gpsAfrica, l.getGPScoord());
		assertEquals(expectedIndex, l.getLocationID()); 
		
		assertTrue(gpsAfrica[0] == l.getGPScoordX());
		assertTrue(gpsAfrica[1]== l.getGPScoordY());
		
		l.setGPSCoordX(22.1234);
		l.setGPSCoordY(23.1234);
		l.incrementOneGPSCoord(0, 1.00);
		
		assertTrue(l.getGPScoordY() == l.getGPScoordX());
		
		assertEquals(l, Locations[search.findIDX("Afrika", Locations)]);
		assertEquals(l2, Locations[search.findIDX(expectedIndex + 1, Locations)]); 
		
		assertEquals(-1, search.findIDX("Afrika", emptyLocations));
		assertEquals(-1, search.findIDX(expectedIndex, emptyLocations));
		
		String expected = "'Copenhagen', '2', '25.678', '4.6789'";
		assertEquals(expected, l2.toString());
/*		Environment e = new Environment(15.0,1.0,0.0);
		assertEquals(e ,l2.getEnvironment()); */
		
		assertEquals(-308075418, Locations[0].hashCode());
		assertEquals(false, Locations[0].equals(null));
		assertEquals(true, Locations[0].equals(Locations[0]));
		assertEquals(false, Locations[0].equals(gpsCopenhagen));
	}

}
