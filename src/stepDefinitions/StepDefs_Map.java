package stepDefinitions;

import static org.junit.Assert.assertEquals;

import UI.Map;
import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Content;
import core.Environment;
import core.Location;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_Map {
	
	Client client;
	Container clientContainer;
	Content containerContent;
	
	ContainerJourney[] clientContainerJourney;
	Location startLocation;
	Location endLocation;
	Map wmap;
	
	
	@Given("the client has a choosen container on a journey")
	public void the_client_has_a_choosen_container_on_a_journey(Container clientContainer) {
		client = new Client("username", "password");
		// Does not have an environment or contents yet, just a client that booked a container
		clientContainer = new Container(client);
		ContainerJourney[] contJs = {new ContainerJourney(startLocation, endLocation, clientContainer)};
	}

	@When("the client looks on the map")
	public void the_client_looks_on_the_map() {
		
		}

	@Then("the container appears on the map")
	public void container_appears_on_the_map() {
	}
	
	@Given("the client has some containers on a journey")
	public void the_client_has_some_containers_on_a_journey(Container clientContainer) {
		client = new Client("username", "password");
		// Does not have an environment or contents yet, just a client that booked a container
		clientContainer = new Container(client);
		ContainerJourney[] contJs = {new ContainerJourney(startLocation, endLocation, clientContainer)};
	}

	@When("the client looks on the map")
	public void the_client_looks_on_the_map() {
		
		}

	@Then("all the containers appear on the map")
	public void all_thecontainer_appear_on_the_map() {
	}


}
