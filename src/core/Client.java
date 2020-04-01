package core;


public class Client {
    private String username;
    private String password;
    private String name;
    private String email;
    private String address;
    private int ID;
    private static int count = 0;
    
    public Client(String username, String password, String name, String email, String address) {
    	this.username = username;
    	this.password = password;
    	this.name = name;
		this.email = email;
		this.address = address;
		this.ID = count++;
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
    @Override
    public String toString() {
    	return "'"+ ID + "', '" + name + "', '" + email + "', '" + 
    			address + "', '" + username + "', '" + password + "'";
    }
}
