package core;


public class Location {
    private String name;
    private int ID;
    private static double[] GPScoord = new double[2];
    
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
    
    public static double getGPScoordX() {
    	return GPScoord[0];
    }
    
    public static double getGPScoordY() {
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
    
    public static Environment getEnvironment() {
		
		Weather[] climate;
		climate = Climate.getClimate();
		
		Environment localEnviro = new Environment(0.0, 0.0, 0.0);
		for (int i = 0; i < climate.length; i++) {
			if (((climate[i].from[0] <= getGPScoordX()) && 
					(getGPScoordX() < climate[i].to[0])) &&
				((climate[i].from[1] <= getGPScoordY()) && 
						(getGPScoordY() < climate[i].to[1])))
			{
				localEnviro = climate[i].localWeather;
				
			}
		}
		return localEnviro;
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
