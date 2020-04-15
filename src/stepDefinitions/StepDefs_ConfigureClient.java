package stepDefinitions;

import static org.junit.Assert.assertEquals;

import core.Client;
import core.Database;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_ConfigureClient {
	Client[] Clients;
	Database d = new Database("agileProject.accdb"); 
	NotifyObject response;

	@Given("a client")
	public void a_client() {
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

	}

	@When("a client configures its details")
	public void a_client_configures_its_details() {
		Clients[1].setUserName("bobby");
		Clients[1].setPassword("4321");
		Clients[1].setName("Bobby Smith");
		Clients[1].setEmail("bobby_smith@gmail.com");
		Clients[1].setAddress("135 Candy ln");
	}

	@Then("the client should have the new configured details")
	public void the_client_should_have_the_new_configured_details() {
		assertEquals(Clients[1].getUserName(), "bobby");
		assertEquals(Clients[1].getPassword(), "4321");
		assertEquals(Clients[1].getName(), "Bobby Smith");
		assertEquals(Clients[1].getEmail(), "bobby_smith@gmail.com");
		assertEquals(Clients[1].getAddress(), "135 Candy ln");
		response = Clients[1].configure();
	}

	@Then("the details should be updated in the database")
	public void the_details_should_be_updated_in_the_database() {


	}

	@Then("a confirmation-message is displayed: {string}")
	public void a_confirmation_message_is_displayed(String string) {
		assertEquals(string, response.getNotifyMessage()); 
	}
}
