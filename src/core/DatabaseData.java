package core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

public class DatabaseData {
	private static Database d = new Database("agileProject.accdb");
	private static Logs Log;
	
	public static Database getDatabase() {
		return d;
	}
	
	public static Client[] getClients() {
		ArrayList<Client> Clients = initClients();
		return Clients.toArray(new Client[0]);
	}
	
	public static Environment[] getEnvironments() {
		ArrayList<Environment> Environments = initEnvironments();
		return Environments.toArray(new Environment[0]);
	}
	
	public static Content[] getContents() {
		ArrayList<Content> Contents = initContents();
		return Contents.toArray(new Content[0]);
	}
	
	public static Container[] getContainers() {
		ArrayList<Container> Containers = initContainers();
		return Containers.toArray(new Container[0]);
	}
	
	public static Location[] getLocations() {
		ArrayList<Location> Locations = initLocations();
		return Locations.toArray(new Location[0]);
	}
	
	public static ContainerJourney[] getJournies() {
		ArrayList<ContainerJourney> Journies = initJournies();
		return Journies.toArray(new ContainerJourney[0]);
	}

	private static ArrayList<Client> initClients() {
		String[][] clients = d.getTable("Clients");
		Client.resetCount();
	
		ArrayList<Client> Clients = new ArrayList<>();
		for(int i = 0; i < Database.lengthTable(clients); i++) {
			Clients.add(new Client(clients[i+1][5], clients[i+1][6], clients[i+1][2], clients[i+1][3], clients[i+1][4]));
		}
		return Clients;
	}
	
	private static ArrayList<Environment> initEnvironments() {
		String[][] environments = d.getTable("Environments");
		Environment.resetCount();
		
		ArrayList<Environment> Environments = new ArrayList<>();
		for(int i = 0; i < Database.lengthTable(environments); i++) {
			Environments.add(new Environment(Double.parseDouble(environments[i+1][2]), 
							Double.parseDouble(environments[i+1][4]), 
							Double.parseDouble(environments[i+1][3])));	
		}
		return Environments;
	}
	
	private static ArrayList<Content> initContents() {
		String[][] contents = d.getTable("Contents");
		Content.resetCount();
		
		ArraySearch search = new ArraySearch(new Environment());
		
		ArrayList<Content> Contents = new ArrayList<>();
		Environment[] Environments = getEnvironments();
		for(int i = 0; i < Database.lengthTable(contents); i++) {
			int envIDX = search.findIDX(contents[i+1][3], Environments);
			
			Contents.add(new Content(contents[i+1][2], 
						Environments[envIDX], 
						Double.parseDouble(contents[i+1][4])));
		}
		return Contents;
	}
	
	private static ArrayList<Container> initContainers() {
		String[][] containers = d.getTable("Containers");
		Container.resetCount();
		
		Client[] Clients = getClients();
		Content[] Contents = getContents();
		Location[] Locations = getLocations();
		
		ArraySearch search = new ArraySearch();
		
		ArrayList<Container> Containers = new ArrayList<>();
		for(int i = 0; i < Database.lengthTable(containers); i++) {
			try {
				search.setSearch(new Client());
				int clientIDX = search.findIDX(Integer.parseInt(containers[i+1][2]), Clients);
				search.setSearch(new Content());
				int contentIDX = search.findIDX(Integer.parseInt(containers[i+1][4]), Contents);
				search.setSearch(new Location());
				int locationIDX = search.findIDX(Integer.parseInt(containers[i+1][5]), Locations);
				
				Containers.add(new Container(Clients[clientIDX], 
							  Contents[contentIDX],
							  Locations[locationIDX]));
			} catch (Exception e) {
				search.setSearch(new Location());
				int locationIDX = search.findIDX(Integer.parseInt(containers[i+1][5]), Locations);
				
				Containers.add(new Container(Locations[locationIDX]));
			}
		}
		return Containers;
	}
	
	private static ArrayList<Location> initLocations() {
		String[][] locations = d.getTable("Locations");
		Location.resetCount();
		
		ArrayList<Location> Locations = new ArrayList<>();
		for(int i = 0; i < Database.lengthTable(locations); i++) {
			double[] gps = {Double.parseDouble(locations[i+1][3]), Double.parseDouble(locations[i+1][4])};
			
			Locations.add(new Location(locations[i+1][2],gps));
		}
		return Locations;
	}
	
	private static ArrayList<ContainerJourney> initJournies () {
		String[][] journies = d.getTable("Journies");
		ContainerJourney.resetCount();
		
		Location[] Locations = getLocations();
		Container[] Containers = getContainers();
		
		ArraySearch search = new ArraySearch();
		
		ArrayList<ContainerJourney> Journies = new ArrayList<>();
		for(int i = 0; i < Database.lengthTable(journies); i++) {
			search.setSearch(new Location());
			int locationFirstIDX = search.findIDX(Integer.parseInt(journies[i+1][1]), Locations);
			int locationSecondIDX = search.findIDX(Integer.parseInt(journies[i+1][2]), Locations);
			search.setSearch(new Container());
			int containerIDX = search.findIDX(Integer.parseInt(journies[i+1][3]), Containers);
			
			Journies.add(new ContainerJourney(Locations[locationFirstIDX], 
						Locations[locationSecondIDX], 
						Containers[containerIDX],
						Double.parseDouble(journies[i+1][6]),Double.parseDouble(journies[i+1][5]),
						LocalDate.of(Integer.parseInt(journies[i+1][7].substring(0,4)), 
							 Integer.parseInt(journies[i+1][7].substring(5,7)),
							 Integer.parseInt(journies[i+1][7].substring(8))),
						LocalDate.of(Integer.parseInt(journies[i+1][8].substring(0,4)), 
						 	 Integer.parseInt(journies[i+1][8].substring(5,7)),
						 	 Integer.parseInt(journies[i+1][8].substring(8)))));	
		}
		return Journies;
	}
	
	private static void initLogs(Container[] Containers) {
		Log = new Logs(Containers);
	}
	
	public static Logs getLogs() {
		initLogs(getContainers());
		return Log;
	}
}
