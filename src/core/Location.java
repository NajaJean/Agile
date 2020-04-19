package core;


public class Location {
    private String name;
    private int ID;
    private double[] GPScoord;
    
    private static int count = 1;
    
    public Location(String name, double[] gps) {
    	this.name = name;
    	this.GPScoord = gps;
    	this.ID = count++;
    }
    
    public String getLocationName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public int getLocationID() {
    	return ID;
    }
    
    public double[] getGPScoord() {
    	return GPScoord;
    }
    
    public void setGPSCoordX(double x) {
    	GPScoord[0] = x;    	
    }
    public void setGPSCoordY(double y) {
    	GPScoord[1] = y;    	
    }
    
    public void setOneGPSCoord(int i, double j) {
    	GPScoord[i] = j;
    }
    
    public void incrementOneGPSCoord(int axis, double j) {
    	GPScoord[axis] = GPScoord[axis] + j;
    }
    
    public static Location findLocation(String id, Location[] locs) {
		Location result = null;
		for(int i = 0; i< locs.length; i++) {
			if(locs[i].ID == Integer.parseInt(id)) {
				result = locs[i];
				break;
			}
		}
		return result;
	}
}
