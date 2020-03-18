
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
	
	public boolean validEnvironment(Environment requiredEnviro) {
		boolean valid = false;
		
		//...
		
		return valid;
	}
}
