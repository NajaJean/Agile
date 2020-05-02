package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import core.Client;
import core.Container;
import core.Content;
import core.DatabaseData;
import core.Environment;
import core.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_ClientUserManagement {
	
	Client specificClient;
	Client randomClient;
	
	Environment enviro;
	Location loc;
	Container specificClientContainer;
	
	Client[] Clients;
	Environment[] Enviros;
	Location[] Locs;
	Content[] Contents;
	
	public StepDefs_ClientUserManagement() {
		this.Clients = DatabaseData.getClients();
		this.Enviros = DatabaseData.getEnvironments();
		this.Locs = DatabaseData.getLocations();
		this.Contents = DatabaseData.getContents();
		specificClient = Clients[0];
		randomClient = Clients[2];
	}
	
	@Given("a client books a container")
	public void a_client_books_a_container() {
	    enviro = Enviros[0];
		loc = Locs[0];

		// Does not have content yet, just a client that booked an empty container
	    specificClientContainer = new Container(loc);
	    specificClientContainer.setClientofContainer(specificClient);
	}

	@Given("the client sets the contents of the container")
	public void the_client_sets_the_contents_of_the_container() {
	    specificClientContainer.setContainerContent(Contents[3]);
	}

	@When("clients log into the Container Management System")
	public void clients_log_into_the_Container_Management_System() {
	    specificClient.logIn(true);
	    randomClient.logIn(true);
	}

	@Then("only the client with username {string} and password {string} has access to that container")
	public void only_the_client_with_username_and_password_has_access_to_that_container(String username, String password) {
	    assertTrue(specificClient.isLoggedIn());
	    assertTrue(randomClient.isLoggedIn());
	    
	    assertEquals("bob", specificClientContainer.getClientofContainer().getUsername());
	    assertEquals("1234", specificClientContainer.getClientofContainer().getPassword());
	    
	    assertEquals(specificClient,specificClientContainer.getClientofContainer());
	    assertNotEquals(randomClient, specificClientContainer.getClientofContainer());
	    
/*	    int specificID = specificClient.getID();
	    int randomID = randomClient.getID();
	    
	    assertFalse(specificID == randomID);*/
	}
	
}
