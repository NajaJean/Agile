package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.ArraySearch;
import core.Client;

public class ClientTest {
	
	int expectedIndex = 1;
	ArraySearch search;
	
	@Before
	public void setUp() {
		search = new ArraySearch(new Client());
		Client.resetCount();
	}

	@Test
	public void testClientInfoFull() {
		Client c1 = new Client("M", "1234", "Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		Client c2 = new Client("N", "4321", "Naja","najasemail@gmail.com","Rådhuspladsen 100");
		
		assertEquals("M", c1.getUsername());
		assertEquals("1234", c1.getPassword());
		assertEquals("Mathilde", c1.getName());
		assertEquals("mathildesemail@gmail.com", c1.getEmail());
		assertEquals("Anker Egelundsvej 1", c1.getAddress());
		assertEquals(expectedIndex, c1.getID()); 
		
		assertEquals(expectedIndex + 1, c2.getID());
	}
	
	@Test
	public void testConfigureClient() {
		Client c1 = new Client("M", "1234", "Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		
		c1.setUserName("W");
		assertEquals("W", c1.getUsername());
		
		c1.setPassword("0000");
		assertEquals("0000", c1.getPassword());
		
		c1.setName("Ulla");
		assertEquals("Ulla", c1.getName());
		
		c1.setEmail("ullasemail@gmail.com");
		assertEquals("ullasemail@gmail.com", c1.getEmail());
		
		c1.setAddress("Østerkirkevej 18");
		assertEquals("Østerkirkevej 18", c1.getAddress());
	}
	
	@Test
	public void testFindClient() {
		Client c1 = new Client("M", "1234", "Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		Client c2 = new Client("N", "4321", "Naja","najasemail@gmail.com","Raadhuspladsen 100");
		Client[] clients = {c1,c2};
		Client[] emptyClients = new Client[0];
		
		assertEquals(c1, clients[search.findIDX(expectedIndex, clients)]);
		assertEquals(-1, search.findIDX(23, clients));
		assertEquals(-1, search.findIDX(23, emptyClients));
		
		assertEquals(c1, clients[search.findIDX("M", "1234", clients)]);
		assertEquals(-1, search.findIDX("M", "1234", emptyClients));
		assertEquals(-1, search.findIDX("N", "1234", clients));
		
		assertEquals(c1, clients[search.findIDX("mathildesemail@gmail.com", clients)]);
		assertEquals(-1, search.findIDX("najasemail@gmail.com", emptyClients));
		assertEquals(-1, search.findIDX("najasemail@gmai", clients));
	}
}
