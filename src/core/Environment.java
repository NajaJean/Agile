package core;


public class Environment extends Search {
	private double temp;
	private double pressure;
	private double humidity;
	private static int count = 1;
	
	public Environment() {}
	
	public Environment(double temp, double pressure, double humidity) {
		this.temp = temp;
		this.pressure = pressure;
		this.humidity = humidity;
		this.ID = count++;
	}
	
	public int getEnviro_ID() {
		return ID;
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
	public int find(String ID, Object[] enviros) {
		int index = -1;
		try {
			for(int i = 0; i < enviros.length; i++) {
				if(Integer.parseInt(ID) == ((Environment)enviros[i]).ID) {
					index = i;
					break;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return index;
	}
	
    @Override
    public String toString() {
    	return "'"+ ID + "', '" + temp + "', '" + pressure + "', '" + 
    			humidity + "'";
    }
}
