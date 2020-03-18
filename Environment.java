
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
		boolean valid = true;
		Environment required = content.getEnvironment();
		double threshold = content.getThreshold();
		
		if(outsideThreshold(this.temp,required.temp,threshold)||
				outsideThreshold(this.humidity,required.humidity,threshold)||
				outsideThreshold(this.pressure,required.pressure,threshold)) {
			valid = false;
		}
		
		return valid;
	}
	
	private boolean outsideThreshold(double current, double required, double thresh) {
		double Thresh = required*thresh;
		if(current < required - Thresh || current > required + Thresh) {
			return true;
		}
		else {
			return false;
		}
	}
}
