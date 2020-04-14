package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import core.LogisticCompany;
import core.NotifyObject;

import static org.junit.Assert.assertNotEquals;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_LogisticLogIn {
	
	NotifyObject response;
	
	LogisticCompany l = new LogisticCompany();
	
	@Given("that the Logistic Company is not logged in")
	public void that_the_Logistic_Company_is_not_logged_in() {
		assertTrue(!l.isLoggedIn());
	}

	@Given("the username is {string} and password is {string}")
	public void the_username_is_and_password_is(String string, String string2) {
	    assertEquals("MSK", string);
	    assertEquals("1234", string2);
	}

	@When("the logistic Company logs in")
	public void the_logistic_Company_logs_in() {
		response = l.logIn(true);
	}
	
	@Then("the Logistic Company is logged in")
	public void the_Logistic_Company_is_logged_in() {
		assertTrue(l.isLoggedIn());
	}
	
	@Then("message is displayed saying {string}") 
	public void message_is_displayed_saying(String s){
		assertEquals(s, response.getNotifyMessage());
	}

	@Given("the username or the password is wrong")
	public void the_username_or_the_password_is_wrong() {
		l.setUsername("wrong username");
		l.setPassword("wrong password");
		assertNotEquals("MSK" + "1234", l.getUsername() + l.getPassword());
	}

	@When("the logistic Company tries logs in")
	public void the_logistic_Company_tries_logs_in() {
		response = l.logIn(false);
	}
	
/*	@Then("an error-message is displayed saying {string}")
	public void an_error_message_is_displayed_saying(String s) {
		assertEquals(s, response.getNotifyMessage());
	} */
	
	@Then("the Logistic Company is not logged in")
	public void the_Logistic_Company_is_not_logged_in() {
		assertTrue(!l.isLoggedIn());
	}
}	
