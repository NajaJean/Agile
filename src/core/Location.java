package core;

public class Location implements Search {
    private String name;
    private int ID;
    private double[] GPScoord = new double[2];
    
    private static int count = 1;
    
    public Location() {}
    
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
    
    public static void resetCount() {
    	count = 1;
    }
    
    public Environment getEnvironment() {
		
		Weather[] climate;
		climate = Climate.getClimate();
		
		Environment[] toAssign = DatabaseData.getEnvironments();
		Environment localEnviro = toAssign[0];
		for (int i = 0; i < climate.length; i++) {
			if (((climate[i].from[0] <= GPScoord[0]) && 
					(GPScoord[0] < climate[i].to[0])) &&
				((climate[i].from[1] <= GPScoord[1]) && 
						(GPScoord[1] < climate[i].to[1])))
			{
				localEnviro = toAssign[i + 1]; 
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
	public int findFromID(int ID, Object[] locs) {
    	int index = -1;
		try {
			for(int i = 0; i < locs.length; i++) {
				if(ID == ((Location)locs[i]).ID) {
					index = i;
					break;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return index; 
	}

	@Override
	public int findFromString(String name, Object[] locs) {
    	int index = -1;
		try {
			for(int i = 0; i < locs.length; i++) {
				if(name.equals(((Location)locs[i]).name)) {
					index = i;
					break;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return index; 
	}

	@Override
	public int findFromStrings(String firstString, String secondString, Object[] locs) {
		String str = (firstString.isEmpty() ? secondString : firstString);
		return findFromString(str, locs);
	}
	
    @Override
    public String toString() {
    	return "'"+ name + "', '" + ID + "', '" + GPScoord[0] + "', '" + 
    			GPScoord[1] + "'";
    }
    
    public double euclideanDistance(double[] loc2) {
    	return Math.sqrt((loc2[1] - this.getGPScoordY()) * (loc2[1] - this.getGPScoordY()) + 
    			(loc2[0] - this.getGPScoordX()) * (loc2[0] - this.getGPScoordX()));
    }
}
