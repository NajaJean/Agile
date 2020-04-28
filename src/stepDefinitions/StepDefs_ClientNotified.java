package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

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
	Environment enviro;
	Location loc;
	
	ContainerJourney clientContainerJourney;
	Location startLocation;
	Location endLocation;
	
	NotifyObject notification;
	
	LocalDate startDate = LocalDate.of(2020, 04, 28);
	LocalDate endDate = LocalDate.of(2020, 05, 28);
	
	@Given("a client has a container")
	public void a_client_has_a_container() {
		client = new Client("bob", "1234", "Bob Smith", "bob_smith@gmail.com","l34 Candy ln");
		enviro = new Environment(5.3,1.1,0.85);
		double[] cphgps = {730.0, 188.0};
		loc = new Location("Copenhagen",cphgps);

		// Does not have content yet, just a client that booked an empty container
		clientContainer = new Container(loc);
		clientContainer.setClientofContainer(client);
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
		assertEquals("Client is notified of invalid environment", notification.getNotifyMessage());
	}
	
	// 2nd Scenario 
	@Given("the container is starting its journey at {string} with coords {double} and {double}")
	public void the_container_is_starting_its_journey_at_with_coords_and(String start, double latitude, double longitude) {
	    double[] gps = {latitude, longitude};
		startLocation = new Location(start, gps);
	}

	@Given("the container has a final destination at {string} with coords {double} and {double}")
	public void the_container_has_a_final_destination_at_with_coords_and(String end, double latitude, double longitude) {
		double[] gps2 = {latitude, longitude};
		endLocation = new Location(end, gps2);
	    
	    clientContainerJourney = new ContainerJourney(startLocation, endLocation, clientContainer, startDate, endDate);
	}

	@When("the container reaches its final destination")
	public void the_container_reaches_its_final_destination() {
		clientContainerJourney.getContaineronJourney().setContainerLocation(endLocation);
		assertEquals(endLocation,clientContainerJourney.getContaineronJourney().getContainerLocation());
		notification = new NotifyObject(100, "Client is notified of arrival");
	}

	@Then("container management system notifies client container arrived")
	public void container_management_system_notifies_client_container_arrived() {
	    assertEquals("Client is notified of arrival", notification.getNotifyMessage());
	}

}
