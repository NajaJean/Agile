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
		int clientLength = Database.lengthTable(clients);
		Clients = new Client[clientLength];
		for(int i = 0; i < clientLength; i++) {
			Clients[i] = new Client(clients[i+1][5],clients[i+1][6],clients[i+1][2],clients[i+1][3],clients[i+1][4]);
		}
		
		String[][] locations = d.getTable("Locations");
		int locLength = Database.lengthTable(locations);
		Locations = new Location[locLength];
		for(int i = 0; i < locLength; i++) {
			double[] gps = {Double.parseDouble(locations[i+1][3]),Double.parseDouble(locations[i+1][4])};
			Locations[i] = new Location(locations[i+1][2],gps);
		}
		
		String[][] environments = d.getTable("Environments");
		int enviroLength = Database.lengthTable(environments);;
		Enviros = new Environment[enviroLength];
		for(int i = 0; i < enviroLength; i++) {
			Enviros[i] = new Environment(Double.parseDouble(environments[i+1][2]),Double.parseDouble(environments[i+1][3]),Double.parseDouble(environments[i+1][4]));	
		}
		
		String[][] contents = d.getTable("Contents");
		int contentLength = Database.lengthTable(contents);;
		Contents = new Content[contentLength];
		for(int i = 0; i < contentLength; i++) {
			Contents[i] = new Content(contents[i+1][2],Environment.findEnviro(contents[i+1][3],Enviros),Double.parseDouble(contents[i+1][4]));
		}
		
		String[][] containers = d.getTable("Containers");
		int containerLength = Database.lengthTable(containers);;
		Containers = new Container[containerLength];
		for(int i = 0; i < containerLength; i++) {
			try {
				Containers[i] = new Container(Client.findClient(containers[i+1][2],Clients),Environment.findEnviro(containers[i+1][3],Enviros),Content.findContent(Integer.parseInt(containers[i+1][4]),Contents),Location.findLocation(Integer.parseInt(containers[i+1][5]), Locations));
			}
			catch(Exception e) {
				Containers[i] = new Container(Environment.findEnviro(containers[i+1][3],Enviros),Location.findLocation(Integer.parseInt(containers[i+1][5]), Locations));
			}
		}
		id = d.getEmptyContainer();	
	    assertNotEquals(0,id);
	}
		
	@When("the client books a container by filling it with a content")
	public void the_client_books_a_container_by_filling_it_with_a_content() {

		//context.setResponse(response);
		con = Container.findContainer(id, Containers); // Finds the empty container
		con.setClientofContainer(Clients[1]); // Assign to client
		String containerClientID = Integer.toString(con.getClientofContainer().getID());
		d.updateDatabase("Containers", "Client_ID",containerClientID , Integer.toString(id));
		//fill container
		content = Content.findContent("Bananas", Contents);
		con.setContainerContent(content);
		response = new NotifyObject(31, "Container is succesfully booked");
		
		d.updateDatabase("Containers", "Content_ID", Integer.toString(content.getContentID()), Integer.toString(con.getContainerID()));
	}
	
	@Then("the first empty container existing in the database should be assigned to the client")
	public void the_first_empty_container_existing_in_the_database_should_be_assigned_to_the_client() {
		assertEquals(con.getClientofContainer(),Clients[1]);
	}

	@Then("the container should contain the content in the database")
	public void the_container_should_contain_the_content_in_the_database() {
		String database = d.queryDatabase("SELECT Content_ID FROM Containers WHERE ID = "+id);
		String program = Integer.toString(Containers[id-1].getContainerContent().getContentID()); 
		assertEquals(database,program);
		d.updateDatabase("Containers", "Content_ID", Integer.toString(con.getContainerID()));
	}
	
	
	//2rd scenario
	@Given("that there does not exist an empty container in the database")
	public void that_there_does_not_exist_an_empty_container_in_the_database() {
		id = d.getEmptyContainer();	
	    assertEquals(0,id);
	    d.updateDatabase("Containers", "Client_ID", Integer.toString(4));
	}

	@When("the Client tries to book a container")
	public void the_Client_tries_to_book_a_container() {
		response = new NotifyObject(33, "No empty containers available");
		//context.setResponse(response);
	}
	@Then ("message is displayed says {string}")
	public void Then_message_is_displayed_says(String s) {
		assertEquals(s, response.getNotifyMessage());
	}

}
