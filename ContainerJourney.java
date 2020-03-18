
public class ContainerJourney {
	private int journeyID;
	private Location start;
	private Location end;
	private Container container;
	
	private static int count = 0;
	
	public ContainerJourney(Location start, Location end, Container container) {
		this.journeyID = count++;
		this.start = start;
		this.end = end;
		this.container = container;
	}
	
	public int getJourneyID() {
		return journeyID;
	}
}
