package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.ArraySearch;
import core.DatabaseData;
import core.Location;

public class LocationTest {
	
	int expectedIndex = 1;
	ArraySearch search;
	Location[] locations = DatabaseData.getLocations();
	double[] gpsCapeTown = {780.0, 790.0};
	Location[] emptyLocations = new Location[0];
	
	@Before
	public void setUp() {
		search = new ArraySearch(new Location());
		Location.resetCount();
	}

	@Test
	public void testLocation() {
	
		assertEquals("Cape Town", locations[5].getLocationName());
		assertEquals(6, locations[5].getLocationID()); 
		
		assertTrue(gpsCapeTown[0] == locations[5].getGPScoordX());
		assertTrue(gpsCapeTown[1]== locations[5].getGPScoordY());
		
		locations[5].setGPSCoordX(22.1234);
		locations[5].setGPSCoordY(23.1234);
		locations[5].incrementOneGPSCoord(0, 1.00);
		
		assertTrue(locations[5].getGPScoordY() == locations[5].getGPScoordX());
		
		assertEquals(locations[5], locations[search.findIDX("Cape Town", locations)]);
		assertEquals(locations[5], locations[search.findIDX(6, locations)]); 
	}
	
	@Test
	public void testFindLocation() {

		assertEquals(-1, search.findIDX("Afrika", emptyLocations));
		assertEquals(-1, search.findIDX(expectedIndex, emptyLocations));
		
		String expected = "'Cape Town', '6', '780.0', '790.0'";
		assertEquals(expected, locations[5].toString());
		
		assertEquals(-636331458, locations[0].hashCode());
		assertEquals(false, locations[0].equals(null));
		assertEquals(true, locations[0].equals(locations[0]));
		assertEquals(false, locations[0].equals(gpsCapeTown));
	}

}
