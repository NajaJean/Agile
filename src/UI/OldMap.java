
package UI;


import javax.swing.*;

import core.*;

class MapFrame extends JFrame{
	  
    //construct components
	
	java.awt.Container map = getContentPane();
	java.awt.Container container = getContentPane();
	
	String planeright = "src\\planeright.png";
	String planeleft = "src\\planeleft.png";
	String ship = "src\\ship.png";
	String containerSmall = "src\\containersmall.png";
	String wMap = "src\\map.jpg";
	
    JLabel worldmap = new JLabel (new ImageIcon(wMap));
    
    JLabel[] shippingC;
    ContainerJourney[] containerJs;
    
    boolean isGoingRight = true;
    
    //Climate climate = new Climate();
    
 
    MapFrame(ContainerJourney[] containerJourneys) {
    	
    	containerJs = containerJourneys;
    	for (int i = 0; i < containerJs.length; i++) {
    		shippingC[i] = new JLabel(new ImageIcon(containerSmall));
    		shippingC[i].setToolTipText("<html>" +
    				"Container ID: " + String.valueOf(containerJs[i].getContaineronJourney().getContainerID()) + "<br>" +
    				"Container Coordinates: " + String.valueOf(containerJs[i].getCurrentX()) + ", " + String.valueOf(containerJs[i].getCurrentY()) + "<br>" +
    				"Content: " + String.valueOf(containerJs[i].getContaineronJourney().getContainerContent().getName()) + "<br>" +
    				"Container Temperature: " + String.valueOf(containerJs[i].getContaineronJourney().getContainerEnvironment().getTemp()) + "<br>" +
    				"Container Pressure: " + String.valueOf(containerJs[i].getContaineronJourney().getContainerEnvironment().getPressure()) + "<br>" +
    				"Container Humidity: " + String.valueOf(containerJs[i].getContaineronJourney().getContainerEnvironment().getHumidity()) + "<br>" +
    				"</html>");}
    	worldmap.setBounds (0, 0, 1800, 900);
        
        addComponentsToContainer();
        addComponentsToMap();
        
        setTitle("World Map");
        setVisible(true);
        setBounds(0, 0, 1810, 950);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    public void addComponentsToContainer() {
    	for (int i = 0; i < shippingC.length; i++) {
    		map.add(shippingC[i]);}
    }
    
    public void addComponentsToMap() {
        map.add(worldmap);
    }
    
    
    public void setLabelIcon(int index, String setToThis) {
    	shippingC[index].setIcon(new ImageIcon(setToThis));
    }
    
    public boolean isGoingRight(int index) {
		return 0 < (containerJs[index].getEndLocX()-containerJs[index].getStartLocX());
	}
    
    public void setContainer(int i) {
    	shippingC[i].setBounds ((int)containerJs[i].getCurrentX(), (int)containerJs[i].getCurrentY(), 50, 38);        
    }
    
	private void ifEnrouteChangeIcon(int j, int index, String enrouteIcon, String stationaryIcon) {
		if (isEnroute(j, index)) {
			
			if (isGoingRight(index)) { setLabelIcon(index, planeright);}
			else {setLabelIcon(index, planeleft);}
		}
		else {
				setLabelIcon(index, stationaryIcon);
		}
		
	}

	private boolean isEnroute(int currentStep, int index) {
		return (currentStep > 5) && (Math.abs(containerJs[index].getCurrentX()-containerJs[index].getEndLocX()) > 3);
	}
    
    public void showAllContainers() throws InterruptedException {
    	for (int i = 0; i < shippingC.length; i++) {
    		resetTooltips(i);
        	setContainer(i);}
    	Thread.sleep(5000);
    } 
    
    public void resetTooltips(int i) {
		shippingC[i].setToolTipText("<html>" +
				"Container ID: " + String.valueOf(containerJs[i].getContaineronJourney().getContainerID()) + "<br>" +
				"Container Coordinates: " + String.valueOf(containerJs[i].getCurrentX()) + ", " + String.valueOf(containerJs[i].getCurrentY()) + "<br>" +
				"Content: " + String.valueOf(containerJs[i].getContaineronJourney().getContainerContent().getName()) + "<br>" +
				"Container Temperature: " + String.valueOf(containerJs[i].getContaineronJourney().getContainerEnvironment().getTemp()) + "<br>" +
				"Container Pressure: " + String.valueOf(containerJs[i].getContaineronJourney().getContainerEnvironment().getPressure()) + "<br>" +
				"Container Humidity: " + String.valueOf(containerJs[i].getContaineronJourney().getContainerEnvironment().getHumidity()) + "<br>" +
				"</html>");
	}
	
    
    public void moveAllContainers() throws InterruptedException {
    	for (int i = 0; i < shippingC.length; i++) {
        	setContainer(i);}
    	Thread.sleep(1000);
    	int maxDist = -1;
    	
    	for (int i = 0; i < shippingC.length; i++) {
    		if (maxDist < (int)getMaxDistance(i)) {
    			maxDist = (int)getMaxDistance(i);
    		}
    	}
	    for (int j = 0; j < maxDist; j++) {
	   
	    	ifEnrouteChangeIcon(j, 0, ship, containerSmall);
	    	ifEnrouteChangeIcon(j, 1, "plane", containerSmall);
	    	ifEnrouteChangeIcon(j, 2, "plane", containerSmall);
	    	for (int i = 0; i < shippingC.length; i++) {
	    		
	    		moveContainer(i);
	    		//climate.updateWeatherForOneContainerJ(containerJs[i]);
	    		resetTooltips(i);
	    		setContainer(i);
	    	}
	    	
	    	Thread.sleep(10);
	    }
   	}


	private void moveInOneDirection(int i, int axis) {
		containerJs[i].getCurrentLocation().incrementOneGPSCoord(axis, stepForOneDirection(i,axis));
	}
        
	
	
	
	
    public void moveOneContainers(int index) throws InterruptedException {
    	setContainer(index);
    	Thread.sleep(1000);
    	int maxDist = (int)getMaxDistance(index);
    	
	    for (int j = 0; j < maxDist; j++) {
	    	ifEnrouteChangeIcon(j, index, "plane", containerSmall);

    		moveContainer(index);
    		//climate.updateWeatherForOneContainerJ(containerJs[index]);
    		resetTooltips(index);
    		setContainer(index);
    		
	    	Thread.sleep(10);
	    }
   	}


	private void moveContainer(int index) {
		if (containerJs[index].getCurrentX() != containerJs[index].getEndLocX()) {
			moveInOneDirection(index, 0);
			moveInOneDirection(index, 1);
			}
	}
    
    public void showOneContainer(int index) throws InterruptedException {
    	//climate.updateWeatherForOneContainerJ(containerJs[index]);
    	resetTooltips(index);
    	setContainer(index);
    	Thread.sleep(5000);
    }
    
        
    
    
    
    private double stepForOneDirection(int index, int axis) {
		return (getDistanceAlongOneAxis(index, axis)) / getMaxDistance(index);
	}

    

	private double getDistanceAlongOneAxis(int i, int axis) {
		return containerJs[i].getEndLocation().getGPScoord()[axis] - containerJs[i].getStartLocation().getGPScoord()[axis];
	}

	private double getMaxDistance(int i) {
		return Math.max(Math.abs(containerJs[i].getEndLocX() - containerJs[i].getStartLocX()), 
				  Math.abs(containerJs[i].getEndLocY() - containerJs[i].getStartLocY()));
	}
 
   
}
 
public class OldMap {
	
	public OldMap(ContainerJourney[] containerJs) {
		MapFrame frame = new MapFrame(containerJs);
	}
	
    public static void main(String[] a) throws InterruptedException {
   
    	//Create test stuff should be given
    	
    	double[] cphgpscoords = {730.0, 128.0};
    	double[] tokyogpscoords = {1423.0, 245.0};
    	double[] sydneygpscoords = {1495.0, 750.0};
    	double[] nygpscoords = {290.0, 225.0};
    	double[] hawaiigpscoords = {1735.0, 265.0};
    	double[] riogpscoords = {420.0, 670.0};
    	

    	Location CPH = new Location("Copenhagen", cphgpscoords);
    	Location Tokyo = new Location("Tokyo", tokyogpscoords);
    	Location Sydney = new Location("Sydney", sydneygpscoords);
    	Location NY = new Location("New York", nygpscoords);
    	Location Hawaii = new Location("Hawaii", hawaiigpscoords);
    	Location Rio = new Location("Rio", riogpscoords);
    	
    	Environment testEnv = new Environment(5.0, 5.0, 5.0);
    	
    	Client testClient = new Client("asd", "yoDaddy69");
    	
    	Content stuff = new Content("Stuff", testEnv, 1.0);
    	
    	Container shippingContainer1 = new Container(testClient, testEnv, stuff);
    	Container shippingContainer2 = new Container(testClient, testEnv, stuff);
    	Container shippingContainer3 = new Container(testClient, testEnv, stuff);
    	
    	//we want this
    	ContainerJourney[] containerJs = {new ContainerJourney(CPH, NY, shippingContainer1),
    									  new ContainerJourney(Hawaii, Tokyo, shippingContainer2),
    									  new ContainerJourney(Rio, Sydney, shippingContainer3)
    									  };
    	
    	MapFrame frame = new MapFrame(containerJs);
    	frame.setTitle("World Map");
        frame.setVisible(true);
        frame.setBounds(0, 0, 1810, 950);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.moveAllContainers();
        //frame.moveOneContainers(2);
        frame.containerJs[2].getCurrentLocation().setGPSCoordX(1735.0);
        frame.containerJs[2].getCurrentLocation().setGPSCoordY(265.0);
        //frame.showOneContainer(2);
        frame.containerJs[1].getCurrentLocation().setGPSCoordX(420.0);
        frame.containerJs[1].getCurrentLocation().setGPSCoordY(670.0);
        
        
        frame.containerJs[0].getCurrentLocation().setGPSCoordX(1000.0);
        frame.containerJs[0].getCurrentLocation().setGPSCoordY(550.0);
        //frame.showAllContainers();
    }
}
