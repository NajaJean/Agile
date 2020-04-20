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
import io.cucumber.java.Before;
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
	double[] currentgpscoords = {1.0, 1.0};

	Location CPH;
	Location NY;
	Location Hawaii;
	Client C;
	Environment en;
	Content co;

	Environment newEnviro;
	Container selectedC;

	ContainerJourney containerJ;
	ContainerJourney selectedJ;
	
	Database d = new Database("agileProject.accdb"); 
	int id = 1;
	Client[] Clients;
	Environment[] Enviros;
	Content[] Contents;
	Container[] Containers; 
	Location[] Locations;
	ContainerJourney[] Journies;
	
	@Before
	public void prepareData() {
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
			try {
				Containers[i] = new Container(Client.findClient(containers[i+1][2],Clients),Environment.findEnviro(containers[i+1][3],Enviros),Content.findContent(Integer.parseInt(containers[i+1][4]),Contents),Location.findLocation(Integer.parseInt(containers[i+1][5]), Locations));
			}
			catch(Exception e) {
				Containers[i] = new Container(Environment.findEnviro(containers[i+1][3],Enviros),Location.findLocation(Integer.parseInt(containers[i+1][5]), Locations));
			}
		}
		
		String[][] journies = d.getTable("Journies");
		int journiesLength = 0;
		for(int i = 1; i < journies.length; i++) {
			if (!(journies[i][1] == null)) {
				journiesLength++;
			}
		}
		Journies = new ContainerJourney[journiesLength];
		for(int i = 0; i < journiesLength; i++) {
			Journies[i] = new ContainerJourney(Location.findLocation(Integer.parseInt(journies[i+1][2]), Locations), 
					Location.findLocation(Integer.parseInt(journies[i+1][3]), Locations), Container.findContainer(Integer.parseInt(journies[i+1][4]), Containers));	
			
		}
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
		assertTrue(newEnviro.getHumidity() == selectedC.getContainerEnvironment().getHumidity());//assertEquals double double was outdated
		assertTrue(newEnviro.getPressure() == selectedC.getContainerEnvironment().getPressure());//assertEquals double double was outdated
		assertTrue(newEnviro.getTemp() == selectedC.getContainerEnvironment().getTemp());//assertEquals double double was outdated
	}
	
	//String tableName, String column, String value, String condition
	@Then("the environment should be updated in the database")
	public void the_environment_should_be_updated_in_the_database() {
		String environmentID = String.valueOf(selectedC.getContainerEnvironment().getEnviro_ID());
		d.updateDatabase("Containers", "Environment_ID",environmentID,Integer.toString(id));
	}
	
	@Then("a message is displayed: {string}") 
	public void a_message_is_displayed(String s){
		assertEquals(s, response.getNotifyMessage());
	} 
	
	@Given("the company selected a container journey")
	public void that_the_company_selected_a_container_journey() {
		CPH = new Location("Copenhagen",cphgpscoords);
		NY = new Location("New York",nygpscoords);
		Hawaii = new Location("Hawaii",hawaiigpscoords);
		C = new Client("M", "1234", "Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		co = new Content("Banana",new Environment(5.0,1.0,0.85), 0.1);
		en = new Environment(5.3,1.1,0.85);
		selectedC = new Container(C,en,co,CPH);
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
	}
}
