package stepDefinitions;

import static org.junit.Assert.assertEquals;

import ExternalData.Database;
import ExternalData.DatabaseData;
import core.Client;
import core.Container;
import core.Content;
import core.Environment;
import core.LogisticCompany;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_ContainerStatus {
	
	Database d = new Database("agileProject.accdb"); 
	Client c;
	LogisticCompany l;
	Container con;
	
	Client[] Clients;
	Container[] Containers; 
	
	Content newContent;
	
	public StepDefs_ContainerStatus() {
		this.Clients = DatabaseData.getClients();
		this.Containers = DatabaseData.getContainers();
		newContent = new Content("Apples", new Environment(15.0,15.0,15.0),0.1);
	}

	@Given("a client with a container and a logistic company")
	public void a_client_with_a_container_and_a_logistic_company() {
	    int clientID = 2;
	    int containerID = 1;
	    c = Clients[clientID];
	    con = Containers[containerID];
	    
	    l = new LogisticCompany();
	}

	@When("a logistic company updates the current status of the container")
	public void a_logistic_company_updates_the_current_status_of_the_container() {
	    con.setContainerContent(newContent);
	}

	@Then("the updates should be visible for the client")
	public void the_updates_should_be_visible_for_the_client() {
	    assertEquals(newContent, con.getContainerContent());
	}
}
