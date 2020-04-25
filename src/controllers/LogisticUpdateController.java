package controllers;

import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


import UI.LogisticUpdate;
import UI.StartLoginPage;
import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Database;
import core.DatabaseData;
import core.Location;


public class LogisticUpdateController {
	Container [] Containers;
	ContainerJourney[] cJs;
	LogisticUpdate view; 
	
	Container chosenContainer;
	public LogisticUpdateController() {
		Containers = DatabaseData.getContainers();
		cJs = DatabaseData.getJournies();
		this.view = new LogisticUpdate(Containers);
	}
	
	public void initController() {
		view.getcancelButton().addActionListener(e -> cancel());
		view.getsaveButton().addActionListener(e -> updateContainer());	
		view.getcontainerBox().addActionListener(e -> containerSelect(e));
		view.getarrivedCheckBox().addActionListener(e -> arrival(e));
		
		
	}
	
	
	// Arrived check box
	public void arrival(ActionEvent e) {
		JCheckBox checkBox = (JCheckBox) e.getSource();
		
		if (checkBox.isSelected()) {
			view.getgpsLatitudeField().setText(Double.toString(chosenContainer.getContainerLocation().getGPScoordX()));
			view.getgpsLongitudeField().setText(Double.toString(chosenContainer.getContainerLocation().getGPScoordY()));
			
			view.getgpsLatitudeField().setEnabled(false);
			view.getgpsLongitudeField().setEnabled(false);
		}
		
		
		
	}
	
	
	// Container select
	public void containerSelect(ActionEvent e) {
		JComboBox containerBox = (JComboBox) e.getSource();
        String selected = containerBox.getSelectedItem().toString();
        chosenContainer = Container.findContainer(Integer.parseInt(selected) , Containers);
        
        
		
	}
	
	public void cancel() {
		LogisticCompanyMenuController lm = new LogisticCompanyMenuController();
		view.dispose();
		lm.initController();
		
	}
	
	public void updateContainer() {
		String gpsLatitudeText = view.getgpsLatitudeField().getText();
		String gpsLongitudeText = view.getgpsLongitudeField().getText();
		double gpsLatitude;
		double gpsLongitude;
		
		try {
			 gpsLatitude = Double.parseDouble(gpsLatitudeText);
			 gpsLongitude = Double.parseDouble(gpsLongitudeText);
			 
			 if (!(gpsLatitudeText.equals("")|gpsLongitudeText.equals(""))) {
					ContainerJourney containerJourney = ContainerJourney.findJourneyFromContainerID(Integer.toString(chosenContainer.getContainerID()), cJs );
					containerJourney.setCurrentLocation(new double [] {gpsLatitude, gpsLongitude});
					DatabaseData.getDatabase(); // method coming
								

					
					//Don't think that this is how we add to database anymore.... Idk...
					//d.addToDatabase("Clients", c.toString());
					JOptionPane.showMessageDialog(null, "Container successfully updated!");
					
					LogisticCompanyMenuController lm = new LogisticCompanyMenuController();
					view.dispose();
					
					lm.initController();
				}
				else {
					JOptionPane.showMessageDialog(null, "All fields have to be filled.");
				}
			 
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: GPS Latitude and GPS Longitude "
											  + "\nbe given as decimal numbers.");
		}
		
		
		
				
	}
	
	
	

	
	
	
	
	

}
