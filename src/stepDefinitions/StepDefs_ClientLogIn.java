package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import ExternalData.Database;
import ExternalData.DatabaseData;
import core.Client;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_ClientLogIn {
	
	ScenarioContext context;
	Client[] Clients;
	Database d;
	Client c;
	
	NotifyObject response;
	
	public StepDefs_ClientLogIn(ScenarioContext context) {
		this.context = context;
		this.Clients = DatabaseData.getClients();
		this.d = DatabaseData.getDatabase();
	}
	
	@Given("a client with username {string} and password {string}")
	public void a_client_with_username_and_password(String username, String password) {
		int clientIndex = Client.findFromUserPass(username, password, Clients);
		c = Clients[clientIndex];
	}
	
	@Given("that the client is not logged in")
	public void that_the_client_is_not_logged_in() {
		assertFalse(c.isLoggedIn());
	}
	
	@Given("the username is correct and password is correct")
	public void the_username_is_correct_and_password_is_correct() {
	    assertEquals(c.getUsername(), "bob");
	    assertEquals(c.getPassword(), "1234");
	}
	
	@Given("username and password exits in the Database")
	public void username_and_password_exits_in_the_Database() {
		assertTrue(d.checkUser(c.getUsername(), c.getPassword()));
	}
	
	@When("the client logs in")
	public void the_client_logs_in() {
		response = c.logIn(d.checkUser(c.getUsername(), c.getPassword()));
		context.setResponse(response);
	}
	
	@Then("the client is logged in")
	public void the_client_is_logged_in() {
		assertTrue(c.isLoggedIn());
	}
	
	@Then("message is displayed saying {string}") 
	public void message_is_displayed_saying(String s){
		assertEquals(s, context.getResponse().getNotifyMessage());
	} 

	@Given("the password or username is incorrect")
	public void the_password_or_username_is_incorrect() {
	    assertNotEquals(c.getUsername() + c.getPassword(), "alice" + "4321");
	}
	
	@When("the client tries to log in")
	public void the_client_tries_to_log_in() {
	    response = c.logIn(d.checkUser("alice", "4321"));
	    context.setResponse(response);
	}
	
	@Then("the client is not logged in")
	public void the_client_is_not_logged_in() {
		assertFalse(c.isLoggedIn());
	}	 
}
