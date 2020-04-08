package core;

public class Client {
    private String username;
    private String password;
    private String name;
    private String email;
    private String address;
    private int ID;
    private static int count = 0;
    private boolean loggedIn;
    
    public Client(String username, String password, String name, String email, String address) {
    	this.username = username;
    	this.password = password;
    	this.name = name;
		this.email = email;
		this.address = address;
		this.ID = count++;
		this.loggedIn = false;
	} 
    
    public Client(String username, String password) {
    	this.username = username;
    	this.password = password;
    	this.name = "noname";
		this.email = "no email";
		this.address = "homeless";
		this.ID = count++;
	} 
    
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getUserName() {
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
    
    public boolean isLoggedIn() {
    	return this.loggedIn;
    }
    
    public void logIn(boolean b) {
    	if (b) {
    		this.loggedIn = true;
    	}
    	else {
    		this.loggedIn = false;
    	}
    }
    
    @Override
    public String toString() {
    	return "'"+ ID + "', '" + name + "', '" + email + "', '" + 
    			address + "', '" + username + "', '" + password + "'";
    }
    
	public static Client findClient(String id, Client[] clients) {
		Client result = null;
		for(int i = 0; i< clients.length; i++) {
			if(clients[i].ID == Integer.parseInt(id)) {
				result = clients[i];
				break;
			}
		}
		return result;
	}
}
