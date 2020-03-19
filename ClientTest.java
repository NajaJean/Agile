package test;

import static org.junit.Assert.*;

import org.junit.Test;
import core.Client;

public class ClientTest {

	@Test
	public void testClient() {
		Client c1 = new Client("Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		Client c2 = new Client("Naja","najasemail@gmail.com","Rådhuspladsen 100");
		
		assertEquals("Mathilde", c1.getName());
		assertEquals("mathildesemail@gmail.com", c1.getEmail());
		assertEquals("Anker Egelundsvej 1", c1.getAddress());
		assertEquals(1, c1.getID());
		
		assertEquals(2, c2.getID());
	}
	
	@Test
	public void testConfigureClient() {
		Client c1 = new Client("Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");

		c1.setName("Ulla");
		assertEquals("Ulla", c1.getName());
		
		c1.setEmail("ullasemail@gmail.com");
		assertEquals("ullasemail@gmail.com", c1.getEmail());
		
		c1.setAddress("Østerkirkevej 18");
		assertEquals("Østerkirkevej 18", c1.getAddress());
	}
}
