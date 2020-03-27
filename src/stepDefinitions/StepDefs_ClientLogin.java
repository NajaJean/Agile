package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class StepDefs_ClientLogin {

	@Given("I navigate to the login page")
	public void i_navigate_to_the_login_page() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		
		System.out.println("I navigate to the login page");
	}

	@Given("I enter the username {string} and password {string} as client")
	public void i_enter_the_username_and_password_as_client(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}

	@Given("I click login button")
	public void i_click_login_button() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}

	@Then("I should see the Client Menu page")
	public void i_should_see_the_Client_Menu_page() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}
}
