package core;


public class Location {
    private String name;
    private int ID;
    private double[] GPScoord = new double[2];
    
    private static int count = 1;
    
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
    
    public void incrementOneGPSCoord(int axis, double j) {
    	GPScoord[axis] = GPScoord[axis] + j;
    }
    
    public static Location findLocation(int id, Location[] locs) {
    	Location result = null;
		try {
			result = locs[0];
			for(int i = 0; i< locs.length; i++) {
				if(locs[i].ID == id) {
					result = locs[i];
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return result; 
	}
    
    public static Location findLocation(String name, Location[] locs) {
    	Location result = null;
		try {
			result = locs[0];
			for(int i = 0; i< locs.length; i++) {
				if(locs[i].name.equals(name)) {
					result = locs[i];
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return result; 
	}
    
    @Override
    public String toString() {
    	return "'"+ name + "', '" + ID + "', '" + GPScoord[0] + "', '" + 
    			GPScoord[1] + "'";
    }
}
