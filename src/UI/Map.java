package UI;


import javax.swing.*;

import core.*;

public class Map extends JFrame{
	  
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
    
 
    public Map(ContainerJourney[] containerJourneys) {
    	
    	containerJs = containerJourneys;
    	JLabel[] temp = new JLabel[containerJs.length];
    	for (int i = 0; i < containerJs.length; i++) {
    		temp[i] = new JLabel(new ImageIcon(containerSmall));
    		temp[i].setToolTipText("<html>" +
    				"Container ID: " + String.valueOf(containerJs[i].getContaineronJourney().getContainerID()) + "<br>" +
    				"Container Coordinates: " + String.valueOf(containerJs[i].getCurrentX()) + ", " + String.valueOf(containerJs[i].getCurrentY()) + "<br>" +
    				"Content: " + String.valueOf(containerJs[i].getContaineronJourney().getContainerContent().getName()) + "<br>" +
    				"Container Temperature: " + String.valueOf(containerJs[i].getContaineronJourney().getContainerEnvironment().getTemp()) + "<br>" +
    				"Container Pressure: " + String.valueOf(containerJs[i].getContaineronJourney().getContainerEnvironment().getPressure()) + "<br>" +
    				"Container Humidity: " + String.valueOf(containerJs[i].getContaineronJourney().getContainerEnvironment().getHumidity()) + "<br>" +
    				"</html>");}
    	shippingC = temp;
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
    
    public void showAllContainers(){
    	for (int i = 0; i < shippingC.length; i++) {
    		resetTooltips(i);
        	setContainer(i);}
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
	
    
   public static void main(String[] args){
	   
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
   	
   	Client testClient = new Client("asd", "yoDaddy69", "1", "2", "3");
   	
   	Content stuff = new Content("Stuff", testEnv, 1.0);
   	
   	Container shippingContainer1 = new Container(testClient, testEnv, stuff, CPH);
   	Container shippingContainer2 = new Container(testClient, testEnv, stuff, Hawaii);
   	Container shippingContainer3 = new Container(testClient, testEnv, stuff, Rio);
   	
   	//we want this
   	ContainerJourney[] containerJs = {new ContainerJourney(CPH, NY, shippingContainer1),
   									  new ContainerJourney(Hawaii, Tokyo, shippingContainer2),
   									  new ContainerJourney(Rio, Sydney, shippingContainer3)
   									  };
   	
	   
	Map frame = new Map(containerJs);
	frame.showAllContainers();
	
   } 
}
 
