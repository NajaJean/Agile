package core;


public class Container {
	
	private int containerID; 
	private Client client;
	private Environment enviro;
	private Content content;
	private Location location;
	
	private static int count = 1;
	
	public Container(Client client, Environment enviro, Content content, Location location) {
		this.containerID = count++;
		this.client = client;
		this.enviro = enviro;
		this.content = content;
		this.location = location;
	}
	
	public Container(Environment enviro,Location location) {
		this.containerID = count++;
		this.enviro = enviro;
		this.location = location;
	}
	
	public Container(Client client, Content content, Location location) {
		this.containerID = count++;
		this.client = client;
		this.enviro = location.getEnvironment();
		this.content = content;
		this.location = location;
	}
	
	public Container(Location location) {
		this.containerID = count++;
		this.enviro = location.getEnvironment();
		this.location = location;
	}
	
	public Location getContainerLocation() {
		return location;
	}
	
	public void setContainerLocation(Location loc) {
		this.location = loc;
	}
	
	public int getContainerID() {
		return containerID;
	}
	
	public Client getClientofContainer() {
		return client;
	}
	
	public void setClientofContainer(Client client) {
		this.client = client;
	}
	
	public Environment getContainerEnvironment() {
		return enviro;
	}
	
	public void setContainerEnvironment(Environment enviro) {
		this.enviro = enviro;
	}
	
	public Content getContainerContent() {
		return content;
	}
	
	public void setContainerContent(Content content) {
		this.content = content;
	}
	
	public static Container findContainer(int id, Container[] containers) {
		Container result = null;
		for(int i = 0; i< containers.length; i++) {
			if(containers[i].containerID == id) {
				result = containers[i];
				break;
			}
		}
		return result;
	}
	

	@Override
    public String toString() {
		String result = "";
		try {
			result = "'"+containerID+"', '"+ client.getID() + "', '" + enviro.getEnviro_ID() + "', '" + 
	    			content.getContentID() + "', '" + location.getLocationID()+"'";
		}
		catch(Exception e) {
			result = "'"+containerID+"', NULL, '" + enviro.getEnviro_ID() + "', NULL, '" +  location.getLocationID()+"'";
		}
    	return result;
    }
	
	// What is dis??
/*	public static void main(String[] args) {
		double[] hawaiigpscoords = {1735.0, 265.0};
		Location Hawaii = new Location("Hawaii", hawaiigpscoords);
		Container test = new Container(Hawaii);
		System.out.println(test.getContainerEnvironment());
	} */
}
