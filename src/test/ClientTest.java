package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ExternalData.DatabaseData;
import core.ArraySearch;
import core.Client;
import core.Container;
import core.ContainerJourney;

public class ClientTest {
	
	ContainerJourney[] cJs = DatabaseData.getJournies(); 
	Client[] clients = DatabaseData.getClients();
	ArraySearch search;
	
	@Before
	public void setUp() {
		search = new ArraySearch(new Client());
		Client.resetCount();
	}

	@Test
	public void testClientInfoFull() {
		assertEquals("bob", clients[0].getUsername());
		assertEquals("1234", clients[0].getPassword());
		assertEquals("Bob Smith", clients[0].getName());
		assertEquals("bob_smith@gmail.com", clients[0].getEmail());
		assertEquals("134 Candy Ln", clients[0].getAddress());
		assertEquals(1, clients[0].getID()); 
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

		Client[] emptyClients = new Client[0];
		
		assertEquals(clients[0], clients[search.findIDX(1, clients)]);
		assertEquals(-1, search.findIDX(23, clients));
		assertEquals(-1, search.findIDX(23, emptyClients));
	
		assertEquals(clients[0], clients[Client.findFromUserPass("bob", "1234", clients)]);
		assertEquals(-1, Client.findFromUserPass("bob", "1234", emptyClients));
		assertEquals(-1, Client.findFromUserPass("N", "1234", clients));
		
		assertEquals(clients[0], clients[search.findIDX("bob_smith@gmail.com", clients)]);
		assertEquals(-1, search.findIDX("najasemail@gmail.com", emptyClients));
		assertEquals(-1, search.findIDX("najasemail@gmai", clients));
		
		ContainerJourney[] conJ = {cJs[1], cJs[2]};

		ContainerJourney[] conJ2 = clients[0].getClientsCJs(cJs);
		
		Set<ContainerJourney> set1 = new HashSet<ContainerJourney>(Arrays.asList(conJ));
		Set<ContainerJourney> set2 = new HashSet<ContainerJourney>(Arrays.asList(conJ2));
		
		assertTrue(set1.equals(set2));
		
	}
}
