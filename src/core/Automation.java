package core;

import java.time.LocalDate;
import java.util.Random;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.RandomStringUtils;

public class Automation {
	
	public String rName(int textLength) 
	{	
		String name = RandomStringUtils.randomAlphabetic(textLength);
		
		//Capital first letter
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		
		return name;
	}
	
	public String rPass(int passLength) 
	{
		return RandomStringUtils.randomAlphanumeric(passLength);
	}
	
	public String rEmail(int emailLength) 
	{
		return RandomStringUtils.randomAlphabetic(emailLength).toLowerCase() + "@gmail.com";			
	}
	
	public String rAddress(int addressLength) 
	{
		Random r = new Random();
		String address = RandomStringUtils.randomAlphabetic(addressLength);
		
		//Capital first letter
		address = address.substring(0, 1).toUpperCase() + address.substring(1).toLowerCase() +
				" " + String.valueOf(r.nextInt(89) + 10);
		
		return address;			
	}
	
	public Client rClient() {
		
		//System.out.println("Client");
	
		return new Client((rName(3) + "UserName"), rPass(3) + "PASS", 
						  (rName(3) + " " + rName(3) + "Name"), 
						   rEmail(3), rAddress(3)  + "Address");
	}
	
		
	public Container rContainer() {
		
		Random r = new Random();
		
		Client[] clients = DatabaseData.getClients();
		Content[] contents = DatabaseData.getContents();
		Location[] locations = DatabaseData.getLocations();
		Container[] containers = DatabaseData.getContainers();// this is here for the second print loop to show wierd stuff :D - Andrew
		
		int numberOfClients = clients.length;
		int numberOfContents = contents.length;
		int numberOfLocations = locations.length;
		
		/*for (int i = 0; i < containers.length; i++) {
			System.out.println(containers[i] + " " + containers[i].getContainerEnvironment().getTemp());
		}*/
		
		//System.out.println("Container");
		return new Container(clients[r.nextInt(numberOfClients)], 
							 contents[r.nextInt(numberOfContents)], 
							 locations[r.nextInt(numberOfLocations)]);
	}
	
	public ContainerJourney rCJs() {
		
		Random r = new Random();
		
		Container[] containers = DatabaseData.getContainers();
		Location[] locations = DatabaseData.getLocations();
		
		ContainerJourney[] cjs = DatabaseData.getJournies();
		Location start;
		Location end;
		
		int numberOfContainers = containers.length;
		int numberOfLocations = locations.length;
		int month;
		int endDay;
		int startday;
		
		month = r.nextInt(8) + 5;
		startday = r.nextInt(23) + 4;
		
		if (month == 5) {
			do {
				endDay = r.nextInt(28) + 1;
				
			} while(startday >= endDay);
		}
		else {
			endDay = r.nextInt(28) + 1;
		}
		
		/*for (int i = 0; i < cjs.length; i++) {
			System.out.println(cjs[i]);
		}*/
		
		
		//System.out.println("CJ");
		
		start = locations[r.nextInt(numberOfLocations)];
		
		do {
		    end = locations[r.nextInt(numberOfLocations)];

		} while(start == end);
		
		ContainerJourney a = new ContainerJourney(start, end,
				containers[r.nextInt(numberOfContainers)], 
				LocalDate.of(2020, 5, startday), 
				LocalDate.of(2020, month, endDay));
		
		//System.out.println(a);
		
		return a;
		}
	
	/* not sure if we want this or not 
	public ContainerJourney rCJswithNewContainer() {
			
			Random r = new Random();
			Container cont = rContainer();
			Location[] locations = DatabaseData.getLocations();
			
			int numberOfLocations = locations.length;
			
			return new ContainerJourney(locations[r.nextInt(numberOfLocations)],
										locations[r.nextInt(numberOfLocations)],
										cont);
	} */
	
	
	
	/*public static void main(String[] args) {
		
		Automation a = new Automation();
	
		Client randoo = a.rClient();
		Container rCon = a.rContainer();
		ContainerJourney cJ1 = a.rCJs();
		//ContainerJourney cJ2 = a.rCJswithNewContainer();
		
		
		System.out.println(randoo);
		System.out.println(rCon);
		System.out.println(cJ1);
		//System.out.println(cJ2);
		
	}*/

}
