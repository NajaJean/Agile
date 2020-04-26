package core;

import java.util.Arrays;

public class DatabaseData {
	private static Database d = new Database("agileProject.accdb");
	
	private static Client[] Clients;
	public static int nextClientIDX;
	
	private static Environment[] Environments;
	public static int nextEnvironmentIDX;
	
	private static Content[] Contents;
	public static int nextContentIDX;
	
	private static Container[] Containers; 
	public static int nextContainerIDX;
	
	private static Location[] Locations;
	public static int nextLocationIDX;
	
	private static ContainerJourney[] Journies;
	public static int nextJourneyIDX;
	
	private final static int extraSpace = 10;
	
	static { 
		initDataBase(); 
		DatabaseObserver observer = new DatabaseObserver();
		d.addObserver(observer);
	}
	
	public static void initDataBase() {
		initClients();
		initLocations();
		initEnvironments();
		initContents();
		initContainers();
		initJournies();
	}
	
	public static Database getDatabase() {
		return d;
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
		nextClientIDX = clientLength;
		
		Clients = new Client[clientLength + extraSpace];
		for(int i = 0; i < clientLength; i++) {
			Clients[i] = new Client(clients[i+1][5],clients[i+1][6],clients[i+1][2],clients[i+1][3],clients[i+1][4]);
		}
	}
	
	private static void initLocations() {
		String[][] locations = d.getTable("Locations");
		int locLength = Database.lengthTable(locations);
		nextLocationIDX = locLength;
		
		Locations = new Location[locLength + extraSpace];
		for(int i = 0; i < locLength; i++) {
			double[] gps = {Double.parseDouble(locations[i+1][3]),Double.parseDouble(locations[i+1][4])};
			Locations[i] = new Location(locations[i+1][2],gps);
		}
	}
	
	private static void initEnvironments() {
		String[][] environments = d.getTable("Environments");
		int enviroLength = Database.lengthTable(environments);
		nextEnvironmentIDX = enviroLength;
		
		Environments = new Environment[enviroLength + extraSpace];
		for(int i = 0; i < enviroLength; i++) {
			Environments[i] = new Environment(Double.parseDouble(environments[i+1][2]),Double.parseDouble(environments[i+1][3]),Double.parseDouble(environments[i+1][4]));	
		}
	}
	
	private static void initContents() {
		String[][] contents = d.getTable("Contents");
		int contentLength = Database.lengthTable(contents);
		nextContentIDX = contentLength;
		
		Contents = new Content[contentLength + extraSpace];
		for(int i = 0; i < contentLength; i++) {
			Contents[i] = new Content(contents[i+1][2],Environment.findEnviro(contents[i+1][3],Environments),Double.parseDouble(contents[i+1][4]));
		}
	}
	
	private static void initContainers() {
		String[][] containers = d.getTable("Containers");
		int containerLength = Database.lengthTable(containers);
		nextContainerIDX = containerLength;
		
		Containers = new Container[containerLength + extraSpace];
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
		nextJourneyIDX = journiesLength;
		
		Journies = new ContainerJourney[journiesLength + extraSpace];
		for(int i = 0; i < journiesLength; i++) {
			Journies[i] = new ContainerJourney(Location.findLocation(Integer.parseInt(journies[i+1][1]), Locations), 
				Location.findLocation(Integer.parseInt(journies[i+1][2]), Locations), Container.findContainer(Integer.parseInt(journies[i+1][3]), Containers));	
		}
	}
	
	public static void addClient(Client client) {	
		if (nextClientIDX >= Clients.length) {
			Clients = Arrays.copyOf(Clients, (Clients.length + extraSpace));
			Clients[nextClientIDX] = client;
			nextClientIDX++;
		} else {
			Clients[nextClientIDX] = client;
			nextClientIDX++;
		}
	}
	
	public static void addContainer(Container container) {
		if (nextContainerIDX >= Containers.length) {
			Containers = Arrays.copyOf(Containers, (Containers.length + extraSpace));
			Containers[nextContainerIDX] = container;
			nextContainerIDX++;
		} else {
			Containers[nextContainerIDX] = container;
			nextContainerIDX++;
		}
	}
	
	public static void addContainerJourney(ContainerJourney containerJourney) {
		if (nextJourneyIDX >= Journies.length) {
			Journies = Arrays.copyOf(Journies, (Journies.length + extraSpace));
			Journies[nextJourneyIDX] = containerJourney;
			nextJourneyIDX++;
		} else {
			Journies[nextJourneyIDX] = containerJourney;
			nextJourneyIDX++;
		}
	}
	
	public static void updateClient(Client c) {
		int id = c.getID();
		Clients[--id] = c;
	}
	
	public static void updateContainer(Container c) {
		int id = c.getContainerID();
		Containers[--id] = c;
	}
	
	public static void updateJourney(ContainerJourney c) {
		int id = c.getJourneyID();
		Journies[--id] = c;
	}
}
