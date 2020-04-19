package stepDefinitions;

import core.Client;
import core.Container;
import core.Content;
import core.Database;
import core.Environment;
import core.Location;
import core.LogisticCompany;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs_LogisticRegisterContainer {
	
	Database d = new Database("agileProject.accdb");
	Container C;
	LogisticCompany l;
	
	Client[] Clients;
	Environment[] Enviros;
	Content[] Contents;
	Container[] Containers;
	Location[] Locations;
	
	Location loc;
	Environment enviro;

	@Given("that Logistic Company wants to register a container")
	public void that_Logistic_Company_wants_to_register_a_container() {
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
				
				Containers[i] = new Container(Environment.findEnviro(containers[i+1][3],Enviros),Location.findLocation(Integer.parseInt(containers[i+1][5]), Locations));
			}
			else {
				Containers[i] = new Container(Client.findClient(containers[i+1][2],Clients),Environment.findEnviro(containers[i+1][3],Enviros),Content.findContent(Integer.parseInt(containers[i+1][4]),Contents),Location.findLocation(Integer.parseInt(containers[i+1][5]), Locations));
			}
		}
		
		l = new LogisticCompany();
	}

	@When("the Logistic Company inserts all information")
	public void the_Logistic_Company_inserts_all_information() {
		enviro = Enviros[0];
		loc = Location.findLocation("Copenhagen",Locations);
	}

	@Then("the container is created with no content in the database and message displayed saying {string}")
	public void the_container_is_created_with_no_content_in_the_database_and_message_displayed_saying(String string) {
		C = new Container(enviro,loc);
		String s = C.toString();
		d.addToDatabase("Containers", s);
		System.out.print(string);
	}

	@Then("container is found in the database")
	public void container_is_found_in_the_database() {
	    d.getEmptyContainer(); 
	}
}
