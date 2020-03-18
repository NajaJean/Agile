
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
		Environment required = content.getContainerEnvironment();
		float threshold = content.getThreshold();
		
		if(outsideThreshold(this.temp,required.temp,threshold)||
				outsideThreshold(this.humidity,required.humidity,threshold)||
				outsideThreshold(this.pressure,required.pressure,threshold)) {
			valid = false;
		}
		
		return valid;
	}
	
	private boolean outsideThreshold(int current, int required, float thresh) {
		float Thresh = required*thresh;
		if(current < required - Thresh || current > required + Thresh) {
			return true;
		}
		else {
			return false;
		}
	}
}
