package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import core.LogisticCompany;
import core.NotifyObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_LogisticLogIn {
	
	private ScenarioContext context;
	
	public StepDefs_LogisticLogIn(ScenarioContext context) {
		this.context = context;
	}
	
	NotifyObject response;
	LogisticCompany l = new LogisticCompany();
	
	@Given("that the Logistic Company is not logged in")
	public void that_the_Logistic_Company_is_not_logged_in() {
		assertFalse(l.isLoggedIn());
	}

	@Given("the username is {string} and password is {string}")
	public void the_username_is_and_password_is(String string, String string2) {
	    assertEquals(l.getUsername(), string);
	    assertEquals(l.getPassword(), string2);
	    assertTrue(l.isValidLogin(string,string2));
	}

	@When("the logistic Company logs in")
	public void the_logistic_Company_logs_in() {
		response = l.logIn(true);
		context.setResponse(response);
	}
	
	@Then("the Logistic Company is logged in")
	public void the_Logistic_Company_is_logged_in() {
		assertTrue(l.isLoggedIn());
	}

	@Given("the username or the password is wrong")
	public void the_username_or_the_password_is_wrong() {
		assertFalse(l.isValidLogin("wrong username","wrong password"));
		assertFalse(l.isValidLogin("MSK","wrong password"));
		assertFalse(l.isValidLogin("wrong username","1234"));
	}

	@When("the logistic Company tries logs in")
	public void the_logistic_Company_tries_logs_in() {
		response = l.logIn(false);
		context.setResponse(response);
	}
	
	@Then("the Logistic Company is not logged in")
	public void the_Logistic_Company_is_not_logged_in() {
		assertFalse(l.isLoggedIn());
	}
}	
