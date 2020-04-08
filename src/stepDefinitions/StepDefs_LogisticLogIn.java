import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import core.LogisticCompany;
import static org.junit.Assert.assertNotEquals;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_LogisticLogIn {
	
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
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
	}
	
	@Then("the Logistic Company is logged in")
	public void the_Logistic_Company_is_logged_in() {
		assertTrue(l.isLoggedIn());
	}

	@Given("the username is {string} or the password is {string}")
	public void the_username_is_or_the_password_is(String string, String string2) {
		assertNotEquals("MSK" + "1234", string + string2);
	}

	@When("the logistic Company tries logs in")
	public void the_logistic_Company_tries_logs_in() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
	}
	
	@Then("the Logistic Company is not logged in")
	public void the_Logistic_Company_is_not_logged_in() {
		assertTrue(!l.isLoggedIn());
	}
}	
