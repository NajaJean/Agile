package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import ExternalData.DatabaseData;
import UI.LogisticUpdate;
import core.ArraySearch;
import core.Calendar;
import core.Container;
import core.ContainerJourney;

public class LogisticUpdateController {
	Container [] Containers;
	ContainerJourney[] cJs;
	LogisticUpdate view; 
	String selectedContainer = "";
	
	Container chosenContainer;
	
	ArraySearch search;
	
	public LogisticUpdateController() {
		Containers = DatabaseData.getContainers();//possibly never used, check this else delete
		cJs = DatabaseData.getJournies();
		// NEED a method to only show the current containers on a journey (where the current x and y are not at the end location)
		this.view = new LogisticUpdate(cJs);
		this.search = new ArraySearch();
	}
	
	public void initController() {
		view.getcancelButton().addActionListener(e -> cancel());
		view.getsaveButton().addActionListener(e -> updateContainer());	
		JCheckBox checkBox = view.getarrivedCheckBox();
		JComboBox containerBox = view.getcontainerBox();
		
		containerBox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if (!selectedContainer.contentEquals("")) {
		    		view.getgpsLatitudeField().setText("");
	    			view.getgpsLongitudeField().setText("");
					
					view.getgpsLatitudeField().setEnabled(true);
					view.getgpsLongitudeField().setEnabled(true);
					checkBox.setSelected(false);
				}
		    	selectedContainer = containerBox.getSelectedItem().toString();
		    	
		    	search.setSearch(new Container());
		    	int containerIDX = search.findIDX(Integer.parseInt(selectedContainer), Containers);
		    	if (containerIDX == -1) {
		    		JOptionPane.showMessageDialog(null, "Error updating container");
		    		return;
		    	}
		        chosenContainer = Containers[containerIDX];    
		    }
		});
		
		checkBox.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if (selectedContainer.equals("")) {
		        	containerNotSelected();
		        	checkBox.setSelected(false);
		        }
		        else {
		        	if (selected) {
			        	view.getgpsLatitudeField().setText(Double.toString(chosenContainer.getContainerLocation().getGPScoordX()));
						view.getgpsLongitudeField().setText(Double.toString(chosenContainer.getContainerLocation().getGPScoordY()));
						
						view.getgpsLatitudeField().setEnabled(false);
						view.getgpsLongitudeField().setEnabled(false);
			        }
		        	else {
		        		view.getgpsLatitudeField().setText("");
		    			view.getgpsLongitudeField().setText("");
						
						view.getgpsLatitudeField().setEnabled(true);
						view.getgpsLongitudeField().setEnabled(true);
		        	}
		        }
		      }
		    });
	}
	
	public void cancel() {
		LogisticCompanyMenuController lm = new LogisticCompanyMenuController();
		view.dispose();
		lm.initController();
		
	}
	
	public void updateContainer() {
		
		try {view.getgpsLatitudeField().setEnabled(true);
			 view.getgpsLongitudeField().setEnabled(true);
			 
			 String gpsLatitudeText = view.getgpsLatitudeField().getText();
			 String gpsLongitudeText = view.getgpsLongitudeField().getText();
			 
			 double gpsLatitude = Double.parseDouble(gpsLatitudeText);
			 double gpsLongitude = Double.parseDouble(gpsLongitudeText);
			 
			 search.setSearch(new ContainerJourney());
			 int cjIDX = search.findIDX(String.valueOf(chosenContainer.getContainerID()), cJs); // CJ found from containerID
			 if (cjIDX == -1) {
				 JOptionPane.showMessageDialog(null, "Error updating container");
				 return;
			 }
			 ContainerJourney containerJourney = cJs[cjIDX];
			 containerJourney.setCurrentLocation(new double [] {gpsLatitude, gpsLongitude});
			 
			 DatabaseData.getDatabase().updateDatabase("Journies", "Current_x", Double.toString(gpsLatitude), Integer.toString(containerJourney.getJourneyID()));
			 DatabaseData.getDatabase().updateDatabase("Journies", "Current_y", Double.toString(gpsLongitude), Integer.toString(containerJourney.getJourneyID()));			
			 DatabaseData.getDatabase().updateDatabase("Journies", "EndDate", Calendar.getSystemDate().toString(), Integer.toString(containerJourney.getJourneyID()));			
			 
			 //If container has arrived
			 //Set the container client ID and content ID to null (make it free for a new journey) and update its current location
			 DatabaseData.getDatabase().updateDatabase("Containers", "Client_ID",Integer.toString(containerJourney.getContaineronJourney().getContainerID()));
			 DatabaseData.getDatabase().updateDatabase("Containers", "Content_ID",Integer.toString(containerJourney.getContaineronJourney().getContainerID()));
			 DatabaseData.getDatabase().updateDatabase("Containers", "Location_ID",Integer.toString(containerJourney.getEndLocation().getLocationID()),Integer.toString(containerJourney.getContaineronJourney().getContainerID()));
			 
			 updateSuccessful();
			
			 LogisticCompanyMenuController lm = new LogisticCompanyMenuController();
			 view.dispose();
			 lm.initController();	 
		}
		catch (Exception e) {
			if (selectedContainer.equals("")) {
				containerNotSelected();
			}
			else {
				GPSisWrong();
			}
		}			
	}
	
	public void containerNotSelected() {
		JOptionPane.showMessageDialog(null, "Please select a container to update");
	}
	public void GPSisWrong() {
		JOptionPane.showMessageDialog(null, "Error: GPS Latitude and GPS Longitude must "
										+ "\nbe given as decimal numbers.");
	}
	public void updateSuccessful() {
		JOptionPane.showMessageDialog(null, "Container successfully updated!");
	}
	
}
