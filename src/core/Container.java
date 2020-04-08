package core;


public class Container {
	
	private int containerID; 
	private Client client;
	private Environment enviro;
	private Content content;
	
	private static int count = 0;
	
	public Container(Client client, Environment enviro, Content content) {
		this.containerID = count++;
		this.client = client;
		this.enviro = enviro;
		this.content = content;
	}
	
	public Container(Client client) {
		this.containerID = count++;
		this.client = client;
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
}
