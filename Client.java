
public class Client {
    private String name;
    private String email;
    private String address;
    private int ID;
    private static int count = 0;
    
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
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
    public void setAddress() {
    	this.address = address;
    }
    public String getID() {
    	return ID;
    }
    
    public Client(String name, String email, String address, int ID) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.ID = count++;
	}
    
}
