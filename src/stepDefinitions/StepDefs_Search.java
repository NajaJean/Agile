package stepDefinitions;

import static org.junit.Assert.assertEquals;

import core.ArraySearch;
import core.Client;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_Search {

	ArraySearch search;
	String email;
	Client[] Clients;
	Client client;
	
	//Given a Logistic Company
	
	@Given("a client email")
	public void a_client_email() {		
		email = Clients[0].getEmail();
		this.search = new ArraySearch(new Client());
	}

	@When("the logistic company types in the email")
	public void the_logistic_company_types_in_the_email() {
	    int clientIDX = search.findIDX(email, Clients);
	    client = Clients[clientIDX];  
	}

	@Then("the client with the typed email will be found")
	public void the_client_with_the_typed_email_will_be_found() {
	    assertEquals(email, client);
	}

	// Given a client 
	
	@When("the client types the content of the container")
	public void the_client_types_the_content_of_the_container() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the containers belonging to the client and with the desired content is found.")
	public void the_containers_belonging_to_the_client_and_with_the_desired_content_is_found() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}


}
