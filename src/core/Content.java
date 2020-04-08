package core;


public class Content {
	private String name;
	private Environment enviro;
	private double threshold;
	private int content_ID;
    private static int count = 0;
	
	public String getName() {
    	return name;
    }
	public Environment getEnvironment() {
    	return enviro;
    }
	public double getThreshold() {
		return threshold;
	}
	public int getContentID() {
		return content_ID;
	}
	
	public Content(String name, Environment enviro, double threshold) {
		this.name = name;
		this.enviro = enviro;
		this.threshold = threshold;
		this.content_ID = count++;
	}
	public static Content findContent(int id, Content[] contents) {
		Content result = null;
		for(int i = 0; i< contents.length; i++) {
			if(contents[i].content_ID == id) {
				result = contents[i];
				break;
			}
		}
		return result;
	}
}
