package core;


public class Environment implements Search {
	private int enviro_ID;
	private double temp;
	private double pressure;
	private double humidity;
	private static int count = 1;
	
	public Environment() {}
	
	public Environment(double temp, double pressure, double humidity) {
		this.temp = temp;
		this.pressure = pressure;
		this.humidity = humidity;
		this.enviro_ID = count++;
	}
	
	public int getEnviro_ID() {
		return enviro_ID;
	}
	
	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	public double getTemp() {
		return temp;
	}
	
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	
	public double getPressure() {
		return pressure;
	}
	
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	
	public double getHumidity() {
		return humidity;
	}
	
	public static void resetCount() {
		count = 1;
	}
	
	public static Environment findEnviro(String id, Environment[] enviros) {
		Environment result = null;
		try {
			for(int i = 0; i< enviros.length; i++) {
				if(enviros[i].enviro_ID == Integer.parseInt(id)) {
					result = enviros[i];
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
//			result = enviros[0];
		}
		
		return result;
	}
	
	public boolean validEnvironment(Content content) {
		Environment required = content.getEnvironment();
		double threshold = content.getThreshold();
		
		return meetsAllRequirements(required, threshold);
	}

	private boolean meetsAllRequirements(Environment required, double threshold) {
		return inThreshold(this.temp,required.temp,threshold)&&
				inThreshold(this.humidity,required.humidity,threshold)&&
				inThreshold(this.pressure,required.pressure,threshold);
	}
	
	private boolean inThreshold(double current, double required, double thresh) {
		double Thresh = required*thresh;
		return (current >= required - Thresh) && (current <= required + Thresh);
	}
	
	public NotifyObject checkEnvironment(Content content) {
		NotifyObject notification;
		if (!validEnvironment(content)) {
			notification = new NotifyObject(200, "Client is notified of invalid environment");
		} else {
			notification = new NotifyObject(0, "Container environment is valid");
		} return notification;
	}

	@Override
	public int findFromID(int ID, Object[] enviros) { 
		int index = -1;
		try {
			for(int i = 0; i < enviros.length; i++) {
				if(ID == ((Environment)enviros[i]).enviro_ID) {
					index = i;
					break;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return index;
	}

	@Override
	public int findFromString(String ID, Object[] enviros) {
		int index = -1;
		try {
			for(int i = 0; i < enviros.length; i++) {
				if(Integer.parseInt(ID) == ((Environment)enviros[i]).enviro_ID) {
					index = i;
					break;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return index;
	}

	@Override
	public int findFromStrings(String firstString, String secondString, Object[] enviros) { 
		String str = (firstString.isEmpty() ? secondString : firstString);
		return findFromString(str, enviros);
	}
	
    @Override
    public String toString() {
    	return "'"+ enviro_ID + "', '" + temp + "', '" + pressure + "', '" + 
    			humidity + "'";
    }
}
