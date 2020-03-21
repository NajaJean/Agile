package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Location;

public class LocationTest {
//test
	@Test
	public void testContent() {
		double[] gpsStart = {55.67594, 12.56553};
		
		Location l = new Location("Afrika", gpsStart);

		assertEquals("Afrika", l.getLocationName());
		assertEquals(gpsStart, l.getGPScoord());
		assertEquals(1, l.getLocationID());		
	}

}
