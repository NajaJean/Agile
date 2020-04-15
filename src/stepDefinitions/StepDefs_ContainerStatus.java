package stepDefinitions;

import static org.junit.Assert.assertEquals;

import core.Client;
import core.Container;
import core.Content;
import core.Database;
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
	Environment[] Enviros;
	Content[] Contents;
	Container[] Containers; 
	
	Content newContent = new Content("Apples", new Environment(15.0,15.0,15.0),0.1);

	@Given("a client with a container")
	public void a_client_with_a_container() {
		//Import database
		String[][] clients = d.getTable("Clients");
		int clientLength = 0;
		for(int i = 1; i < clients.length; i++) {
			if (!(clients[i][1] == null)) {
				clientLength++;
			}
		}
		Clients = new Client[clientLength];
		for(int i = 0; i < clientLength; i++) {
			Clients[i] = new Client(clients[i+1][5],clients[i+1][6],clients[i+1][2],clients[i+1][3],clients[i+1][4]);
		}
		
		String[][] environments = d.getTable("Environments");
		int enviroLength = 0;
		for(int i = 1; i < environments.length; i++) {
			if (!(environments[i][1] == null)) {
				enviroLength++;
			}
		}
		Enviros = new Environment[enviroLength];
		for(int i = 0; i < enviroLength; i++) {
			Enviros[i] = new Environment(Double.parseDouble(environments[i+1][2]),Double.parseDouble(environments[i+1][3]),Double.parseDouble(environments[i+1][4]));	
		}
		
		String[][] contents = d.getTable("Contents");
		int contentLength = 0;
		for(int i = 1; i < contents.length; i++) {
			if (!(contents[i][1] == null)) {
				contentLength++;
			}
		}
		Contents = new Content[contentLength];
		for(int i = 0; i < contentLength; i++) {
			Contents[i] = new Content(contents[i+1][2],Environment.findEnviro(contents[i+1][3],Enviros),Double.parseDouble(contents[i+1][4]));
		}
		
		String[][] containers = d.getTable("Containers");
		int containerLength = 0;
		for(int i = 1; i < containers.length; i++) {
			if (!(containers[i][1] == null)) {
				containerLength++;
			}
		}
		Containers = new Container[containerLength];
		for(int i = 0; i < containerLength; i++) {
			if(containers[i+1][2] == null && containers[i+1][4] == null) {
				Containers[i] = new Container(Environment.findEnviro(containers[i+1][3],Enviros));
			}
			else {
				Containers[i] = new Container(Client.findClient(containers[i+1][2],Clients),Environment.findEnviro(containers[i+1][3],Enviros),Content.findContent(containers[i+1][4],Contents));
			}
		}
		
		
	    int clientID = 2;
	    int containerID = 1;
	    c = Clients[clientID];
	    con = Containers[containerID];
	}

	@Given("a logistic company")
	public void a_logistic_company() {
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
