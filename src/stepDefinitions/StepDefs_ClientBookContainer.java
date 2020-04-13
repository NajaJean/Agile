package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.nio.file.Paths;

import core.Client;
import core.Container;
import core.Database;
import core.Environment;
import core.NotifyObject;
import core.Content;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_ClientBookContainer {
	
	Database d = new Database("agileProject.accdb"); 
	int id;
	Container con;
	
	Client[] Clients;
	Environment[] Enviros;
	Content[] Contents;
	Container[] Containers; 
	
	NotifyObject response;
	
	public void import_database() {
		String[][] clients = d.getTable("Clients");
		Clients = new Client[clients.length];
		for(int i = 0; i < clients.length; i++) {
			Clients[i] = new Client(clients[i][4],clients[i][5],clients[i][1],clients[i][2],clients[i][3]);
		}
		String[][] environments = d.getTable("Environments");
		Enviros = new Environment[environments.length];
		for(int i = 0; i < environments.length; i++) {
			Enviros[i] = new Environment(Double.parseDouble(environments[i][1]),Double.parseDouble(environments[i][2]),Double.parseDouble(environments[i][3]));
		}
		String[][] contents = d.getTable("Contents");
		Contents = new Content[contents.length];
		for(int i = 0; i < contents.length; i++) {
			Contents[i] = new Content(contents[i][1],Environment.findEnviro(contents[i][2],Enviros),Double.parseDouble(contents[i][3]));
		}
		
		String[][] containers = d.getTable("Contatiners");
		Containers = new Container[containers.length];
		for(int i = 0; i < containers.length; i++) {
			Containers[i] = new Container(Client.findClient(containers[i][1],Clients),Environment.findEnviro(contents[i][2],Enviros),Content.findContent(containers[i][3],Contents));
		}
	}
	
	@Given("that there exists an empty container in the database")
	public void that_there_exists_an_empty_container_in_the_database() {
		id = d.getEmptyContainer();	
	    assertNotEquals(id, 0);  
	}

	@When("the Client books a container")
	public void the_Client_books_a_container() {
		con = Container.findContainer(Integer.toString(id), Containers); // Finds the empty container
		con.setClientofContainer(Clients[1]); // Assign to client
		response = con.checkBookingOfContainer(id);
	}

	@Then("the first empty container existing in the database should be assigned to the Client")
	public void the_first_empty_container_existing_in_the_database_should_be_assigned_to_the_Client() {
	    assertEquals(con.getClientofContainer(),Clients[1].getID());
	}
	
	@Then("message is displayed saying {string}")
	public void message_is_displayed_saying(String s) {
		assertEquals(s, response.getNotifyMessage());
	}

	@Given("that there does not exist an empty container in the database")
	public void that_there_does_not_exist_an_empty_container_in_the_database() {
		id = d.getEmptyContainer();	
	    assertEquals(id, 0);
	}

	@When("the Client tries to book a container")
	public void the_Client_tries_to_book_a_container() {
		response = con.checkBookingOfContainer(id);
	}

	@Then("an error-message is displayed saying {string}")
	public void an_error_message_is_displayed_saying(String s) {
		assertEquals(s, response.getNotifyMessage());
	}
}
