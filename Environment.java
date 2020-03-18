
public class Environment {
	private int temp;
	private int pressure;
	private int humidity;
	
	public Environment(int temp, int pressure, int humidity) {
		this.temp = temp;
		this.pressure = pressure;
		this.humidity = humidity;
	}
	
	public void setTemp(int temp) {
		this.temp = temp;
	}
	
	public int getTemp() {
		return temp;
	}
	
	public void setPressure(int pressure) {
		this.pressure = pressure;
	}
	
	public int getPressure() {
		return pressure;
	}
	
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	
	public int getHumidity() {
		return humidity;
	}
	
	public boolean validEnvironment(Content content) {
		boolean valid = true;
		Environment required = content.getEnvironment();
		float threshold = content.getThreshold();
		
		float tempThresh = threshold*required.temp;
		float humidThresh = threshold*required.humidity;
		float pressThresh = threshold*required.pressure;
		
		if(this.temp < required.temp-tempThresh || this.temp > required.temp+tempThresh) {
			valid = false;
		}
		else if(this.humidity < required.humidity-humidThresh || this.humidity > required.humidity+humidThresh) {
			valid = false;
		}
		else if(this.pressure < required.pressure-pressThresh || this.pressure > required.pressure+pressThresh) {
			valid = false;
		}

		return valid;
	}
}
