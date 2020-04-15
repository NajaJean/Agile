package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import core.Client;
import core.Database;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_ClientLogIn {
	
	ScenarioContext context;
	
	public StepDefs_ClientLogIn(ScenarioContext context) {
		this.context = context;
	}
	
	Client Cc = new Client("bob", "1234", "Bob Smith", "bob_smith@gmail.com","l34 Candy ln"); 
	Client Ic = new Client("Horona", "1234", "Habrat", "h@", "Hasd");
	Database d = new Database("agileProject.accdb");
	
	NotifyObject response;
	
	@Given("that the client is not logged in")
	public void that_the_client_is_not_logged_in() {
		assertTrue(!Cc.isLoggedIn());
		assertTrue(!Ic.isLoggedIn());
	}
	
	@Given("the username is correct and password is correct")
	public void the_username_is_correct_and_password_is_correct() {
	    assertEquals(Cc.getUserName(), "bob");
	    assertEquals(Cc.getPassword(), "1234");
	}
	
	@Given("username and password exits in the Database")
	public void username_and_password_exits_in_the_Database() {
		assertTrue(d.checkUser(Cc.getUserName(), Cc.getPassword()));
	}
	
	@When("the client logs in")
	public void the_client_logs_in() {
		response = Cc.logIn(d.checkUser(Cc.getUserName(), Cc.getPassword()));
		context.setResponse(response);
	}
	
	@Then("the client is logged in")
	public void the_client_is_logged_in() {
		assertTrue(Cc.isLoggedIn());
	}
	
	@Then("message is displayed saying {string}") 
	public void message_is_displayed_saying(String s){
		assertEquals(s, context.getResponse().getNotifyMessage());
	} 

	@Given("the password or username is incorrect")
	public void the_password_or_username_is_incorrect() {
	    assertNotEquals(Ic.getUserName() + Ic.getPassword(), "bob" + "1234");
	}
	
	@Given("username or password does not exits in the Database")
	public void username_or_password_does_not_exits_in_the_Database() {
		assertFalse(d.checkUser(Ic.getUserName(), Ic.getPassword()));
	}
	
	@When("the client tries to log in")
	public void the_client_tries_to_log_in() {
	    response = Ic.logIn(d.checkUser(Ic.getUserName(), Ic.getPassword()));
	    context.setResponse(response);
	}

	@Then("an error-message is displayed saying {string}")
	public void an_error_message_is_displayed_saying(String s) {
		assertEquals(s, context.getResponse().getNotifyMessage());
	} 
	
	@Then("the client is not logged in")
	public void the_client_is_not_logged_in() {
		assertTrue(!Ic.isLoggedIn());
	}	 
}
