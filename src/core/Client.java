package core;

public class Client extends User {
    private String username;
    private String password;
    private String name;
    private String email;
    private String address;
    private int ID;
    private static int count = 1;
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
    public String toString() {
    	return "'"+ ID + "', '" + name + "', '" + email + "', '" + 
    			address + "', '" + username + "', '" + password + "'";
    }
    
	public static Client findClient(int id, Client[] clients) {
		Client result = null;
		try {
			for(int i = 0; i< clients.length; i++) {
				if(clients[i].ID == id) {
					result = clients[i];
					break;
				}
			}
		} catch (Exception e) {
		//	result = clients[0];
		}

		return result;
	}
	public static Client findClient(String user,String pass, Client[] clients) {
		Client result = null;
		try {
			for(int i = 0; i< clients.length; i++) {
				if(pass.equals(clients[i].password) && user.equals(clients[i].username)) {
					result = clients[i];
					break;
				}
			}
		} catch (Exception e) {
			//result = clients[0];
		}

		return result;
	}
	public static Client findClient(String email, Client[] clients) {
		Client result = null;
		try {
			for(int i = 0; i< clients.length; i++) {
				if(email.equals(clients[i].email)) {
					result = clients[i];
					break;
				}
			}
		} catch (Exception e) {
			//result = clients[0];
		}

		return result;
	}
}
