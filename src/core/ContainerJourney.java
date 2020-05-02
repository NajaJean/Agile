package core;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ContainerJourney implements Search {
	private int journeyID;
	private Location start;
	private Location end;
	private Container container;
	private double[] currentGps = new double[2];
	private LocalDate startDate;
	private LocalDate endDate;
	
	private static int count = 1;
	
	public ContainerJourney() {}
	
	public ContainerJourney(Location start, Location end, Container container,
							LocalDate startDate, LocalDate endDate) {
		this.journeyID = count++;
		this.start = start;
		this.end = end;
		this.container = container;
		this.startDate = startDate;
		this.endDate = endDate;
		
		double[] temp = start.getGPScoord();
		currentGps = new double[2];
		currentGps[0] = temp[0];
		currentGps[1] = temp[1];
	}
	
	public ContainerJourney(Location start, Location end, Container container,
							LocalDate endDate) {
		this.journeyID = count++;
		this.start = start;
		this.end = end;
		this.container = container;
		this.startDate = LocalDate.now();
		this.endDate = endDate;
		
		double[] temp = start.getGPScoord();
		currentGps = new double[2];
		currentGps[0] = temp[0];
		currentGps[1] = temp[1];
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	} 
	
	public ContainerJourney(Location start, Location end, Container container,double currentx, double currenty,
			LocalDate startDate, LocalDate endDate) {
		// Overloaded method just when reading in the database 
		this.journeyID = count++;
		this.start = start;
		this.end = end;
		this.container = container;
		currentGps[0] = currentx;
		currentGps[1] = currenty;

		this.startDate = startDate;
		this.endDate = endDate;

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
	
	public static void resetCount() {
		count = 1;
	}
	
	// Needed to update the current location of the container
	// Returns a NotifyObject to notify the client in the case where current == end
	
	public void setCurrentLocation(double[] current) {
		this.currentGps[0] = current[0];
		this.currentGps[1] = current[1];
		updateWeather();
		/*NotifyObject notification;
		if ((current[0] == end.getGPScoord()[0]) && (current[1] == end.getGPScoord()[1])) {
			notification = new NotifyObject(100, "Client is notified of arrival");
		} else { 
			notification = new NotifyObject(0, "Container is in transit");
		} return notification;*/
	}
	
	public void moveContainerOnJ() 
	{
		double travelDays = (double) ChronoUnit.DAYS.between(getStartDate(), getEndDate());
	
		double[] newCurrents = new double[2];
		
		if ((getCurrentX() == getEndLocX()) && (getCurrentY() == getEndLocY()))
		{
			newCurrents[0] = getEndLocX();
			newCurrents[1] = getEndLocY();
		}
		else 
		{
			newCurrents[0] = getCurrentX() + ((getEndLocX() - getStartLocX()) / travelDays);
			
			newCurrents[1] = getCurrentY() + ((getEndLocY() - getStartLocY()) / travelDays);
		}
		
		setCurrentLocation(newCurrents);
	}
	
	public void updateWeather() {
		Weather[] climate;
		climate = Climate.getClimate();
		
		for (int i = 0; i < climate.length; i++) {
				if (((climate[i].from[0] <= getCurrentX()) && 
						(getCurrentX() < climate[i].to[0])) &&
					((climate[i].from[1] <= getCurrentY()) && 
							(getCurrentY() < climate[i].to[1]))){
					getContaineronJourney().setContainerEnvironment(climate[i].localWeather);
					
				}
		}	
	}

	@Override
	public int findFromID(int ID, Object[] cJs) {
		int index = -1;
		try {
			for(int i = 0; i < cJs.length; i++) {
				if(ID == ((ContainerJourney)cJs[i]).journeyID) {
					index = i;
					break;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return index;
	}

	@Override
	public int findFromString(String containerID, Object[] cJs) {
		int index = -1;
		try {
			for(int i = 0; i < cJs.length; i++) {
				if(Integer.parseInt(containerID) == ((ContainerJourney)cJs[i]).container.getContainerID()) {
					index = i;
					break;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return index;
	}

	@Override
	public int findFromStrings(String firstString, String secondString, Object[] cJs) {
		String str = (firstString.isEmpty() ? secondString : firstString);
		return findFromString(str, cJs);
	}
	
    @Override
    public String toString() {
    	return "'"+ journeyID + "', '" + start.getLocationID() + "', '" + end.getLocationID() + "', '" + 
    			container.getContainerID() + "', '" + currentGps[0] + "', '" + currentGps[1] + "', '" +
    			startDate + "', '" + endDate + "'";
    }
    
    
    //Testing weather update keep it until the update is implemented in the UI
    //and autoweather is working
    /*public static void main(String[] args) {
    	double[] cphgpscoords = {730.0, 128.0};
       	double[] tokyogpscoords = {1423.0, 245.0};
       	double[] sydneygpscoords = {1495.0, 750.0};
       	double[] nygpscoords = {290.0, 225.0};
       	double[] hawaiigpscoords = {1735.0, 265.0};
       	double[] riogpscoords = {420.0, 670.0};
       	

       	Location CPH = new Location("Copenhagen", cphgpscoords);
       	Location Tokyo = new Location("Tokyo", tokyogpscoords);
       	Location Sydney = new Location("Sydney", sydneygpscoords);
       	Location NY = new Location("New York", nygpscoords);
       	Location Hawaii = new Location("Hawaii", hawaiigpscoords);
       	Location Rio = new Location("Rio", riogpscoords);
       	
       	Environment testEnv = new Environment(5.0, 5.0, 5.0);
       	
       	Client testClient = new Client("asd", "yoDaddy69", "1", "2", "3");
       	
       	Content stuff = new Content("Stuff", testEnv, 1.0);
       	
       	Container shippingContainer1 = new Container(testClient, testEnv, stuff, CPH);
       	Container shippingContainer2 = new Container(testClient, testEnv, stuff, Hawaii);
       	Container shippingContainer3 = new Container(testClient, testEnv, stuff, Rio);
       	
       	//we want this
       	ContainerJourney[] cJs = {new ContainerJourney(CPH, NY, shippingContainer1),
       									  new ContainerJourney(Hawaii, Tokyo, shippingContainer2),
       									  new ContainerJourney(Rio, Sydney, shippingContainer3)
       									  };
       	System.out.println(cJs[0].getContaineronJourney().getContainerEnvironment());
       	cJs[0].setCurrentLocation(hawaiigpscoords);
       	System.out.println(cJs[0].getContaineronJourney().getContainerEnvironment());
	}*/
}
