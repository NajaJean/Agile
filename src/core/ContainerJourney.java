package core;


public class ContainerJourney {
	private int journeyID;
	private Location start;
	private Location current;
	private Location end;
	private Container container;
	
	private static int count = 0;
	
	
	public ContainerJourney(Location start, Location end, Container container) {
		this.journeyID = count++;
		this.start = start;
		this.end = end;
		this.container = container;
		
		// I do not think we need to pass another parameter for the current location 
		// because the current location should start at start when the container journey
		// is initialized
		this.current = start;
	}
	
	public int getJourneyID() {
		return journeyID;
	}
	
	public Location getStartLocation() {
		return start;
	}
	
	public Location getEndLocation() {
		return end;
	}
	
	public Container getContaineronJourney() {
		return container;
	}
	
	// Needed to update the current location of the container
	// Returns a NotifyObject to notify the client in the case where current == end
	public NotifyObject setCurrentLocation(Location current) {
		this.current = current;
		
		NotifyObject notification;
		if (current == end) {
			notification = new NotifyObject(100, "Client is notified of arrival");
		} else { 
			notification = new NotifyObject(0, "Container is in transit");
		} return notification;
	}
}
