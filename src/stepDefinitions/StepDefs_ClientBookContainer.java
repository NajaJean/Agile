package stepDefinitions;

import core.Client;
import core.Container;
import core.Database;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_ClientBookContainer {
	
	Client C = new Client("bob", "1234", "Bob Smith", "bob_smith@gmail.com","l34 Candy ln");
	Database d = new Database("C:\\Users\\-\\eclipse-workspace\\Agile\\target\\agileProject.accdb");
	Container con;
	
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
