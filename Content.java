
public class Content extends Container {
	private String name;
	private Environment enviro;
	private float threshold;
	
	public String getName() {
    	return name;
    }
	public Environment getEnvironment() {
    	return enviro;
    }
	public float getThreshold() {
		return threshold;
	}
	
	public Content(String name, Environment enviro, float threshold) {
		this.name = name;
		this.enviro = enviro;
		this.threshold = threshold;
	}
	
}
