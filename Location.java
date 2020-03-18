
public class Location {
    private String name;
    private int ID;
    private int[] GPScoord;
    
    private static int count = 0;
    
    public Location(String name, int[] gps) {
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
    
    public int[] getGPScoord() {
    	return GPScoord;
    }
}
