package test;

import static org.junit.Assert.*;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Before;
import org.junit.Test;

import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Content;
import core.DatabaseData;
import core.Environment;
import core.Location;

public class DatabaseDataTest {
	Client client;
	
	Content content;
	Location startLocation;
	Container container;
	
	Location endLocation;
	ContainerJourney containerJourney;
	
	@Before
	public void init() {
		client = new Client("M", "12345", "Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		
		content = new Content("Apple", new Environment(2.0,0.8,0.5), 0.1);
		startLocation = new Location("Copenhagen", new double[]{55.68, 12.57});
		container = new Container(client, content, startLocation);
		
		endLocation = new Location("New York", new double[]{40.71, 74.01});
		containerJourney = new ContainerJourney(startLocation, endLocation, container);
	}

	@Test
	public void addRemoveDataTest() {
		// Add client
		DatabaseData.addClient(client);
		assertEquals(client, DatabaseData.getClients()[DatabaseData.getClients().length - 1]);
		
		// Remove client
		int oldClientLength = DatabaseData.getClients().length;
		DatabaseData.removeClient(client);
		
		assertNotSame(client, DatabaseData.getClients()[DatabaseData.getClients().length - 1]);
		assertEquals(oldClientLength - 1, DatabaseData.getClients().length);
		
		int clientIDX = ArrayUtils.indexOf(DatabaseData.getClients(), client);
		assertTrue(clientIDX == -1);
		
		
		// Add container
		DatabaseData.addContainer(container);
		assertEquals(container, DatabaseData.getContainers()[DatabaseData.getContainers().length - 1]);
		
		// Remove container 
		int oldContainerLength = DatabaseData.getContainers().length;
		DatabaseData.removeContainer(container);
		
		assertNotSame(container, DatabaseData.getContainers()[DatabaseData.getContainers().length - 1]);
		assertEquals(oldContainerLength - 1, DatabaseData.getContainers().length);
		
		int containerIDX = ArrayUtils.indexOf(DatabaseData.getContainers(), container);
		assertTrue(containerIDX == -1);
		
		
		// Add container journey
		DatabaseData.addContainerJourney(containerJourney);
		assertEquals(containerJourney, DatabaseData.getJournies()[DatabaseData.getJournies().length - 1]);
		
		// Remove container journey
		int oldContainerJourneyLength = DatabaseData.getJournies().length;
		DatabaseData.removeContainerJourney(containerJourney);
		
		assertNotSame(containerJourney, DatabaseData.getJournies()[DatabaseData.getJournies().length - 1]);
		assertEquals(oldContainerJourneyLength - 1, DatabaseData.getJournies().length);
		
		int containerJourneyIDX = ArrayUtils.indexOf(DatabaseData.getContainers(), container);
		assertTrue(containerJourneyIDX == -1);
	}
	
	/*
	@Test
	public void dataObserverTest() {
		int oldLengthClients = DatabaseData.getClients().length;
		Client newClient = new Client("C", "2323", "Carter", "carter@carter.com", "San Luis Obispo");
		DatabaseData.getDatabase().addToDatabase("Clients", newClient.toString(), newClient);
		
		assertEquals((oldLengthClients + 1), DatabaseData.getClients().length);
		assertEquals(newClient, DatabaseData.getClients()[oldLengthClients]);
		
		DatabaseData.printClients();
	}
	*/
}
