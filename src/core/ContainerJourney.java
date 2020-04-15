package core;


public class ContainerJourney {
	private int journeyID;
	private Location start;
	private Location current;
	private Location end;
	private Container container;
	
	private static int count = 1;
	
	
	public ContainerJourney(Location start, Location end, Container container) {
		this.journeyID = count++;
		this.start = start;
		this.end = end;
		this.container = container;
		
		// I do not think we need to pass another parameter for the current location 
		// because the current location should start at start when the container journey
		// is initialized
		
		//i added this because if you just pass the start location it does a shallow copy which messes up things, 
		//if you know how to pass the start location easier a deep copy pls change this
		double[] currentGps = {start.getGPScoord()[0], start.getGPScoord()[1]};
		this.current = new Location("Current Location", currentGps);
	}
	
	public int getJourneyID() {
		return journeyID;
	}
	
	public Location getStartLocation() {
		return start;
	}
	
	public double getStartLocX() {
		return start.getGPScoord()[0];
	}
	public double getStartLocY() {
		return start.getGPScoord()[1];
	}
	
	public Location getCurrentLocation() {
		return current;
	}
	
	public double getCurrentLocX() {
		return current.getGPScoord()[0];
	}
	public double getCurrentLocY() {
		return current.getGPScoord()[1];
	}
	
	public Location getEndLocation() {
		return end;
	}
	
	public double getEndLocX() {
		return end.getGPScoord()[0];
	}
	public double getEndLocY() {
		return end.getGPScoord()[1];
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
