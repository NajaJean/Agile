package test;

import static org.junit.Assert.*;

import org.junit.Test;
import core.Client;

public class ClientTest {

	@Test
	public void testClientInfoFull() {
		Client c1 = new Client("M", "1234", "Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		Client c2 = new Client("N", "4321", "Naja","najasemail@gmail.com","Rådhuspladsen 100");
		
		assertEquals("M", c1.getUserName());
		assertEquals("1234", c1.getPassword());
		assertEquals("Mathilde", c1.getName());
		assertEquals("mathildesemail@gmail.com", c1.getEmail());
		assertEquals("Anker Egelundsvej 1", c1.getAddress());
		assertEquals(1, c1.getID());
		
		assertEquals(2, c2.getID());
	}
	
	@Test
	public void testClientInfoMinimal() {
		Client c1 = new Client("X", "1234");
		Client c2 = new Client("N", "4321");
		
		assertEquals("X", c1.getUserName());
		assertEquals("1234", c1.getPassword());
		assertEquals("noname", c1.getName());
		assertEquals("no email", c1.getEmail());
		assertEquals("homeless", c1.getAddress());
		assertEquals(3, c1.getID());
		
		assertEquals(4, c2.getID());
	}
	
	@Test
	public void testConfigureClient() {
		Client c1 = new Client("M", "1234", "Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		
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
	}
}
