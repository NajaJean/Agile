package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import core.ArraySearch;
import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Content;
import core.DatabaseData;
import core.Environment;
import core.Location;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_ClientNotified {
	
	Client client;
	Container clientContainer;
	
	Client[] Clients;
	Container[] Containers;
	Environment[] Environments;
	Location[] Locations;
	ContainerJourney[] Journies;
	
	ArraySearch search;
	
	ContainerJourney clientContainerJourney;
	
	NotifyObject notification;
	
	LocalDate startDate = LocalDate.of(2020, 04, 28);
	LocalDate endDate = LocalDate.of(2020, 05, 28);
	
	public StepDefs_ClientNotified() {
		this.Locations = DatabaseData.getLocations();
		this.Clients = DatabaseData.getClients();
		this.Containers = DatabaseData.getContainers();
		this.Environments = DatabaseData.getEnvironments();
		this.Journies = DatabaseData.getJournies();
		this.search = new ArraySearch();
	}
	
	@Given("a client has a container")
	public void a_client_has_a_container() {
		client = Clients[0];
		search.setSearch(new Container());
		int containerIDX = search.findIDX(String.valueOf(client.getID()), Containers);
		clientContainer = Containers[containerIDX];
	}
	
	@When("the environment in the container is outside the contents threshold")
	public void the_environment_in_the_container_is_outside_the_contents_threshold() {
		Environment containerEnvironment = Environments[8];
		clientContainer.setContainerEnvironment(containerEnvironment);
	    
	   	notification = clientContainer.getContainerEnvironment().checkEnvironment(clientContainer.getContainerContent());  
	}

	@Then("container management system notifies client of invalid environment")
	public void container_management_system_notifies_client_of_invalid_environment() {
		assertEquals("Client is notified of invalid environment", notification.getNotifyMessage());
	}
	
	@When("the container reaches its final destination")
	public void the_container_reaches_its_final_destination() {
		search.setSearch(new ContainerJourney());
		int cjIDX = search.findIDX(String.valueOf(clientContainer.getContainerID()), Journies); // CJ found from containerID
		clientContainerJourney = Journies[cjIDX];

		clientContainer.setContainerLocation(clientContainerJourney.getEndLocation());
		assertEquals(clientContainerJourney.getEndLocation(), clientContainer.getContainerLocation());
		notification = new NotifyObject(100, "Client is notified of arrival");
	}

	@Then("container management system notifies client container arrived")
	public void container_management_system_notifies_client_container_arrived() {
	    assertEquals("Client is notified of arrival", notification.getNotifyMessage());
	}

}
