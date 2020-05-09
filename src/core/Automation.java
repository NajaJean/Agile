package core;

import java.time.LocalDate;
import java.util.Random;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.RandomStringUtils;

import ExternalData.DatabaseData;

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
		
		return new Client((rName(3) + "UserName"), rPass(3) + "PASS", 
						  (rName(3) + " " + rName(3) + "Name"), 
						   rEmail(3), rAddress(3)  + "Address");
	}
	
		
	public Container rContainer() {
		
		Random r = new Random();
		
		Client[] clients = DatabaseData.getClients();
		Content[] contents = DatabaseData.getContents();
		Location[] locations = DatabaseData.getLocations();
		
		int numberOfClients = clients.length;
		int numberOfContents = contents.length;
		int numberOfLocations = locations.length;

		return new Container(clients[r.nextInt(numberOfClients)], 
							 contents[r.nextInt(numberOfContents)], 
							 locations[r.nextInt(numberOfLocations)]);
	}
	

	public Client randomClient() {
		Random r = new Random();
		Client[] clients = DatabaseData.getClients();
		return clients[r.nextInt(clients.length)];
	}
	public Content randomContent() {
		Random r = new Random();
		Content[] contents = DatabaseData.getContents();
		return contents[r.nextInt(contents.length)];
	}
}
