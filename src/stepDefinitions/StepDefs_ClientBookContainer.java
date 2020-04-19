package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import core.Client;
import core.Container;
import core.Content;
import core.Database;
import core.Location;
import core.Environment;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_ClientBookContainer {

	Database d = new Database("agileProject.accdb"); 
	int id;
	Container con;
	Content content;
	
	Client[] Clients;
	Environment[] Enviros;
	Content[] Contents;
	Container[] Containers; 
	Location[] Locations;
	
	NotifyObject response;
	ScenarioContext context;

	//1st scenario
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
		
		String[][] locations = d.getTable("Locations");
		int locLength = 0;
		for(int i = 1; i < locations.length; i++) {
			if (!(locations[i][1] == null)) {
				locLength++;
			}
		}
		Locations = new Location[locLength];
		for(int i = 0; i < locLength; i++) {
			double[] gps = {Double.parseDouble(locations[i+1][3]),Double.parseDouble(locations[i+1][4])};
			Locations[i] = new Location(locations[i+1][2],gps);
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
				Containers[i] = new Container(Environment.findEnviro(containers[i+1][3],Enviros),Location.findLocation(containers[i+1][3], Locations));
			}
			else {
				Containers[i] = new Container(Client.findClient(containers[i+1][2],Clients),Environment.findEnviro(containers[i+1][3],Enviros),Content.findContent(containers[i+1][4],Contents),Location.findLocation(containers[i+1][3], Locations));
			}
		}
		
		id = d.getEmptyContainer();	
	    assertNotEquals(id, 0);
	}
	
	@When("the client tries to book a container by filling it with no content")
	public void the_client_tries_to_book_a_container_by_filling_it_with_no_content() {		
		response = con.responseFillContainer(content);
	}
			
	@Then("the container should not be assigned to the client")
	public void the_container_should_not_be_assigned_to_the_client() {
		assertNotEquals(con.getClientofContainer(),Clients[1]);		
	}
			
	//2nd Scenario
	@When("the client books a container by filling it with a content")
	public void the_client_books_a_container_by_filling_it_with_a_content() {
		con = Container.findContainer(Integer.toString(id), Containers); // Finds the empty container
		con.setClientofContainer(Clients[1]); // Assign to client
		String containerClientID = Integer.toString(con.getClientofContainer().getID());
		d.updateDatabase("Containers", "Client_ID",containerClientID , Integer.toString(id));
		response = con.checkBookingOfContainer(id);
		//context.setResponse(response);
		
		//fill container
		content = Content.findContent("Bananas", Contents);
		con.setContainerContent(content);
		response = con.responseFillContainer(content);
		
		d.updateDatabase("Containers", "Content", Integer.toString(content.getContentID()), Integer.toString(con.getContainerID()));
	}
	
	@Then("the first empty container existing in the database should be assigned to the client")
	public void the_first_empty_container_existing_in_the_database_should_be_assigned_to_the_client() {
		assertEquals(con.getClientofContainer(),Clients[1]);
	}

	@Then("the container should contain the content in the database")
	public void the_container_should_contain_the_content_in_the_database() {
		//Reload container table in database
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
				Containers[i] = new Container(Environment.findEnviro(containers[i+1][3],Enviros),Location.findLocation(containers[i+1][3], Locations));
			}
			else {
				Containers[i] = new Container(Client.findClient(containers[i+1][2],Clients),Environment.findEnviro(containers[i+1][3],Enviros),Content.findContent(containers[i+1][4],Contents),Location.findLocation(containers[i+1][3], Locations));
			}
		}
		
		assertEquals(content,Containers[id].getContainerContent());
	}
	
	
	//3rd scenario
	@Given("that there does not exist an empty container in the database")
	public void that_there_does_not_exist_an_empty_container_in_the_database() {
		id = d.getEmptyContainer();	
	    assertEquals(id, 0);
	}

	@When("the Client tries to book a container")
	public void the_Client_tries_to_book_a_container() {
		response = con.checkBookingOfContainer(id);
		//context.setResponse(response);
	}

}
