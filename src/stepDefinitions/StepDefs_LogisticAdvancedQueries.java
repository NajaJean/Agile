package stepDefinitions;

import static org.junit.Assert.assertEquals;

import core.Client;
import core.Container;
import core.Content;
import core.Database;
import core.DatabaseData;
import core.Location;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_LogisticAdvancedQueries {
	Database d;
	Content[] Contents;
	Location[] Locations;
	Client[] Clients;
	Container[] Containers;
	
	int mostUsedContent;
	
	int mostVisitedLoc;
	
	int busiestClient;
	
	int mostTravelledCon;
	
	public StepDefs_LogisticAdvancedQueries() {
		this.Contents = DatabaseData.getContents();
		this.Locations = DatabaseData.getLocations();
		this.Clients = DatabaseData.getClients();
		this.Containers = DatabaseData.getContainers();
	}
	
	@Given("A logistic company has preloaded data from the database")
	public void A_logistic_company__has_preloaded_data_from_the_database() {
		this.d = DatabaseData.getDatabase();
	}
	@When("advanced statistics want to be calculated")
	public void advanced_statistics_want_to_be_calculated() {
		String maxContent = "SELECT Content_ID FROM Containers GROUP BY Content_ID"
				+ " HAVING COUNT (Content_ID) = (SELECT MAX(mycount) FROM ("
				+ "SELECT Content_ID, COUNT(Content_ID) mycount FROM Containers GROUP BY Content_ID))";
		mostUsedContent = Integer.parseInt(d.queryDatabase(maxContent));
		
		String maxLoc = "SELECT End FROM Journies GROUP BY End"
				+ " HAVING COUNT (End) = (SELECT MAX(mycount) FROM ("
				+ "SELECT End, COUNT(End) mycount FROM Journies GROUP BY End))";
		mostVisitedLoc = Integer.parseInt(d.queryDatabase(maxLoc));
		
		String maxClient = "SELECT Client_ID FROM Containers GROUP BY Client_ID"
				+ " HAVING COUNT (Client_ID) = (SELECT MAX(mycount) FROM ("
				+ "SELECT Client_ID, COUNT(Client_ID) mycount FROM Containers GROUP BY Client_ID))";
		busiestClient = Integer.parseInt(d.queryDatabase(maxClient));
		
		String maxContainer = "SELECT Container_ID FROM Journies GROUP BY Container_ID"
				+ " HAVING COUNT (Container_ID) = (SELECT MAX(mycount) FROM ("
				+ "SELECT Container_ID, COUNT(Container_ID) mycount FROM Journies GROUP BY Container_ID))";
		mostTravelledCon = Integer.parseInt(d.queryDatabase(maxContainer));

	}
	@Then("these are validated for the company")
	public void these_are_validated_for_the_company() {
		assertEquals(mostUsedContent,Content.findContent(mostUsedContent, Contents).getContentID());
		
		assertEquals(mostVisitedLoc,Location.findLocation(mostVisitedLoc, Locations).getLocationID());
		
		assertEquals(busiestClient,Client.findClient(busiestClient, Clients).getID());
		
		assertEquals(mostTravelledCon,Container.findContainer(mostTravelledCon, Containers).getContainerID());
	}

}
