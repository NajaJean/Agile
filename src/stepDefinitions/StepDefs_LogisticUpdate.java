package stepDefinitions;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Content;
import core.Database;
import core.DatabaseData;
import core.Environment;
import core.Location;
import core.NotifyObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_LogisticUpdate {
	
	ScenarioContext context;
	NotifyObject response;
	
	double[] cphgpscoords = {730.0, 128.0};
	double[] nygpscoords = {290.0, 225.0};
	double[] hawaiigpscoords = {1735.0, 265.0};
	double[] currentgpscoords = {1.0, 1.0};

	Location CPH;
	Location NY;
	Location Hawaii;
	Client C;
	Environment en;
	Content co;

	Environment newEnviro;
	int oldEnviro;
	Container selectedC;

	ContainerJourney containerJ;
	ContainerJourney selectedJ;
	
	Database d = new Database("agileProject.accdb"); 
	int id = 1;

	Environment[] Enviros;
	Container[] Containers; 
	
	public StepDefs_LogisticUpdate(ScenarioContext context) {
		this.context = context;
		this.Enviros = DatabaseData.getEnvironments();
		this.Containers = DatabaseData.getContainers();
	}
	
	@Given("company selected a container")
	public void company_selected_a_container() {
		newEnviro = Enviros[4];
		selectedC = Containers[id];
		//oldEnviro = Containers[id].getContainerEnvironment().getEnviro_ID();
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
		assertTrue(newEnviro.getHumidity() == selectedC.getContainerEnvironment().getHumidity());//assertEquals double double was outdated
		assertTrue(newEnviro.getPressure() == selectedC.getContainerEnvironment().getPressure());//assertEquals double double was outdated
		assertTrue(newEnviro.getTemp() == selectedC.getContainerEnvironment().getTemp());//assertEquals double double was outdated
	}
	
	//String tableName, String column, String value, String condition
	@Then("the environment should be updated in the database")
	public void the_environment_should_be_updated_in_the_database() {
		String environmentID = String.valueOf(selectedC.getContainerEnvironment().getEnviro_ID());
		d.updateDatabase("Containers", "Environment_ID",environmentID,Integer.toString(id));
		//d.updateDatabase("Containers", "Environment_ID",Integer.toString(oldEnviro),Integer.toString(id));
	}
	
	@Given("the company selected a container journey")
	public void that_the_company_selected_a_container_journey() {
		CPH = new Location("Copenhagen",cphgpscoords);
		NY = new Location("New York",nygpscoords);
		Hawaii = new Location("Hawaii",hawaiigpscoords);
		C = new Client("M", "1234", "Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		co = new Content("Banana",new Environment(5.0,1.0,0.85), 0.1);
		en = new Environment(5.3,1.1,0.85);
		selectedC = new Container(C,co,CPH);
		selectedJ = new ContainerJourney(CPH, NY, selectedC);
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
	
	//String tableName, String column, String value, String condition
	@Then("the current location should be updated in the database")
	public void the_current_location_should_be_updated_in_the_database() {
		String currentX = String.valueOf(selectedJ.getCurrentX());
		NotifyObject firstResponse = d.updateDatabase("Journies", "Current_x", currentX, Integer.toString(id));
		String currentY = String.valueOf(selectedJ.getCurrentY());
		NotifyObject secondResponse = d.updateDatabase("Journies", "Current_y", currentY,Integer.toString(id));
		if (firstResponse.getNotifyCode() != 0 && secondResponse.getNotifyCode() != 0) {
			// They both passed and they have the same response message
			context.setResponse(firstResponse);
		}
		d.updateDatabase("Journies", "Current_y", Integer.toString(id));
		d.updateDatabase("Journies", "Current_x", Integer.toString(id));
	}
}
