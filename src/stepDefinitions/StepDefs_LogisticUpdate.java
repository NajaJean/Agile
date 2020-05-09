package stepDefinitions;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import ExternalData.Database;
import ExternalData.DatabaseData;
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
	NotifyObject response;

	double[] currentgpscoords = {1.0, 1.0};

	Environment newEnviro;
	int oldEnviro;
	Container selectedC;

	ContainerJourney selectedJ;
	
	Database d = new Database("agileProject.accdb"); 
	int id = 1;

	Environment[] Enviros;
	Container[] Containers; 
	ContainerJourney[] Journies;
	
	LocalDate startDate = LocalDate.of(2020, 04, 28);
	LocalDate endDate = LocalDate.of(2020, 05, 28);
	
	public StepDefs_LogisticUpdate(ScenarioContext context) {
		this.context = context;
		this.Enviros = DatabaseData.getEnvironments();
		this.Containers = DatabaseData.getContainers();
		this.Journies = DatabaseData.getJournies();
	}
	
	@Given("company selected a container")
	public void company_selected_a_container() {
		newEnviro = Enviros[4];
		selectedC = Containers[id];
	}

	@When("the company updates the environment")
	public void the_company_updates_the_environment() {
		selectedC.setContainerEnvironment(newEnviro);
		response = new NotifyObject(101, "Environment is updated successfully");
		selectedC.setContainerEnvironment(newEnviro);;
		context.setResponse(response);
	}

	@Then("the environment is updated")
	public void the_environment_is_updated() {
		assertEquals(newEnviro.getEnviro_ID(), selectedC.getContainerEnvironment().getEnviro_ID());
		assertTrue(newEnviro.getHumidity() == selectedC.getContainerEnvironment().getHumidity());
		assertTrue(newEnviro.getPressure() == selectedC.getContainerEnvironment().getPressure());
		assertTrue(newEnviro.getTemp() == selectedC.getContainerEnvironment().getTemp());
	}
	
	@Then("the environment should be updated in the database")
	public void the_environment_should_be_updated_in_the_database() {
		String environmentID = String.valueOf(selectedC.getContainerEnvironment().getEnviro_ID());
		d.updateDatabase("Containers", "Environment_ID",environmentID,Integer.toString(id));
	}
	
	@Given("the company selected a container journey")
	public void that_the_company_selected_a_container_journey() {
		selectedJ = Journies[id];
	} 

	@When("the company updates the current location")
	public void the_company_updates_the_current_location() {
		selectedJ.setCurrentLocation(currentgpscoords);
	}
	
	@Then("the current location is updated")
	public void the_current_location_is_updated() {
		assertTrue(currentgpscoords[0] == selectedJ.getCurrentX());
		assertTrue(currentgpscoords[1] == selectedJ.getCurrentY());
	}
	
	@Then("the current location should be updated in the database")
	public void the_current_location_should_be_updated_in_the_database() {
		String currentX = String.valueOf(selectedJ.getCurrentX());
		NotifyObject firstResponse = d.updateDatabase("Journies", "Current_x", currentX, Integer.toString(id));
		String currentY = String.valueOf(selectedJ.getCurrentY());
		NotifyObject secondResponse = d.updateDatabase("Journies", "Current_y", currentY,Integer.toString(id));
		if (firstResponse.getNotifyCode() != 0 && secondResponse.getNotifyCode() != 0) {
			context.setResponse(firstResponse);
		}
		d.updateDatabase("Journies", "Current_y", Double.toString(Journies[0].getCurrentY()), Integer.toString(id));
		d.updateDatabase("Journies", "Current_x", Double.toString(Journies[0].getCurrentX()),Integer.toString(id));
	}
}
