package controllers;

import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import ExternalData.DatabaseData;
import UI.RegisterContainerUI;
import core.ArraySearch;
import core.Container;
import core.Environment;
import core.Location;

public class RegisterContainerController {
	private RegisterContainerUI view;
	Location chosenLocation;
	Container container;
	String loc;
	Environment Enviro;
	String selected;
	Location[] Locations;
	
	ArraySearch search;
	
	public RegisterContainerController() {
		view = new RegisterContainerUI();
		this.Locations = DatabaseData.getLocations();
		this.search = new ArraySearch(new Location());
	}
	
	public void initController() {
		view.getRegContainerButton().addActionListener(e -> tryRegister());
		view.getGoBackButton().addActionListener(e -> goToLogisticCompanyMenu());
		view.getLocationContainerJComboBox().addActionListener(e -> locationSelected(e));
		view.getLoadClimateButton().addActionListener(e -> tryClimate());
		}
	
	public void locationSelected(ActionEvent e) {
		JComboBox box = (JComboBox) e.getSource();
		selected = (String) box.getSelectedItem();
		
		int locationIDX = search.findIDX(selected, Locations);
		if (locationIDX == -1) {
			JOptionPane.showMessageDialog(null, "Error finding location");
			return;
		}
		chosenLocation = Locations[locationIDX];
		
		view.iString();
	}
	
	public void tryClimate() {
		double enviroTemp = chosenLocation.getEnvironment().getTemp();
		double enviroPres = chosenLocation.getEnvironment().getPressure();
		double enviroHum = chosenLocation.getEnvironment().getHumidity();
		view.changeString(selected, enviroTemp, enviroPres, enviroHum);
	}
	
	public void tryRegister() {
		Container C = new Container(chosenLocation);
		DatabaseData.getDatabase().addToDatabase(C);
		view.registerString(selected);
	}
	
	private void goToLogisticCompanyMenu() {
		view.dispose();
		LogisticCompanyMenuController w = new LogisticCompanyMenuController();
		w.initController();
	}
}
