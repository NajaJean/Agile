package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Classes.Location;

public class LocationTest {

	@Test
	public void testContent() {
		double[] gpsStart = {55.67594, 12.56553};
		
		Location l = new Location("Afrika", gpsStart);

		assertEquals("Afrika", l.getLocationName());
		assertEquals(gpsStart, l.getGPScoord());
		assertEquals(0, l.getLocationID());		
	}

}
