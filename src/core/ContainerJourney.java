package core;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import ExternalData.DatabaseData;
import ExternalData.DatabaseEntity;

public class ContainerJourney extends Search implements DatabaseEntity {
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
		this.ID = count++;
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
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	} 
	
	public ContainerJourney(Location start, Location end, Container container,double currentx, double currenty,
			LocalDate startDate, LocalDate endDate) { 
		this.ID = count++;
		this.start = start;
		this.end = end;
		this.container = container;
		currentGps[0] = currentx;
		currentGps[1] = currenty;

		this.startDate = startDate;
		this.endDate = endDate;

	}
	
	public int getJourneyID() {
		return ID;
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
	
	public void setCurrentLocation(double[] current) {
		this.currentGps[0] = current[0];
		this.currentGps[1] = current[1];
		updateWeather();
	}
	
	public void moveContainerOnJ() {
		double travelDays = (double) ChronoUnit.DAYS.between(getStartDate(), getEndDate());
		double[] newCurrents = new double[2];
		
		if ((getCurrentX() == getEndLocX()) && (getCurrentY() == getEndLocY()))	{
			newCurrents[0] = getEndLocX();
			newCurrents[1] = getEndLocY();
		}
		else {
			newCurrents[0] = getCurrentX() + ((getEndLocX() - getStartLocX()) / travelDays);
			newCurrents[1] = getCurrentY() + ((getEndLocY() - getStartLocY()) / travelDays);
		}
		setCurrentLocation(newCurrents);
	}
	
	public void updateWeather() {
		Weather[] climate;
		climate = Climate.getClimate();
		Environment[] toAssign = DatabaseData.getEnvironments();
		
		for (int i = 0; i < climate.length; i++) {
				if (((climate[i].from[0] <= getCurrentX()) && 
						(getCurrentX() < climate[i].to[0])) &&
					((climate[i].from[1] <= getCurrentY()) && 
							(getCurrentY() < climate[i].to[1]))){
					getContaineronJourney().setContainerEnvironment(toAssign[i + 1]);
					
				}
		}	
	}
	
	public static ContainerJourney[] clientJournies(ContainerJourney[] Journies, Client c) {
		ArrayList<ContainerJourney> clientContainersList = new ArrayList<ContainerJourney>();
 		for (int i = 0; i < Journies.length; i++) {
 			if (Journies[i].getContaineronJourney().getClientofContainer() == null) { continue; }
 			if (c.getID() == Journies[i].getContaineronJourney().getClientofContainer().getID()) {
 				clientContainersList.add(Journies[i]);
 			}
 		}
 		
 		ContainerJourney[] ClientJournies = new ContainerJourney[clientContainersList.size()];
 		ClientJournies = clientContainersList.toArray(ClientJournies);
 		return ClientJournies;
	}
	
	public static ContainerJourney longestJourney(ContainerJourney[] ClientJournies) {
		double len = 0;
 		ContainerJourney con = ClientJournies[0];
 		for(int i=0; i<ClientJournies.length;i++) {
 			double newlen = ClientJournies[i].getEndLocation().euclideanDistance(ClientJournies[i].getCurrentLocationDoubleA());
 			if(newlen>len) {
 				len = newlen;
 				con = ClientJournies[i];
 			}
 		}
 		return con;
	}

	@Override
	public int find(String containerID, Object[] cJs) {
		int index = -1;
			for(int i = 0; i < cJs.length; i++) {
				if(Integer.parseInt(containerID) == ((ContainerJourney)cJs[i]).container.getContainerID()) {
					index = i;
					break;
				}
			}
		return index;
	}
	
    @Override
    public String toString() {
    	return "'"+ ID + "', '" + start.getLocationID() + "', '" + end.getLocationID() + "', '" + 
    			container.getContainerID() + "', '" + currentGps[0] + "', '" + currentGps[1] + "', '" +
    			startDate + "', '" + endDate + "'";
    }

	@Override
	public String databaseTable() {
		return "Journies";
	}

	@Override
	public int entityID() {
		return ID;
	}

	@Override
	public String addValues() {
		return this.toString();
	}
	
}
