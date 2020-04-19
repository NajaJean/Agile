package stepDefinitions;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Content;
import core.Database;
import core.Environment;
import core.Location;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_LogisticUpdate {
	
	ScenarioContext context;
	
	public StepDefs_LogisticUpdate(ScenarioContext context) {
		this.context = context;
	}
	
	NotifyObject response;
	
	double[] cphgpscoords = {730.0, 128.0};
	double[] nygpscoords = {290.0, 225.0};
	double[] hawaiigpscoords = {1735.0, 265.0};

	Location CPH;
	Location NY;
	Location Hawaii;

	Environment enviro;
	Environment newEnviro;
	
	Client client;
	
	Content stuff;
	Content newStuff;
	
	Container selectedC;

	ContainerJourney containerJ;
	ContainerJourney selectedJ;
	
	Database d = new Database("agileProject.accdb"); 
	int id = 1;
	Container con;
	Client[] Clients;
	Environment[] Enviros;
	Content[] Contents;
	Container[] Containers; 
	Location[] Locations;

	
	@Given("company selected a container")
	public void company_selected_a_container() {
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
		String[][] locations = d.getTable("Locations");
		int locLength = 0;
		for(int i = 1; i < locations.length; i++) {
			if (!(locations[i][1] == null)) {
				locLength++;
			}
		}
		Locations = new Location[locLength];
		for(int i = 0; i < locLength; i++) {
			double[] gps = {Double.parseDouble(locations[i+1][3]),Double.parseDouble(locations[i+1][4])};
			Locations[i] = new Location(locations[i+1][2],gps);
		}
		
		String[][] environments = d.getTable("Environments");
		int enviroLength = 0;
		for(int i = 1; i < environments.length; i++) {
			if (!(environments[i][1] == null)) {
				enviroLength++;
			}
		}
		Enviros = new Environment[enviroLength];
		for(int i = 0; i < enviroLength; i++) {
			Enviros[i] = new Environment(Double.parseDouble(environments[i+1][2]),Double.parseDouble(environments[i+1][3]),Double.parseDouble(environments[i+1][4]));	
		}
		
		String[][] contents = d.getTable("Contents");
		int contentLength = 0;
		for(int i = 1; i < contents.length; i++) {
			if (!(contents[i][1] == null)) {
				contentLength++;
			}
		}
		Contents = new Content[contentLength];
		for(int i = 0; i < contentLength; i++) {
			Contents[i] = new Content(contents[i+1][2],Environment.findEnviro(contents[i+1][3],Enviros),Double.parseDouble(contents[i+1][4]));
		}
		
		String[][] containers = d.getTable("Containers");
		int containerLength = 0;
		for(int i = 1; i < containers.length; i++) {
			if (!(containers[i][1] == null)) {
				containerLength++;
			}
		}
		Containers = new Container[containerLength];
		for(int i = 0; i < containerLength; i++) {
			if(containers[i+1][2] == null && containers[i+1][4] == null) {
				Containers[i] = new Container(Environment.findEnviro(containers[i+1][3],Enviros),Location.findLocation(containers[i+1][3], Locations));
			}
			else {
				Containers[i] = new Container(Client.findClient(containers[i+1][2],Clients),Environment.findEnviro(containers[i+1][3],Enviros),Content.findContent(containers[i+1][4],Contents),Location.findLocation(containers[i+1][3], Locations));
			}
		}
		CPH = new Location("Copenhagen", cphgpscoords);
		NY = new Location("New York", nygpscoords);
		Hawaii = new Location("Hawaii", hawaiigpscoords);

		enviro = new Environment(5.0, 5.0, 5.0);
		newEnviro = new Environment(15.0, 15.0, 15.0);
		
		client = new Client("plsShipMyStuff", "yoDaddy69", "name", "email", "address");
		
		stuff = new Content("Stuff", enviro, 1.0);
		newStuff = new Content("NewStuff", newEnviro, 2.0);
		
		selectedC = new Container(client, enviro, stuff,CPH);

		containerJ = new ContainerJourney(CPH, NY, selectedC);
		selectedJ = containerJ;
		
		selectedC = Containers[id];
	}

	@When("the company updates the content")
	public void the_company_updates_the_content() {
		selectedC.setContainerContent(newStuff);
		response = selectedC.responseLogisticUpdate();
		//context.setResponse(response);
	}

	@Then("the content is updated")
	public void the_content_is_updated() {
		assertEquals(newStuff.getContentID(), selectedC.getContainerContent().getContentID());
		assertEquals(newStuff.getName(), selectedC.getContainerContent().getName());
		assertTrue(newStuff.getThreshold() == selectedC.getContainerContent().getThreshold());
		assertEquals(newStuff.getEnvironment().getEnviro_ID(), selectedC.getContainerContent().getEnvironment().getEnviro_ID());
		assertTrue(newStuff.getEnvironment().getHumidity() == selectedC.getContainerContent().getEnvironment().getHumidity());//assertEquals double double was outdated
		assertTrue(newStuff.getEnvironment().getPressure() == selectedC.getContainerContent().getEnvironment().getPressure());//assertEquals double double was outdated
		assertTrue(newStuff.getEnvironment().getTemp() == selectedC.getContainerContent().getEnvironment().getTemp());//assertEquals double double was outdated
	}
	
	//String tableName, String column, String value, String condition
	@Then("the content should be updated in the database")
	public void the_content_should_be_updated_in_the_database() {
		String contentID = String.valueOf(selectedC.getContainerContent().getContentID());
		d.updateDatabase("Contents", "Content_ID",contentID,Integer.toString(id));
		String contentName = selectedC.getContainerContent().getName();
		d.updateDatabase("Contents", "Name",contentName,Integer.toString(id));
		String contentThreshold = String.valueOf(selectedC.getContainerContent().getThreshold());
		d.updateDatabase("Contents", "Threshold",contentThreshold,Integer.toString(id));
		String contentEnvironmentID = String.valueOf(selectedC.getContainerEnvironment().getEnviro_ID());
		d.updateDatabase("Containers", "Environment",contentEnvironmentID,Integer.toString(id));
		String contentEnvironmentTemp = String.valueOf(selectedC.getContainerEnvironment().getTemp());
		d.updateDatabase("Containers", "Temperature",contentEnvironmentTemp,Integer.toString(id));
		String contentEnvironmentPressure = String.valueOf(selectedC.getContainerEnvironment().getPressure());
		d.updateDatabase("Containers", "Pressure",contentEnvironmentPressure,Integer.toString(id));
		String contentEnvironmentHumidity = String.valueOf(selectedC.getContainerEnvironment().getHumidity());
		d.updateDatabase("Containers", "Humidity",contentEnvironmentHumidity,Integer.toString(id));
		
	}
	
	@Then("a message is displayed: {string}") 
	public void a_message_is_displayed(String s){
	//	System.out.println(response.getNotifyMessage());
		assertEquals(s, response.getNotifyMessage());
	} 
	
	@Given("the company selected a container journey")
	public void that_the_company_selected_a_container_journey() {
		assertEquals(containerJ.getJourneyID(), selectedJ.getJourneyID());
	}

	@When("the company updates the current location")
	public void the_company_updates_the_current_location() {
		selectedJ.setCurrentLocation(Hawaii);
	}
	

	@Then("the current location is updated")
	public void the_current_location_is_updated() {
		
		assertEquals(Hawaii.getLocationName(), selectedJ.getCurrentLocation().getLocationName());
		assertEquals(Hawaii.getGPScoord(), selectedJ.getCurrentLocation().getGPScoord());
		assertTrue(Hawaii.getGPScoord()[0] == selectedJ.getCurrentLocX());
		assertTrue(Hawaii.getGPScoord()[1] == selectedJ.getCurrentLocY());
	}
	
/*	@Then("a message is displayed: {string}") 
	public void a_message_is_displayed_saying(String s){
		System.out.println(response.getNotifyMessage());
		assertEquals(s, response.getNotifyMessage());
	} */
}
