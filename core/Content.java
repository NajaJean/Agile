package core;


public class Content {
	private String name;
	private Environment enviro;
	private double threshold;
	
	public String getName() {
    	return name;
    }
	public Environment getEnvironment() {
    	return enviro;
    }
	public double getThreshold() {
		return threshold;
	}
	
	public Content(String name, Environment enviro, double threshold) {
		this.name = name;
		this.enviro = enviro;
		this.threshold = threshold;
	}
}
