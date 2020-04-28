package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Environment;
import core.Location;

public class LocationTest {

	@Test
	public void testContent() {
	
		double[] gpsAfrica = {55.67594, 12.56553};
		double[] gpsCopenhagen = {25.678, 4.6789}; 
		
		Location l = new Location("Afrika", gpsAfrica);
		Location l2 = new Location("Copenhagen",gpsCopenhagen);

		Location[] LocationArray = {l,l2};
		Location[] LocationArray2 = null;
		
		assertEquals("Afrika", l.getLocationName());
		assertEquals(gpsAfrica, l.getGPScoord());
		assertEquals(27, l.getLocationID()); //27
		
		assertTrue(gpsAfrica[0] == l.getGPScoordX());
		assertTrue(gpsAfrica[1]== l.getGPScoordY());
		
		l.setGPSCoordX(22.1234);
		l.setGPSCoordY(23.1234);
		l.incrementOneGPSCoord(0, 1.00);
		
		assertTrue(l.getGPScoordY() == l.getGPScoordX());
		
		assertEquals(l, Location.findLocation("Afrika", LocationArray));
		assertEquals(l2, Location.findLocation(28, LocationArray)); //14
		assertEquals(null, Location.findLocation("Afrika", LocationArray2));
		assertEquals(null, Location.findLocation(9, LocationArray2));
		assertEquals(LocationArray[0], Location.findLocation("Afrik", LocationArray));
		
		assertEquals("'Copenhagen', '28', '25.678', '4.6789'", l2.toString());
/*		Environment e = new Environment(15.0,1.0,0.0);
		assertEquals(e ,l2.getEnvironment()); */
	}

}
