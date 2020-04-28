package core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

public class DatabaseData {
	public static Database d = new Database("agileProject.accdb");
	
	private static Client[] Clients;
	private static Environment[] Environments;
	private static Content[] Contents;
	private static Container[] Containers; 
	private static Location[] Locations;
	private static ContainerJourney[] Journies;
	private static Logs Log;
	
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
		initLogs(Containers);
	}
	
	public static Logs getLogs() {
		return Log;
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
				Containers[i] = new Container(Client.findClient(containers[i+1][2],Clients), Content.findContent(Integer.parseInt(containers[i+1][4]),Contents),Location.findLocation(Integer.parseInt(containers[i+1][5]), Locations));
			}
			catch(Exception e) {
				Containers[i] = new Container(Location.findLocation(Integer.parseInt(containers[i+1][5]), Locations));
			}
		}
	}
	
	private static void initJournies () {
		String[][] journies = d.getTable("Journies");
		
		int journiesLength = Database.lengthTable(journies);
		
		Journies = new ContainerJourney[journiesLength];
		for(int i = 0; i < journiesLength; i++) {
			Journies[i] = new ContainerJourney(Location.findLocation(Integer.parseInt(journies[i+1][1]), Locations), 
				Location.findLocation(Integer.parseInt(journies[i+1][2]), Locations), 
				Container.findContainer(Integer.parseInt(journies[i+1][3]), Containers),
				Double.parseDouble(journies[i+1][6]),Double.parseDouble(journies[i+1][5]),

				LocalDate.of(Integer.parseInt(journies[i+1][7].substring(0,4)), 
							 Integer.parseInt(journies[i+1][7].substring(5,7)),
							 Integer.parseInt(journies[i+1][7].substring(8))),
				LocalDate.of(Integer.parseInt(journies[i+1][8].substring(0,4)), 
						 	 Integer.parseInt(journies[i+1][8].substring(5,7)),
						 	 Integer.parseInt(journies[i+1][8].substring(8))));	
		}
	}
	
	private static void initLogs(Container[] Containers)
	{
		Log = new Logs(Containers);
	}
	
	public static void addClient(Client client) {
		Clients = Arrays.copyOf(Clients, (Clients.length + 1));
		Clients[(Clients.length - 1)] = client;
	}
	
	public static void addContainer(Container container) {
		Containers = Arrays.copyOf(Containers, (Containers.length + 1));
		Containers[(Containers.length - 1)] = container;
	}
	
	public static void addContainerJourney(ContainerJourney containerJourney) {
		Journies = Arrays.copyOf(Journies, (Journies.length + 1));
		Journies[(Journies.length - 1)] = containerJourney;
	}
	
	public static void removeClient(Client client) {
		int id = ArrayUtils.indexOf(Clients, client);
		Client[] tempStart = Arrays.copyOfRange(Clients, 0, id);
		Client[] tempEnd = Arrays.copyOfRange(Clients, ++id, Clients.length);
		Clients = (Client [])ArrayUtils.addAll(tempStart, tempEnd);
	}
	
	public static void removeContainer(Container container) {
		int id = ArrayUtils.indexOf(Containers, container);
		Container[] tempStart = Arrays.copyOfRange(Containers, 0, id);
		Container[] tempEnd = Arrays.copyOfRange(Containers, ++id, Containers.length);
		Containers = (Container [])ArrayUtils.addAll(tempStart, tempEnd);
	}
	
	public static void removeContainerJourney(ContainerJourney containerJourney) {
		int id = ArrayUtils.indexOf(Journies, containerJourney);
		ContainerJourney[] tempStart = Arrays.copyOfRange(Journies, 0, id);
		ContainerJourney[] tempEnd = Arrays.copyOfRange(Journies, ++id, Journies.length);
		Journies = (ContainerJourney [])ArrayUtils.addAll(tempStart, tempEnd);
	}
	
	public static void updateClient(Client client) {
		Clients[ArrayUtils.indexOf(Clients, client)] = client;
	}
	
	public static void updateContainer(Container container) {
		Containers[ArrayUtils.indexOf(Containers, container)] = container;
	}
	
	public static void updateJourney(ContainerJourney containerJourney) {
		Journies[ArrayUtils.indexOf(Journies, containerJourney)] = containerJourney;
		
	}
	
	public static void printClients() {
		for (Client c : Clients) {
			System.out.println(c.toString());
		}
	}
}
