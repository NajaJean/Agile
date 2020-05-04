package core;


public class Container implements Search, DatabaseEntity {
	
	private int containerID; 
	private Client client;
	private Environment enviro;
	private Content content;
	private Location location;
	
	private static int count = 1;
	
	public Container() {}
	
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
	
	public static void resetCount() {
		count = 1;
	}

	@Override
	public int findFromID(int containerID, Object[] containers) {
		int index = -1;
		for(int i = 0; i < containers.length; i++) {
			if(containerID == ((Container)containers[i]).containerID) {
				index = i;
				break;
			}
		}
		return index;
	}

	@Override
	public int findFromString(String clientID, Object[] containers) {
		int index = -1;
		for(int i = 0; i < containers.length; i++) {
			if(Integer.parseInt(clientID) == ((Container)containers[i]).client.getID()) {
				index = i;
				break;
			}
		}
		return index;
	}

	@Override
	public int findFromStrings(String firstString, String secondString, Object[] containers) {
		String str = (firstString.isEmpty() ? secondString : firstString);
		return findFromString(str, containers);
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

	@Override
	public String databaseTable() {
		return "Containers";
	}

	@Override
	public int entityID() {
		return containerID;
	}

	@Override
	public String addValues() {
		return this.toString();
	}
}
