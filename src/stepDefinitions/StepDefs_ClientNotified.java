package stepDefinitions;

import static org.junit.Assert.assertEquals;

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

public class StepDefs_ClientNotified {
	
	Client client;
	Container clientContainer;
	Content containerContent;
	
	ContainerJourney clientContainerJourney;
	Location startLocation;
	Location endLocation;
	
	NotifyObject notification;
	
	@Given("a client with username {string} and password {string} has a container")
	public void a_client_with_username_and_password_has_a_container(String username, String password) {
		client = new Client(username, password);
		// Does not have an environment or contents yet, just a client that booked a container
		clientContainer = new Container(client);
	}
	
	@Given("the container has content {string} which has required environment and threshold of {double}")
	public void the_container_has_content_which_has_required_environment_and_threshold_of(String contentName, Double threshold) {
		Environment requiredEnvironment = new Environment(5.0, 1.0, 0.9);
	    containerContent = new Content(contentName, requiredEnvironment, threshold);
	    clientContainer.setContainerContent(containerContent);
	}

	@When("the environment in the container is outside the contents threshold")
	public void the_environment_in_the_container_is_outside_the_contents_threshold() {
		Environment containerEnvironment = new Environment(7.0, 1.0, 0.9);
	    clientContainer.setContainerEnvironment(containerEnvironment);
	    
	    notification = clientContainer.getContainerEnvironment().checkEnvironment(clientContainer.getContainerContent());  
	}

	@Then("container management system notifies client of invalid environment")
	public void container_management_system_notifies_client_of_invalid_environment() {
		assertEquals(notification.getNotifyMessage(), "Client is notified of invalid environment");
	}
	
	
	@Given("the container is starting its journey at {string} with coords {double} and {double}")
	public void the_container_is_starting_its_journey_at_with_coords_and(String start, double latitude, double longitude) {
	    startLocation = new Location(start, new double[]{latitude, longitude});
	}

	@Given("the container has a final destination at {string} with coords {double} and {double}")
	public void the_container_has_a_final_destination_at_with_coords_and(String end, double latitude, double longitude) {
	    endLocation = new Location(end, new double[]{latitude, longitude});
	    
	    clientContainerJourney = new ContainerJourney(startLocation, endLocation, clientContainer);
	}

	@When("the container reaches its final destination")
	public void the_container_reaches_its_final_destination() {
		notification = clientContainerJourney.setCurrentLocation(endLocation);
	}

	@Then("container management system notifies client container arrived")
	public void container_management_system_notifies_client_container_arrived() {
	    assertEquals(notification.getNotifyMessage(), "Client is notified of arrival");
	}

}
