package core;

public class DatabaseData {
	public static Database d = new Database("agileProject.accdb");
	
	private static Client[] Clients;
	private static Environment[] Environments;
	private static Content[] Contents;
	private static Container[] Containers; 
	private static Location[] Locations;
	private static ContainerJourney[] Journies;
	
	static { initDataBase(); }
	
	public static void initDataBase() {
		initClients();
		initLocations();
		initEnvironments();
		initContents();
		initContainers();
		initJournies();
	}
	
	public static Client[] getClients() {
		return Clients;
	}
	
	public static Environment[] getEnvironments() {
		return Environments;
	}
	
	public static Content[] getContents() {
		return Contents;
	}
	
	public static Container[] getContainers() {
		return Containers;
	}
	
	public static Location[] getLocations() {
		return Locations;
	}
	
	public static ContainerJourney[] getJournies() {
		return Journies;
	}

	private static void initClients() {
		String[][] clients = d.getTable("Clients");
		int clientLength = Database.lengthTable(clients);
		
		Clients = new Client[clientLength];
		for(int i = 0; i < clientLength; i++) {
			Clients[i] = new Client(clients[i+1][5],clients[i+1][6],clients[i+1][2],clients[i+1][3],clients[i+1][4]);
		}
	}
	
	private static void initLocations() {
		String[][] locations = d.getTable("Locations");
		int locLength = Database.lengthTable(locations);
		
		Locations = new Location[locLength];
		for(int i = 0; i < locLength; i++) {
			double[] gps = {Double.parseDouble(locations[i+1][3]),Double.parseDouble(locations[i+1][4])};
			Locations[i] = new Location(locations[i+1][2],gps);
		}
	}
	
	private static void initEnvironments() {
		String[][] environments = d.getTable("Environments");
		int enviroLength = Database.lengthTable(environments);
		
		Environments = new Environment[enviroLength];
		for(int i = 0; i < enviroLength; i++) {
			Environments[i] = new Environment(Double.parseDouble(environments[i+1][2]),Double.parseDouble(environments[i+1][3]),Double.parseDouble(environments[i+1][4]));	
		}
	}
	
	private static void initContents() {
		String[][] contents = d.getTable("Contents");
		int contentLength = Database.lengthTable(contents);
		
		Contents = new Content[contentLength];
		for(int i = 0; i < contentLength; i++) {
			Contents[i] = new Content(contents[i+1][2],Environment.findEnviro(contents[i+1][3],Environments),Double.parseDouble(contents[i+1][4]));
		}
	}
	
	private static void initContainers() {
		String[][] containers = d.getTable("Containers");
		int containerLength = Database.lengthTable(containers);
		
		Containers = new Container[containerLength];
		for(int i = 0; i < containerLength; i++) {
			try {
				Containers[i] = new Container(Client.findClient(containers[i+1][2],Clients),Environment.findEnviro(containers[i+1][3],Environments),Content.findContent(Integer.parseInt(containers[i+1][4]),Contents),Location.findLocation(Integer.parseInt(containers[i+1][5]), Locations));
			}
			catch(Exception e) {
				Containers[i] = new Container(Environment.findEnviro(containers[i+1][3],Environments),Location.findLocation(Integer.parseInt(containers[i+1][5]), Locations));
			}
		}
	}
	
	private static void initJournies () {
		String[][] journies = d.getTable("Journies");
		int journiesLength = Database.lengthTable(journies);
		
		Journies = new ContainerJourney[journiesLength];
		for(int i = 0; i < journiesLength; i++) {
			Journies[i] = new ContainerJourney(Location.findLocation(Integer.parseInt(journies[i+1][2]), Locations), 
					Location.findLocation(Integer.parseInt(journies[i+1][3]), Locations), Container.findContainer(Integer.parseInt(journies[i+1][4]), Containers));	
			
		}
	}
}
