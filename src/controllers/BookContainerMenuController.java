package controllers;


import javax.swing.JOptionPane;
import javax.swing.JTextField;

import UI.BookContainerMenu;
import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Content;
import core.DatabaseData;
import core.Environment;
import core.Location;
import core.NotifyObject;

public class BookContainerMenuController {
	Client client;
	Container container;
	BookContainerMenu view;
	
	public BookContainerMenuController(Client client, Container container) {
		this.client = client;
		this.container = container;
		this.view = new BookContainerMenu();
	}
	
	public void initController() {
		view.getbookButton().addActionListener(e -> bookContainer());
		view.getcancelButton().addActionListener(e -> cancel());
	}
	
	private void bookContainer() {
		String contentName = view.getcontentNameTextField().getText();
		String locStartName = view.getlocStartNameTextField().getText();
		String locEndName = view.getlocEndNameTextField().getText();
		
		String reqTempText = view.getreqTempTextField().getText(); 
		String pressureText = view.getpressureTextField().getText();
		String humidityText = view.gethumidityTextField().getText();
		String thresholdText = view.getthresholdTextField().getText();
		String gpsXStartText = view.getgpsXStartTextField().getText();
		String gpsYStartText = view.getgpsYStartTextField().getText();
		String gpsXEndText = view.getgpsXEndTextField().getText();
		String gpsYEndText = view.getgpsYEndTextField().getText();
		try {
			  double reqTemp = Double.parseDouble(reqTempText);
			  double pressure = Double.parseDouble(pressureText);
			  double humidity = Double.parseDouble(humidityText);
			  double threshold = Double.parseDouble(thresholdText);
			  double gpsXStart = Double.parseDouble(gpsXStartText);
			  double gpsYStart = Double.parseDouble(gpsYStartText);
			  double gpsXEnd = Double.parseDouble(gpsXEndText);
			  double gpsYEnd = Double.parseDouble(gpsYEndText);
			  
			  Environment reqEnviro = new Environment(reqTemp,pressure,humidity);
			  Content content = new Content(contentName,reqEnviro,threshold);
			  
			  Location locStart = new Location(locStartName, new double[]{gpsXStart, gpsYStart});
			  Location locEnd = new Location(locEndName, new double[]{gpsXEnd, gpsYEnd});
			  
			  container.setContainerContent(content);
			  
			  //Set client of container in database
			  DatabaseData.getDatabase().updateDatabase("Containers", "Client_ID",Integer.toString(client.getID()), Integer.toString(container.getContainerID()));
			  //Update container in database (add content)
			  DatabaseData.getDatabase().updateDatabase("Containers", "Content_ID", Integer.toString(content.getContentID()), Integer.toString(container.getContainerID()));
			  
			  //Configure container in container array
			  DatabaseData.updateContainer(container);
			 
			  ContainerJourney cj = new ContainerJourney(locStart, locEnd, container);
			  
			  //Update database by adding cj
			  //Add cj to containerJourney array
			  DatabaseData.getDatabase().addToDatabase("Journies", cj.toString());
			  DatabaseData.addContainerJourney(cj);
			  
			  NotifyObject response = new NotifyObject(31, "Container is succesfully booked");
			  JOptionPane.showMessageDialog(null, response.getNotifyMessage());
			  
			}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error: temperature, pressure, humidity, "
											  + "     \nthreshold and GPS coordinates must"
											  + "     \nbe given as decimal numbers.");
		}
	}
	
	private void cancel() {
		ClientMenuController cm = new ClientMenuController(client);
		view.dispose();
		cm.initController();
	}

}
