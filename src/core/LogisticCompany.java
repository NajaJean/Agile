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
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}
