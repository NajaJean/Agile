package core;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Client extends User implements Search {
    private String username;
    private String password;
    private String name;
    private String email;
    private String address;
    private int ID;
    private static int count = 1;
    private boolean loggedIn;
    
    public Client() {};
    
    public Client(String username, String password, String name, String email, String address) {
    	this.username = username;
    	this.password = password;
    	this.name = name;
		this.email = email;
		this.address = address;
		this.ID = count++;
		this.loggedIn = false;
	} 
    
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getUsername() {
    	return username;
    }
    public void setUserName(String username) {
    	this.username = username;
    }
    
    public String getPassword() {
    	return password;
    }
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public String getAddress() {
    	return address;
    }
    
    public void setAddress(String address) {
    	this.address = address;
    }
    
    public int getID() {
    	return ID;
    }
    
    public static void resetCount() {
    	count = 1;
    }
    
    public boolean isLoggedIn() {
    	return this.loggedIn;
    }
    
    public NotifyObject logIn(boolean b) {
    	NotifyObject response;
    	if (b) {
    		this.loggedIn = true;
    		response = new NotifyObject(100, "Login successful");
    	}
    	else {
    		this.loggedIn = false;
    		response = new NotifyObject(150, "Incorrect username or password");
    	}
    	return response;
    }
    
    public NotifyObject configure() {
    	NotifyObject response;
    	response = new NotifyObject(101, "Your information is updated!");
    	return response;
    }
    
    @Override
    public int findFromID(int ID, Object[] clients) {
		int index = -1;
		try {
			for(int i = 0; i < clients.length; i++) {
				if(ID == ((Client)clients[i]).ID) {
					index = i;
					break;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return index;
    }
    
    @Override
    public int findFromString(String email, Object[] clients) {
		int index = -1;
		try {
			for(int i = 0; i < clients.length; i++) {
				if(email.equals(((Client)clients[i]).email)) {
					index = i;
					break;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return index;
    }
    
    @Override
    public int findFromStrings(String username, String password, Object[] clients) {
		int index = -1;
		try {
			for(int i = 0; i < clients.length; i++) {
				if(username.equals(((Client)clients[i]).username) && password.equals(((Client)clients[i]).password)) {
					index = i;
					break;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return index;
    }
    
    @Override
    public String toString() {
    	return "'"+ ID + "', '" + name + "', '" + email + "', '" + 
    			address + "', '" + username + "', '" + password + "'";
    }
   
    public ContainerJourney[] getClientsCJs(ContainerJourney[] containerJourneys) {
   	 
   	 ArrayList<ContainerJourney> clientContainersList = new ArrayList<ContainerJourney>();
   	 
		for (int i = 0; i < containerJourneys.length; i++) {
			
			if ((getID() == containerJourneys[i].getContaineronJourney().getClientofContainer().getID()) && 
					((int)ChronoUnit.DAYS.between(containerJourneys[i].getStartDate(), Calendar.getSystemDate()) >= 0) && 
					((int)ChronoUnit.DAYS.between( Calendar.getSystemDate(), containerJourneys[i].getEndDate()) >= 0))
			{
			
				clientContainersList.add(containerJourneys[i]);
			}
			
		}
		
		ContainerJourney[] clientConts = new ContainerJourney[clientContainersList.size()];
		clientConts = clientContainersList.toArray(clientConts);
		
		return clientConts;
    }
    
}
