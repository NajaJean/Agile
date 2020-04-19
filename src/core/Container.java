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
		this.enviro.setTemp(enviro.getTemp());
		this.enviro.setHumidity(enviro.getHumidity());
		this.enviro.setPressure(enviro.getPressure());
	}
	
	public Content getContainerContent() {
		return content;
	}
	
	public NotifyObject responseLogisticUpdate() {
		NotifyObject response;
		response = new NotifyObject(101, "Environment is updated successfully");
		return response;
	}
	
	public NotifyObject responseFillContainer(Content con) {
		NotifyObject response;
		if (con == null) {
			response = new NotifyObject(55, "All fields has to be filled");
		}
		else {
			response = new NotifyObject(56, "Content is updated successfully");
		}
		return response;
	}
	
	public void setContainerContent(Content content) {
		this.content = content;
	}
	
	public static Container findContainer(String id, Container[] containers) {
		Container result = null;
		for(int i = 0; i< containers.length; i++) {
			if(containers[i].containerID == Integer.parseInt(id)) {
				result = containers[i];
				break;
			}
		}
		return result;
	}
	
	public NotifyObject checkBookingOfContainer(int id) {
		NotifyObject notification;
		if (id != 0) {
			notification = new NotifyObject(69, "Container is succesfully booked");
		} else {
			notification = new NotifyObject(666, "No empty containers available");
		} return notification;
	}
	
	@Override
    public String toString() {
    	return "'"+ containerID + "', '" + client.getID() + "', '" + enviro.getEnviro_ID() + "', '" + 
    			content.getContentID() + "'";
    }
}
