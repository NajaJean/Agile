package core;


public class Environment {
	private int enviro_ID;
	private double temp;
	private double pressure;
	private double humidity;
	private static int count = 0;
	
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
	
	public static Environment findEnviro(String id, Environment[] enviros) {
		Environment result = null;
		for(int i = 0; i< enviros.length; i++) {
			if(enviros[i].enviro_ID == Integer.parseInt(id)) {
				result = enviros[i];
				break;
			}
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
}
