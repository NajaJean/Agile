package core;


public class Content extends Search {
	private String name;
	private Environment enviro;
	private double threshold;
    private static int count = 1;
    
	public Content() {}
    
	public Content(String name, Environment enviro, double threshold) {
		this.name = name;
		this.enviro = enviro;
		this.threshold = threshold;
		this.ID = count++;
	}
	
	public String getName() {
    	return name;
    }
	
	public Environment getEnvironment() {
    	return enviro;
    }
	
	public double getThreshold() {
		return threshold;
	}
	
	public int getContentID() {
		return ID;
	}
	
	public static void resetCount() {
		count = 1;
	}
	
	@Override
	public int findFromString(String name, Object[] contents) {
		int index = -1;
			for(int i = 0; i < contents.length; i++) {
				if(name.equals(((Content)contents[i]).name)) {
					index = i;
					break;
				}
			}
		return index;
	}
	
	@Override
    public String toString() {
    	return "'"+ ID + "', '" + name + "', '" + enviro.getEnviro_ID() + "', '" + 
    			threshold + "'";
    }
}
