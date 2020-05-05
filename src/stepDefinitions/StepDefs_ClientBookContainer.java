package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import core.ArraySearch;
import core.Client;
import core.Container;
import core.Content;
import core.Database;
import core.DatabaseData;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_ClientBookContainer {
	
	Database d = new Database("agileProject.accdb"); 
	int id;
	Container con;
	Content content;
	
	NotifyObject response;
	ScenarioContext context;
	
	Container[] Containers;
	Client[] Clients;
	Content[] Contents;
	
	ArraySearch search;
	
	public StepDefs_ClientBookContainer(ScenarioContext context) {
		this.context = context;
		this.Containers = DatabaseData.getContainers();
		this.Clients = DatabaseData.getClients();
		this.Contents = DatabaseData.getContents();
		this.search = new ArraySearch();
	}
	
	@Given("that there exists an empty container in the database")
	public void that_there_exists_an_empty_container_in_the_database() {
		id = d.getEmptyContainer();
	    assertNotEquals(0,id);
	    System.out.println(id);
	    System.out.println(Containers.length);
	    System.out.println(Containers[3].getContainerID());
	}
		
	@When("the client books a container by filling it with a content")
	public void the_client_books_a_container_by_filling_it_with_a_content() {
		search.setSearch(new Container());
		int containerIDX = search.findIDX(id, Containers); // Finds the empty container
		con = Containers[containerIDX]; 
		con.setClientofContainer(Clients[1]); // Assign to client
		String containerClientID = Integer.toString(con.getClientofContainer().getID());
		d.updateDatabase("Containers", "Client_ID",containerClientID , Integer.toString(con.getContainerID()));

		// Fill container
		search.setSearch(new Content());
		int contentIDX = search.findIDX("Bananas", Contents);
		content = Contents[contentIDX];
		con.setContainerContent(content);
		response = new NotifyObject(31, "Container is succesfully booked");
		context.setResponse(response);
		
		d.updateDatabase("Containers", "Content_ID", Integer.toString(content.getContentID()), Integer.toString(con.getContainerID()));
	}
	
	@Then("the first empty container existing in the database should be assigned to the client")
	public void the_first_empty_container_existing_in_the_database_should_be_assigned_to_the_client() {
		assertEquals(con.getClientofContainer(), Clients[1]);
	}

	@Then("the container should contain the content in the database")
	public void the_container_should_contain_the_content_in_the_database() {
		String database = d.queryDatabase("SELECT Content_ID FROM Containers WHERE ID = "+id);
		String program = Integer.toString(Containers[id-1].getContainerContent().getContentID()); 
		assertEquals(database,program);
		d.updateDatabase("Containers", "Content_ID", Integer.toString(con.getContainerID()));
		d.updateDatabase("Containers", "Client_ID", Integer.toString(con.getContainerID()));
	}
	
	@Given("that there does not exist an empty container in the database")
	public void that_there_does_not_exist_an_empty_container_in_the_database() {
		id = 0;
	    assertEquals(0,id);
	}

	@When("the Client tries to book a container")
	public void the_Client_tries_to_book_a_container() {
		response = new NotifyObject(33, "No empty containers available");
		context.setResponse(response);
	}
}
