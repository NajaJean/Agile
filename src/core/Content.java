package core;


public class Content implements Search {
	private String name;
	private Environment enviro;
	private double threshold;
	private int content_ID;
    private static int count = 1;
    
	public Content() {}
    
	public Content(String name, Environment enviro, double threshold) {
		this.name = name;
		this.enviro = enviro;
		this.threshold = threshold;
		this.content_ID = count++;
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
		return content_ID;
	}
	
	public static void resetCount() {
		count = 1;
	}
	
	@Override
	public int findFromID(int ID, Object[] contents) {
		int index = -1;
		try {
			for(int i = 0; i < contents.length; i++) {
				if(ID == ((Content)contents[i]).content_ID) {
					index = i;
					break;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return index;
	}
	
	@Override
	public int findFromString(String name, Object[] contents) {
		int index = -1;
		try {
			for(int i = 0; i < contents.length; i++) {
				if(name.equals(((Content)contents[i]).name)) {
					index = i;
					break;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return index;
	}
	
	@Override
	public int findFromStrings(String firstString, String secondString, Object[] contents) {
		String str = (firstString.isEmpty() ? secondString : firstString);
		return findFromString(str, contents);
	}
	
	@Override
    public String toString() {
    	return "'"+ content_ID + "', '" + name + "', '" + enviro.getEnviro_ID() + "', '" + 
    			threshold + "'";
    }
}
