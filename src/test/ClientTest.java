package test;

import static org.junit.Assert.*;

import org.junit.Test;
import core.Client;

public class ClientTest {
	
	int expectedIndex = 17;

	@Test
	public void testClientInfoFull() {
		Client c1 = new Client("M", "1234", "Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		Client c2 = new Client("N", "4321", "Naja","najasemail@gmail.com","Rådhuspladsen 100");
		
		assertEquals("M", c1.getUserName());
		assertEquals("1234", c1.getPassword());
		assertEquals("Mathilde", c1.getName());
		assertEquals("mathildesemail@gmail.com", c1.getEmail());
		assertEquals("Anker Egelundsvej 1", c1.getAddress());
		assertEquals(expectedIndex, c1.getID()); //16
		
		assertEquals(expectedIndex +1, c2.getID()); //17
	}
	
	@Test
	public void testConfigureClient() {
		Client c1 = new Client("M", "1234", "Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		Client c2 = new Client("H", "1234", "Naja", "hsh@hdew.com", "Hjemme 12");
		
		Client[] clients = {c1,c2};
		
		c1.setUserName("W");
		assertEquals("W", c1.getUserName());
		
		c1.setPassword("0000");
		assertEquals("0000", c1.getPassword());
		
		c1.setName("Ulla");
		assertEquals("Ulla", c1.getName());
		
		c1.setEmail("ullasemail@gmail.com");
		assertEquals("ullasemail@gmail.com", c1.getEmail());
		
		c1.setAddress("Østerkirkevej 18");
		assertEquals("Østerkirkevej 18", c1.getAddress());
		
		assertEquals(c2, Client.findClient(20, clients));
			
	}
	
	@Test
	public void testFindClient() {
		Client c1 = new Client("M", "1234", "Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		Client c2 = new Client("N", "4321", "Naja","najasemail@gmail.com","Raadhuspladsen 100");
		Client[] clients = {c1,c2};
		Client[] emptyClients = null;
		
		assertEquals(c1, Client.findClient("M", "1234", clients));
		assertEquals(null, Client.findClient("M", "1234", emptyClients));
		assertEquals(null, Client.findClient("N", "1234", clients));
	}
}
