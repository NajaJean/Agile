
public class Location {
    private String name;
    private int ID;
    private double[] GPScoord;
    
    private static int count = 0;
    
    public Location(String name, double[] gps) {
    	this.name = name;
    	this.GPScoord = gps;
    	this.ID = count++;
    }
    
    public String getLocationName() {
    	return name;
    }
    
    public int getLocationID() {
    	return ID;
    }
    
    public double[] getGPScoord() {
    	return GPScoord;
    }
}
