package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import core.Client;
import core.Database;
import core.DatabaseData;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_ClientLogIn {
	
	ScenarioContext context;
	Client[] Clients;
	Database d;
	
	NotifyObject response;
	
	public StepDefs_ClientLogIn(ScenarioContext context) {
		this.context = context;
		this.Clients = DatabaseData.getClients();
		this.d = DatabaseData.getDatabase();
	}
	
	@Given("that the client is not logged in")
	public void that_the_client_is_not_logged_in() {
		assertTrue(!Clients[1].isLoggedIn());
	}
	
	@Given("the username is correct and password is correct")
	public void the_username_is_correct_and_password_is_correct() {
	    assertEquals(Clients[2].getUserName(), "alice");
	    assertEquals(Clients[2].getPassword(), "1234");
	}
	
	@Given("username and password exits in the Database")
	public void username_and_password_exits_in_the_Database() {
		assertTrue(d.checkUser(Clients[2].getUserName(), Clients[2].getPassword()));
	}
	
	@When("the client logs in")
	public void the_client_logs_in() {
		response = Clients[2].logIn(d.checkUser(Clients[2].getUserName(), Clients[2].getPassword()));
		context.setResponse(response);
	}
	
	@Then("the client is logged in")
	public void the_client_is_logged_in() {
		assertTrue(Clients[2].isLoggedIn());
	}
	
	@Then("message is displayed saying {string}") 
	public void message_is_displayed_saying(String s){
		assertEquals(s, context.getResponse().getNotifyMessage());
	} 

	@Given("the password or username is incorrect")
	public void the_password_or_username_is_incorrect() {
	    assertNotEquals(Clients[1].getUserName() + Clients[1].getPassword(), "alice" + "4321");
	}
	
	@When("the client tries to log in")
	public void the_client_tries_to_log_in() {
	    response = Clients[1].logIn(d.checkUser("alice", "4321"));
	    context.setResponse(response);
	}
	
	@Then("the client is not logged in")
	public void the_client_is_not_logged_in() {
		assertTrue(!Clients[1].isLoggedIn());
	}	 
}
