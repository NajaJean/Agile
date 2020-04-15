package stepDefinitions;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

public class StepDefs_LogisticUpdate {
	
	ScenarioContext context;
	
	public StepDefs_LogisticUpdate(ScenarioContext context) {
		this.context = context;
	}
	
	NotifyObject response;
	
	double[] cphgpscoords = {730.0, 128.0};
	double[] tokyogpscoords = {1423.0, 245.0};
	double[] sydneygpscoords = {1495.0, 750.0};
	double[] nygpscoords = {290.0, 225.0};
	double[] hawaiigpscoords = {1735.0, 265.0};
	double[] riogpscoords = {420.0, 670.0};
	

	Location CPH = new Location("Copenhagen", cphgpscoords);
	Location Tokyo = new Location("Tokyo", tokyogpscoords);
	Location Sydney = new Location("Sydney", sydneygpscoords);
	Location NY = new Location("New York", nygpscoords);
	Location Hawaii = new Location("Hawaii", hawaiigpscoords);
	Location Rio = new Location("Rio", riogpscoords);

	Environment enviro = new Environment(5.0, 5.0, 5.0);
	Environment newEnviro = new Environment(15.0, 15.0, 15.0);
	
	Client client = new Client("asd", "yoDaddy69");
	
	Content stuff = new Content("Stuff", enviro, 1.0);
	Content newStuff = new Content("NewStuff", newEnviro, 2.0);
	
	Container selectedC = new Container(client, enviro, stuff);

	ContainerJourney containerJ = new ContainerJourney(CPH, NY, selectedC);
	ContainerJourney selectedJ = containerJ;
	
	
	@Given("company selected a container")
	public void company_selected_a_container() {
		assertEquals(client.getID(), selectedC.getClientofContainer().getID());
	}

	@When("the company updates the content")
	public void the_company_updates_the_content() {
		selectedC.setContainerContent(newStuff);
		//response = selectedC.setContainerContent(newStuff); //I dont know what this is :\
		//context.setResponse(response);
	}
	

	@Then("the content is updated")
	public void the_content_is_updated() {
		assertEquals(client.getID(), selectedC.getClientofContainer().getID());
		assertEquals(newStuff.getContentID(), selectedC.getContainerContent().getContentID());
		assertEquals(newStuff.getName(), selectedC.getContainerContent().getName());
		assertTrue(newStuff.getThreshold() == selectedC.getContainerContent().getThreshold());
		assertEquals(newStuff.getEnvironment().getEnviro_ID(), selectedC.getContainerContent().getEnvironment().getEnviro_ID());
		assertTrue(newStuff.getEnvironment().getHumidity() == selectedC.getContainerContent().getEnvironment().getHumidity());//assertEquals double double was outdated
		assertTrue(newStuff.getEnvironment().getPressure() == selectedC.getContainerContent().getEnvironment().getPressure());//assertEquals double double was outdated
		assertTrue(newStuff.getEnvironment().getTemp() == selectedC.getContainerContent().getEnvironment().getTemp());//assertEquals double double was outdated
	}
	
	@Then("a message is displayed saying {string}") 
	public void a_message_is_displayed_saying(String s){
		System.out.println(response.getNotifyMessage());
		assertEquals(s, response.getNotifyMessage());
	}
	
	@Given("that the company selected a container journey")
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
	
	@Then("than a message is displayed saying {string}") 
	public void than_a_message_is_displayed_saying(String s){
		System.out.println(response.getNotifyMessage());
		assertEquals(s, response.getNotifyMessage());
	}
}
