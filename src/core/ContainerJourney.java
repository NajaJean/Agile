package core;


public class ContainerJourney {
	private int journeyID;
	private Location start;
	private Location end;
	private Container container;
	private double[] currentGps = new double[2];
	
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
		
		double[] temp = start.getGPScoord();
		currentGps = new double[2];
		currentGps[0] = temp[0];
		currentGps[1] = temp[1];
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
	
	public double[] getCurrentLocationDoubleA() {
		return currentGps;
	}
	
	public double getCurrentX() {
		return currentGps[0];
	}
	public double getCurrentY() {
		return currentGps[1];
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
	
	public void setCurrentLocation(double[] current) {
		this.currentGps[0] = current[0];
		this.currentGps[1] = current[1];
		
		/*NotifyObject notification;
		if ((current[0] == end.getGPScoord()[0]) && (current[1] == end.getGPScoord()[1])) {
			notification = new NotifyObject(100, "Client is notified of arrival");
		} else { 
			notification = new NotifyObject(0, "Container is in transit");
		} return notification;*/
	}
	
	
	public static ContainerJourney findJourney(String id, ContainerJourney[] cJs) {
		ContainerJourney result = null;
		try {
			for(int i = 0; i< cJs.length; i++) {
				if(cJs[i].journeyID == Integer.parseInt(id)) {
					result = cJs[i];
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
//			result = cJs[0];
		}
		
//		assert result != null;

		return result;
	}
	
	
	
	public static ContainerJourney findJourneyFromContainerID(String containerID, ContainerJourney[] cJs) {
		ContainerJourney result = null;
		try {
			for(int i = 0; i< cJs.length; i++) {
				if(cJs[i].container.getContainerID() == Integer.parseInt(containerID)) {
					result = cJs[i];
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
//			result = cJs[0];
		}
		
//		assert result != null;

		return result;
	}
	
	
    @Override
    public String toString() {
    	return "'"+ journeyID + "', '" + start + "', '" + end + "', '" + 
    			container + "', '" + currentGps[0] + "', '" + currentGps[1] + "'";
    }
}
