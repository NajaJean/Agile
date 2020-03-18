package test;

import static org.junit.Assert.*;

import org.junit.Test;
import core.Client;

public class ClientTest {

	@Test
	public void testClient() {
		Client c1 = new Client("Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		Client c2 = new Client("Naja","najasemail@gmail.com","Rådhuspladsen 100");
		
		assertEquals(c1.getName(),"Mathilde");
		assertEquals(c1.getEmail(),"mathildesemail@gmail.com");
		assertEquals(c1.getAddress(),"Anker Egelundsvej 1");
		assertEquals(c1.getID(),1);
		
		assertEquals(c2.getID(),2);
	}
	
	@Test
	public void testConfigureClient() {
		Client c1 = new Client("Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");

		c1.setName("Ulla");
		assertEquals(c1.getName(),"Ulla");
		
		c1.setEmail("ullasemail@gmail.com");
		assertEquals(c1.getEmail(),"ullasemail@gmail.com");
		
		c1.setAddress("Østerkirkevej 18");
		assertEquals(c1.getAddress(),"Østerkirkevej 18");
	}

}
