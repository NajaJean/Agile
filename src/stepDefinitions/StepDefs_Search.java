package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import ExternalData.DatabaseData;
import core.ArraySearch;
import core.Client;
import core.Container;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_Search {

	ArraySearch search;
	String email;
	Client[] Clients = DatabaseData.getClients();
	Client client;
	String content;
	Container[] containers = DatabaseData.getContainers();
	Container[] clientContainers;
	
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
	    assertEquals(email, client.getEmail());
	}

	// Given a client 
	
	@When("the client types the content of the container")
	public void the_client_types_the_content_of_the_container() {
	    content = "Mangoes";
	}

	@Then("the containers belonging to the client and with the desired content is found.")
	public void the_containers_belonging_to_the_client_and_with_the_desired_content_is_found() {
		clientContainers = Clients[0].findClientContentContainers(content, containers);
		Container[] conSet = {containers[0], containers[1]};
		Set<Container> set1 = new HashSet<Container>(Arrays.asList(conSet));
		Set<Container> set2 = new HashSet<Container>(Arrays.asList(clientContainers));
		assertTrue(set1.equals(set2));
		
	}


}
