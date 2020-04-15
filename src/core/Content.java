package core;


public class Content {
	private String name;
	private Environment enviro;
	private double threshold;
	private int content_ID;
    private static int count = 1;
	
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
	public static Content findContent(String id, Content[] contents) {
		Content result = null;
		try {
			for(int i = 0; i< contents.length; i++) {
				if(contents[i].content_ID == Integer.parseInt(id)) {
					result = contents[i];
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			result = contents[0];
		}
		assert result != null;

		return result;
	}
}
