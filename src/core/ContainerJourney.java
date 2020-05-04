package core;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ContainerJourney implements Search, DatabaseEntity {
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
		Environment[] toAssign = DatabaseData.getEnvironments();
		Environment localEnviro = toAssign[0];
		
		for (int i = 0; i < climate.length; i++) {
				if (((climate[i].from[0] <= getCurrentX()) && 
						(getCurrentX() < climate[i].to[0])) &&
					((climate[i].from[1] <= getCurrentY()) && 
							(getCurrentY() < climate[i].to[1]))){
					getContaineronJourney().setContainerEnvironment(toAssign[i + 1]);
					
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

	@Override
	public String databaseTable() {
		return "Journies";
	}

	@Override
	public int entityID() {
		return journeyID;
	}

	@Override
	public String addValues() {
		return this.toString();
	}
}
