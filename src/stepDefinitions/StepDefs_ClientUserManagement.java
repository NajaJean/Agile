package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import core.Client;
import core.Container;
import core.Content;
import core.Environment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_ClientUserManagement {
	
	Client specificClient;
	Client randomClient = new Client("randomUsername", "password");
	
	Container specificClientContainer;
	
	@Given("a client with username {string} and password {string} registers a container")
	public void a_client_with_username_and_password_registers_a_container(String username, String password) {
	    specificClient = new Client(username, password);
	    specificClientContainer = new Container(specificClient);
	}

	@Given("the client sets the contents of the container")
	public void the_client_sets_the_contents_of_the_container() {
		Environment requiredAppleEnvironment = new Environment(5.0, 1.0, 0.9);
		double appleThreshold = 0.1;
		
	    specificClientContainer.setContainerContent(new Content("Apple", requiredAppleEnvironment, appleThreshold));
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
	    
	    assertEquals("Corona", specificClientContainer.getClientofContainer().getUserName());
	    assertEquals("1234", specificClientContainer.getClientofContainer().getPassword());
	    
	    assertTrue(specificClient == specificClientContainer.getClientofContainer());
	    assertTrue(randomClient != specificClientContainer.getClientofContainer());
	    
	    int specificID = specificClient.getID();
	    int randomID = randomClient.getID();
	    
	    assertTrue(specificID != randomID);
	    
	    // When database is completely implemented, can add another assert statement to assert that the list 
	    // of containers associated with the "randomClient" does not include specificClientContainer and that 
	    // it belongs to the "specificClient"
	}
	
}
