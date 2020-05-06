package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import core.ArraySearch;
import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Content;
import core.Database;
import core.DatabaseData;
import core.Location;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_ClientAdvancedQueries {
	Database d;
	Content[] Contents;
	Location[] Locations;
	Client[] Clients;
	Container[] Containers;
	ContainerJourney[] Journies;
	
	ContainerJourney[] ClientJournies;
	Client c;
	
	ArraySearch search;
	
	int longestTravelledCon;
	
	public StepDefs_ClientAdvancedQueries() {
		this.Contents = DatabaseData.getContents();
		this.Locations = DatabaseData.getLocations();
		this.Clients = DatabaseData.getClients();
		this.Containers = DatabaseData.getContainers();
		this.Journies = DatabaseData.getJournies();
		this.search = new ArraySearch(new Container()); 
	}
	
	@Given("A client has data stored in the database")
	public void A_client_has_data_stored_in_database() {
		this.d = DatabaseData.getDatabase();
		c = Clients[0];
	}
	
	@When("advanced statistics about their usage want to be calculated")
	public void advanced_statistics_about_their_usage_want_to_be_calculated() {
 		ClientJournies = ContainerJourney.clientJournies(Journies, c);
 		longestTravelledCon = ContainerJourney.longestJourney(ClientJournies).getContaineronJourney().getContainerID();
	}
	
	@Then("these are validated for the client")
	public void these_are_validated_for_the_client() {
		int containerIDX = search.findIDX(longestTravelledCon, Containers);
		assertEquals(longestTravelledCon, Containers[containerIDX].getContainerID());
	}

}
