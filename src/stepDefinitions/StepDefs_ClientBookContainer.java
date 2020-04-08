package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
	
	Database dC = new Database("C:\\Users\\-\\eclipse-workspace\\Agile\\target\\agileProject.accdb");
	Database dI = new Database("C:\\Users\\-\\eclipse-workspace\\Agile\\target\\agileProject.accdb");
	int idC;
	int idI;
	Container con;
	
	Client[] ClientsC;
	Environment[] EnvirosC;
	Content[] ContentsC;
	Container[] ContainersC; 
	
	Client[] ClientsI;
	Environment[] EnvirosI;
	Content[] ContentsI;
	Container[] ContainersI; 
	
	NotifyObject response;
	
	public void import_databaseC() {
		String[][] clientsC = dC.getTable("Clients");
		ClientsC = new Client[clientsC.length];
		for(int i = 0; i < clientsC.length; i++) {
			ClientsC[i] = new Client(clientsC[i][4],clientsC[i][5],clientsC[i][1],clientsC[i][2],clientsC[i][3]);
		}
		String[][] environmentsC = dC.getTable("Environments");
		EnvirosC = new Environment[environmentsC.length];
		for(int i = 0; i < environmentsC.length; i++) {
			EnvirosC[i] = new Environment(Double.parseDouble(environmentsC[i][1]),Double.parseDouble(environmentsC[i][2]),Double.parseDouble(environmentsC[i][3]));
		}
		String[][] contentsC = dC.getTable("Contents");
		ContentsC = new Content[contentsC.length];
		for(int i = 0; i < contentsC.length; i++) {
			ContentsC[i] = new Content(contentsC[i][1],Environment.findEnviro(contentsC[i][2],EnvirosC),Double.parseDouble(contentsC[i][3]));
		}
		
		String[][] containersC = dC.getTable("Contatiners");
		ContainersC = new Container[containersC.length];
		for(int i = 0; i < containersC.length; i++) {
			ContainersC[i] = new Container(Client.findClient(containersC[i][1],ClientsC),Environment.findEnviro(contentsC[i][2],EnvirosC),Content.findContent(containersC[i][3],ContentsC));
		}
	}
	
	public void import_databaseI() {
		String[][] clientsI = dI.getTable("Clients");
		ClientsI = new Client[clientsI.length];
		for(int i = 0; i < clientsI.length; i++) {
			ClientsI[i] = new Client(clientsI[i][4],clientsI[i][5],clientsI[i][1],clientsI[i][2],clientsI[i][3]);
		}
		String[][] environmentsI = dI.getTable("Environments");
		EnvirosI = new Environment[environmentsI.length];
		for(int i = 0; i < environmentsI.length; i++) {
			EnvirosI[i] = new Environment(Double.parseDouble(environmentsI[i][1]),Double.parseDouble(environmentsI[i][2]),Double.parseDouble(environmentsI[i][3]));
		}
		String[][] contentsI = dI.getTable("Contents");
		ContentsI = new Content[contentsI.length];
		for(int i = 0; i < contentsI.length; i++) {
			ContentsI[i] = new Content(contentsI[i][1],Environment.findEnviro(contentsI[i][2],EnvirosI),Double.parseDouble(contentsI[i][3]));
		}
		
		String[][] containersI = dI.getTable("Contatiners");
		ContainersI = new Container[containersI.length];
		for(int i = 0; i < containersI.length; i++) {
			ContainersI[i] = new Container(Client.findClient(containersI[i][1],ClientsI),Environment.findEnviro(contentsI[i][2],EnvirosI),Content.findContent(containersI[i][3],ContentsI));
		}
	}
	
	@Given("that there exists an empty container in the database")
	public void that_there_exists_an_empty_container_in_the_database() {
		idC = dC.getEmptyContainer();	
	    assertNotEquals(idC, 0);  
	}

	@When("the Client books a container")
	public void the_Client_books_a_container() {
		con = Container.findContainer(Integer.toString(idC), ContainersC);
		con.setClientofContainer(ClientsC[1]);
		response = con.checkBookingOfContainer(idC);
	}

	@Then("the first empty container existing in the database should be assigned to the Client")
	public void the_first_empty_container_existing_in_the_database_should_be_assigned_to_the_Client() {
	    assertEquals(con.getClientofContainer(),ClientsC[1].getID());
	}
	
	@Then("message is displayed saying {string}")
	public void message_is_displayed_saying(String s) {
		assertEquals(s, response.getNotifyMessage());
	}

	@Given("that there does not exist an empty container in the database")
	public void that_there_does_not_exist_an_empty_container_in_the_database() {
		idI = dI.getEmptyContainer();	
	    assertEquals(idI, 0);
	}

	@When("the Client tries to book a container")
	public void the_Client_tries_to_book_a_container() {
		response = con.checkBookingOfContainer(idI);
	}

	@Then("an error-message is displayed saying {string}")
	public void an_error_message_is_displayed(String s) {
		assertEquals(s, response.getNotifyMessage());
	}


}
