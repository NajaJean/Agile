package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.Automation;
import core.Client;
import core.Container;
import core.ContainerJourney;
import core.DatabaseData;


public class AutomationTest {
	
	private Automation a;
	private int lengths = 3;
	private Client[] clients;
	private Container[] containers;
	private ContainerJourney[] cJs;
	
	
	@Before
	public void prep() {
		
		clients = DatabaseData.getClients();
		containers = DatabaseData.getContainers();
		cJs = DatabaseData.getJournies();
		a = new Automation();
		
	}
	
	
	
	@Test
	public void testrName() {
		
		String test = a.rName(lengths);
		assertEquals(lengths, test.length()); 
		assertTrue(Character.isUpperCase(test.charAt(0)));
	}
	
	@Test
	public void testrPass() {
		
		String test = a.rPass(lengths);
		assertEquals(lengths, test.length()); 
	}
	
		
	@Test
	public void testrEmail() {
		
		String test = a.rEmail(lengths);
		String ending = "@gmail.com";
		assertEquals(lengths + 10, test.length()); 
		assertTrue(test.contains(ending));
	}
	
	@Test
	public void testrAddress() {
		
		String test = a.rAddress(lengths);
		assertEquals(lengths + 3, test.length()); 
	}
	
	@Test
	public void testrClient() {
		
		Client test = a.rClient();
		assertEquals(clients[0].getClass(), test.getClass()); 
	}
	
	@Test
	public void testrContainer() {
		
		Container test = a.rContainer();
		assertEquals(containers[0].getClass(), test.getClass()); 
	}
	
	@Test
	public void testrCJs() {
		
		ContainerJourney test = a.rCJs();
		assertEquals(cJs[0].getClass(), test.getClass()); 
	}
	
	

}
