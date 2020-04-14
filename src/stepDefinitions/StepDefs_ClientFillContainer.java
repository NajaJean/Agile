package stepDefinitions;

import static org.junit.Assert.assertNotEquals;

import core.Client;
import core.Database;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_ClientFillContainer {
	
	Client C = new Client("bob", "1234", "Bob Smith", "bob_smith@gmail.com","l34 Candy ln"); 
	Database d = new Database("agileProject.accdb"); 
	int ConID;
	
	@Given("that a client has an empty container")
	public void that_a_client_has_an_empty_container() {
		ConID = d.clientEmptyContainer(C.getID());
		assertNotEquals(ConID, 0);
	}

	@Given("some content \\(Name of content, required environment, threshold it is able to withstand, start location, end location)")
	public void some_content_Name_of_content_required_environment_threshold_it_is_able_to_withstand_start_location_end_location() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the client fills a container with the content")
	public void the_client_fills_a_container_with_the_content() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the container contains the content")
	public void the_container_contains_the_content() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the container should no longer be empty in the database")
	public void the_container_should_no_longer_be_empty_in_the_database() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("a success-message is displayed {string}")
	public void a_success_message_is_displayed(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	// that a client has an empty container

	@Given("no content")
	public void no_content() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the client tries to fill the container")
	public void the_client_tries_to_fill_the_container() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the container filling fails")
	public void the_container_filling_fails() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("an error-message is displayed: {string}")
	public void an_error_message_is_displayed(String string) {
		throw new io.cucumber.java.PendingException();
	} 
}
