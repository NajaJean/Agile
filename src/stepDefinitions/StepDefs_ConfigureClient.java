package stepDefinitions;

import static org.junit.Assert.assertEquals;

import core.Client;
import core.Database;
import core.DatabaseData;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_ConfigureClient {
	String[] oldClient;
	Client[] Clients;
	Database d = new Database("agileProject.accdb"); 
	NotifyObject response;
	ScenarioContext context;
	int id = 1;
	
	public StepDefs_ConfigureClient(ScenarioContext context) {
		this.context = context;
		this.Clients = DatabaseData.getClients();
	}

	@Given("a client")
	public void a_client() {		
		oldClient = new String[5];
		oldClient[0] = Clients[0].getUsername();
		oldClient[1] = Clients[0].getPassword();
		oldClient[2] = Clients[0].getName();
		oldClient[3] = Clients[0].getEmail();
		oldClient[4] = Clients[0].getAddress();

	}

	@When("a client configures its details")
	public void a_client_configures_its_details() {
		Clients[id].setUserName("bobby");
		Clients[id].setPassword("4321");
		Clients[id].setName("Bobby Smith");
		Clients[id].setEmail("bobby_smith@gmail.com");
		Clients[id].setAddress("135 Candy ln");
	}

	@Then("the client should have the new configured details")
	public void the_client_should_have_the_new_configured_details() {
		assertEquals(Clients[id].getUsername(), "bobby");
		assertEquals(Clients[id].getPassword(), "4321");
		assertEquals(Clients[id].getName(), "Bobby Smith");
		assertEquals(Clients[id].getEmail(), "bobby_smith@gmail.com");
		assertEquals(Clients[id].getAddress(), "135 Candy ln");
		response = Clients[id].configure();
		context.setResponse(response);
	}

	@Then("the details should be updated in the database")
	public void the_details_should_be_updated_in_the_database() {
		String user = Clients[id].getUsername();
		d.updateDatabase("Clients", "Username",user,Integer.toString(id));
		String pass = Clients[id].getPassword();
		d.updateDatabase("Clients", "Password",pass,Integer.toString(id));
		String name = Clients[id].getName();
		d.updateDatabase("Clients", "Name",name,Integer.toString(id));
		String email = Clients[id].getEmail();
		d.updateDatabase("Clients", "Email",email,Integer.toString(id));
		String address = Clients[id].getAddress();
		d.updateDatabase("Clients", "Address",address,Integer.toString(id));
		
		// Reset the database back
		d.updateDatabase("Clients", "Username",oldClient[0],Integer.toString(id));
		d.updateDatabase("Clients", "Password",oldClient[1],Integer.toString(id));
		d.updateDatabase("Clients", "Name",oldClient[2],Integer.toString(id));
		d.updateDatabase("Clients", "Email",oldClient[3],Integer.toString(id));
		d.updateDatabase("Clients", "Address",oldClient[4],Integer.toString(id));
	}
}
