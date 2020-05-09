package UI;


import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.swing.*;

import core.*;

public class Map extends JFrame{
	  
	java.awt.Container map = getContentPane();
	java.awt.Container container = getContentPane();
	
	String planeright = "src//planeright.png";
	String planeleft = "src//planeleft.png";
	String ship = "src//ship.png";
	String containerSmall = "src//containersmall.png";
	String wMap = "src//map.jpg";
	
    JLabel worldmap = new JLabel (new ImageIcon(wMap));
    
    JLabel[] shippingC;
    ContainerJourney[] cJs;
    Client client;
    
    boolean isGoingRight = true;    
 
    public Map(Client client, ContainerJourney[] containerJourneys) {
    	
    	this.client = client;
    	cJs = client.getClientsCJs(containerJourneys);
    	
    	JLabel[] temp = new JLabel[cJs.length];
    	for (int i = 0; i < cJs.length; i++) {
    		temp[i] = new JLabel(new ImageIcon(containerSmall));
    		temp[i].setToolTipText("<html>" +
    				"Container ID: " + String.valueOf(cJs[i].getContaineronJourney().getContainerID()) + "<br>" +
    				"Container Coordinates: " + String.valueOf(cJs[i].getCurrentX()) + ", " + String.valueOf(cJs[i].getCurrentY()) + "<br>" +
    				"Content: " + String.valueOf(cJs[i].getContaineronJourney().getContainerContent().getName()) + "<br>" +
    				"Container Temperature: " + String.valueOf(cJs[i].getContaineronJourney().getContainerEnvironment().getTemp()) + "<br>" +
    				"Container Pressure: " + String.valueOf(cJs[i].getContaineronJourney().getContainerEnvironment().getPressure()) + "<br>" +
    				"Container Humidity: " + String.valueOf(cJs[i].getContaineronJourney().getContainerEnvironment().getHumidity()) + "<br>" +
    				"</html>");}
    	shippingC = temp;
    	worldmap.setBounds (0, 0, 1800, 900);
    	
    	for (int i = 0; i < shippingC.length; i++) {
    		setContainer(i);
    	}
    	
        addComponentsToContainer();
        addComponentsToMap();
    }
    
    public JLabel getMapBackground() {
    	worldmap.setBounds (0, 0, 1800, 900);
    	return worldmap;
    }
    
    public JLabel[] getContainerLabels() {
    	return shippingC;
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
		return 0 < (cJs[index].getEndLocX()-cJs[index].getStartLocX());
	}
    
    public void setContainer(int i) {
    	shippingC[i].setBounds ((int)cJs[i].getCurrentX(), (int)cJs[i].getCurrentY(), 50, 38);        
    }
    
    public void showAllContainers(){
    	for (int i = 0; i < shippingC.length; i++) {
    		resetTooltips(i);
        	setContainer(i);}
    } 
    
    public void resetTooltips(int i) {
		shippingC[i].setToolTipText("<html>" +
				"Container ID: " + String.valueOf(cJs[i].getContaineronJourney().getContainerID()) + "<br>" +
				"Container Coordinates: " + String.valueOf(cJs[i].getCurrentX()) + ", " + String.valueOf(cJs[i].getCurrentY()) + "<br>" +
				"Content: " + String.valueOf(cJs[i].getContaineronJourney().getContainerContent().getName()) + "<br>" +
				"Container Temperature: " + String.valueOf(cJs[i].getContaineronJourney().getContainerEnvironment().getTemp()) + "<br>" +
				"Container Pressure: " + String.valueOf(cJs[i].getContaineronJourney().getContainerEnvironment().getPressure()) + "<br>" +
				"Container Humidity: " + String.valueOf(cJs[i].getContaineronJourney().getContainerEnvironment().getHumidity()) + "<br>" +
				"</html>");
	}
}
 
