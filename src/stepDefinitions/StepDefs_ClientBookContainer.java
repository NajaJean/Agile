package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import core.Client;
import core.Container;
import core.Content;
import core.Database;
import core.Environment;
import core.NotifyObject;
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

	/*
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
	*/
	
	@Given("that there exists an empty container in the database")
	public void that_there_exists_an_empty_container_in_the_database() {
		
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
		
		
		id = d.getEmptyContainer();	
	    assertNotEquals(id, 0);
	}

	@When("the Client books a container")
	public void the_Client_books_a_container() {
		con = Container.findContainer(Integer.toString(id), Containers); // Finds the empty container
		con.setClientofContainer(Clients[1]); // Assign to client
		String s = con.toString();
		d.addToDatabase("Containers", s);
		response = con.checkBookingOfContainer(id);
	}

	@Then("the first empty container existing in the database should be assigned to the Client")
	public void the_first_empty_container_existing_in_the_database_should_be_assigned_to_the_Client() {
		assertEquals(con.getClientofContainer(),Clients[1]);
	}

/*	@Then("message is displayed saying {string}")
	public void message_is_displayed_saying(String string) {
		assertEquals(string, response.getNotifyMessage());
	} */

	@Given("that there does not exist an empty container in the database")
	public void that_there_does_not_exist_an_empty_container_in_the_database() {
		id = d.getEmptyContainer();	
	    assertEquals(0, id);
	}

	@When("the Client tries to book a container")
	public void the_Client_tries_to_book_a_container() {
		response = con.checkBookingOfContainer(id);
	}

/*	@Then("an error-message is displayed saying {string}")
	public void an_error_message_is_displayed_saying(String string) {
		assertEquals(string, response.getNotifyMessage());
	} */
}
