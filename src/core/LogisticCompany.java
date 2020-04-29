package core;

public class LogisticCompany {
	private String username = "MSK";
    private String password = "1234";
    private String name = "LongTimeNoSea";
	private int ID = 1;
	private boolean loggedIn;
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}
	public int getID() {
		return ID;
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
    
}
