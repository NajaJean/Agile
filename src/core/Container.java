package core;


public class Container {
	
	private int containerID; 
	private Client client;
	private Environment enviro;
	private Content content;
	
	private static int count = 1;
	
	public Container(Client client, Environment enviro, Content content) {
		this.containerID = count++;
		this.client = client;
		this.enviro = enviro;
		this.content = content;
	}
	
	public Container(Environment enviro) {
		this.containerID = count++;
		this.enviro = enviro;
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
	
	public NotifyObject setContainerContent(Content content) {
		this.content = content;
		return new NotifyObject(100, "Content update successful");
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
}
