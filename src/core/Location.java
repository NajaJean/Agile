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
    
    public double getGPScoordX() {
    	return GPScoord[0];
    }
    
    public double getGPScoordY() {
    	return GPScoord[1];
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
    
    public static Location findLocation(int id, Location[] locs) {
    	Location result = null;
		try {
			for(int i = 0; i< locs.length; i++) {
				if(locs[i].ID == id) {
					result = locs[i];
					break;
				}
			}
		} catch (Exception e) {
			result = locs[0];
		}

		return result; 
	}
    
    public static Location findLocation(String name, Location[] locs) {
    	Location result = null;
		try {
			for(int i = 0; i< locs.length; i++) {
				if(locs[i].name == name) {
					result = locs[i];
					break;
				}
			}
		} catch (Exception e) {
			result = locs[0];
		}

		return result; 
	}
}
