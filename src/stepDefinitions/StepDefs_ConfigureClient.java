import static org.junit.Assert.assertEquals;

import core.Client;
import core.Database;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_ConfigureClient {

	Client C;
	Database d = new Database("agileProject.accdb"); 
	NotifyObject response;
	
	@Given("a client")
	public void a_client() {
		C = new Client("bob", "1234", "Bob Smith", "bob_smith@gmail.com","l34 Candy ln"); 
	}

	@When("a client configures its details")
	public void a_client_configures_its_details() {
	    C.setUserName("bobby");
	    C.setPassword("4321");
	    C.setName("Bobby Smith");
	    C.setEmail("bobby_smith@gmail.com");
	    C.setAddress("135 Candy ln");
	}

	@Then("the client should have the new configured details")
	public void the_client_should_have_the_new_configured_details() {
	    assertEquals(C.getUserName(), "bobby");
	    assertEquals(C.getPassword(), "4321");
	    assertEquals(C.getName(), "Bobby Smith");
	    assertEquals(C.getEmail(), "bobby_smith@gmail.com");
	    assertEquals(C.getAddress(), "135 Candy ln");
	    response = C.configure();
	}

	@Then("the details should be updated in the database")
	public void the_details_should_be_updated_in_the_database() {
	    
		
	}

	@Then("a confirmation-message is displayed: {string}")
	public void a_confirmation_message_is_displayed(String string) {
		assertEquals(string, response.getNotifyMessage()); 
	}
}
