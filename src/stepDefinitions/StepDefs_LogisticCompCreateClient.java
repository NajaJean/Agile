package stepDefinitions;

import static org.junit.Assert.assertTrue;

import core.Client;
import core.Database;
import core.DatabaseData;
import core.LogisticCompany;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefs_LogisticCompCreateClient {
	
	Database d = new Database("agileProject.accdb");
	Client C;
	LogisticCompany l; 
	Client[] Clients;
	
	public StepDefs_LogisticCompCreateClient() {
		this.Clients = DatabaseData.getClients();
	}
	
	@Given("a logistic company")
	public void a_logistic_company() {
	   l = new LogisticCompany();    
	}

	@Given("given a new client")
	public void given_a_new_client() {
		C = new Client("N", "4321", "Naja","najasemail@gmail.com","Rådhuspladsen 100");
	    
	}

	@When("a logistic company wants to create a new client")
	public void a_logistic_company_wants_to_create_a_new_client() {
		d.addToDatabase("Clients", C.toString(), C);
	    
	}

	@Then("the client is created and message displayed says {string}")
	public void the_client_is_created_and_message_displayed_says(String string) {
		// need notify
	    System.out.print("Client successfully created!");
	}
	
	@Then("is found in the database")
	public void is_found_in_the_database() {
		assertTrue(d.checkUser(C.getUserName(), C.getPassword()));
		d.removeFromDatabase("Clients", C.getID(), C); 
	}
}
