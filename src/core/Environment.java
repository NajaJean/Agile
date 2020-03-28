package core;


public class Environment {
	private double temp;
	private double pressure;
	private double humidity;
	
	public Environment(double temp, double pressure, double humidity) {
		this.temp = temp;
		this.pressure = pressure;
		this.humidity = humidity;
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
			return notification;
		} else {
			notification = new NotifyObject(0, "");
			return notification;
		}
	}
}
