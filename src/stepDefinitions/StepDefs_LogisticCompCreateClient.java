package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import ExternalData.Database;
import ExternalData.DatabaseData;
import core.Client;
import core.LogisticCompany;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefs_LogisticCompCreateClient {
	
	Database d = new Database("agileProject.accdb");
	Client C;
	LogisticCompany l; 
	Client[] Clients;
	NotifyObject response;
	ScenarioContext context;
	
	public StepDefs_LogisticCompCreateClient(ScenarioContext context) {
		this.context = context;
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
		d.addToDatabase(C);
	    
	}

	@Then("the client is created")
	public void the_client_is_created() {
		response = C.createNewClient();
		context.setResponse(response);	
	}
	
	@Then("is found in the database")
	public void is_found_in_the_database() {
		assertTrue(d.checkUser(C.getUsername(), C.getPassword()));
		d.removeFromDatabase(C); 
	}
}
