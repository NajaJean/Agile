package stepDefinitions;

import core.Client;
import core.Container;
import core.Database;
import core.Environment;
import core.Content;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_ClientBookContainer {
	
	Database d = new Database("C:\\Users\\-\\eclipse-workspace\\Agile\\target\\agileProject.accdb");
	
	Client[] Clients = null;
	Environment[] Enviros = null;
	Content[] Contents = null;
	Container[] Containers = null;
	
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
			Containers[i] = new Container(findClient(containers[i][1],Clients),Environment.findEnviro(contents[i][2],Enviros),Content.findContent(containers[i][3],Contents));
		}
	}
	
	private Client findClient(String string, Client[] clients) {
		// TODO Auto-generated method stub
		return null;
	}

	@Given("that there exists an empty container in the database")
	public void that_there_exists_an_empty_container_in_the_database() {
	    assertNotEquals(d.getEmptyContainer(), null);
	    con = d.getEmptyContainer();
	}

	@When("the Client books a container")
	public void the_Client_books_a_container() {
	    d.bookEmptyContainer(C);
	}

	@Then("the first empty container existing in the database should be assigned to the Client")
	public void the_first_empty_container_existing_in_the_database_should_be_assigned_to_the_Client() {
	    con.getClientofContainer();
	}

	@Given("that there does not exist an empty container in the database")
	public void that_there_does_not_exist_an_empty_container_in_the_database() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the Client tries to book a container")
	public void the_Client_tries_to_book_a_container() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("an error-message is displayed")
	public void an_error_message_is_displayed() {
	    System.out.println("We havn't got any containers available at the moment");
	}


}
