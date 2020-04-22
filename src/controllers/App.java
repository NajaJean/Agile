package controllers;

import UI.StartLoginPage;
import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Content;
import core.Database;
import core.Environment;
import core.Location;

public class App {
	static Database d = new Database("agileProject.accdb"); 
	static Client[] Clients;
	static Environment[] Enviros;
	static Content[] Contents;
	static Container[] Containers; 
	static Location[] Locations;
	static ContainerJourney[] Journies;
	
	public static void initDataBase() {
		String[][] clients = d.getTable("Clients");
		int clientLength = Database.lengthTable(clients);
		Clients = new Client[clientLength];
		for(int i = 0; i < clientLength; i++) {
			Clients[i] = new Client(clients[i+1][5],clients[i+1][6],clients[i+1][2],clients[i+1][3],clients[i+1][4]);
		}
		
		String[][] locations = d.getTable("Locations");
		int locLength = Database.lengthTable(locations);
		Locations = new Location[locLength];
		for(int i = 0; i < locLength; i++) {
			double[] gps = {Double.parseDouble(locations[i+1][3]),Double.parseDouble(locations[i+1][4])};
			Locations[i] = new Location(locations[i+1][2],gps);
		}
		
		String[][] environments = d.getTable("Environments");
		int enviroLength = Database.lengthTable(environments);
		Enviros = new Environment[enviroLength];
		for(int i = 0; i < enviroLength; i++) {
			Enviros[i] = new Environment(Double.parseDouble(environments[i+1][2]),Double.parseDouble(environments[i+1][3]),Double.parseDouble(environments[i+1][4]));	
		}
		
		String[][] contents = d.getTable("Contents");
		int contentLength = Database.lengthTable(contents);
		Contents = new Content[contentLength];
		for(int i = 0; i < contentLength; i++) {
			Contents[i] = new Content(contents[i+1][2],Environment.findEnviro(contents[i+1][3],Enviros),Double.parseDouble(contents[i+1][4]));
		}
		
		String[][] containers = d.getTable("Containers");
		int containerLength = Database.lengthTable(containers);
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
		int journiesLength = Database.lengthTable(journies);
		Journies = new ContainerJourney[journiesLength];
		for(int i = 0; i < journiesLength; i++) {
			Journies[i] = new ContainerJourney(Location.findLocation(Integer.parseInt(journies[i+1][2]), Locations), 
					Location.findLocation(Integer.parseInt(journies[i+1][3]), Locations), Container.findContainer(Integer.parseInt(journies[i+1][4]), Containers));	
			
		}
	}
	
	public static void main(String[] args) {
		initDataBase();
		// Assemble all the pieces of the MVC
		StartLoginPage v = new StartLoginPage();
		StartLoginPageController c = new StartLoginPageController(v, Clients, Enviros, Contents, Containers, Locations, Journies, d);
		c.initController();
	}
}
