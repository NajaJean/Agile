package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

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
	
	int longestTravelledCon;
	
	public StepDefs_ClientAdvancedQueries() {
		this.Contents = DatabaseData.getContents();
		this.Locations = DatabaseData.getLocations();
		this.Clients = DatabaseData.getClients();
		this.Containers = DatabaseData.getContainers();
		this.Journies = DatabaseData.getJournies();
	}
	
	@Given("A client has data stored in the database")
	public void A_client_has_data_stored_in_database() {
		this.d = DatabaseData.getDatabase();
		c = Clients[0];
	}
	
	@When("advanced statistics about their usage want to be calculated")
	public void advanced_statistics_about_their_usage_want_to_be_calculated() {
		//THIS ONE IS NOT COMPLETE YET
		// There is a method already for this within map - maybe a good idea to move it somehwere where it can be used
		ArrayList<ContainerJourney> clientContainersList = new ArrayList<ContainerJourney>();
 		for (int i = 0; i < Journies.length; i++) {
 			
 			if (c.getID() == Journies[i].getContaineronJourney().getClientofContainer().getID()) 
 			{
 			
 				clientContainersList.add(Journies[i]);
 			}
 			
 		}
 		
 		ClientJournies = new ContainerJourney[clientContainersList.size()];
 		ClientJournies = clientContainersList.toArray(ClientJournies);
 		
 		double len = 0;
 		Container con = ClientJournies[0].getContaineronJourney();
 		for(int i=0; i<ClientJournies.length;i++) {
 			double newlen = ClientJournies[i].getEndLocation().euclideanDistance(ClientJournies[i].getCurrentLocationDoubleA());
 			if(newlen>len) {
 				len = newlen;
 				con = ClientJournies[i].getContaineronJourney();
 			}
 		}
 		longestTravelledCon = con.getContainerID();
	}
	
	@Then("these are validated for the client")
	public void these_are_validated_for_the_client() {
		assertEquals(longestTravelledCon,Container.findContainer(longestTravelledCon, Containers).getContainerID());
		// THIS IS NTO COMPLETE YET
	}

}
