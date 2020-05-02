package controllers;

import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import UI.RegisterContainerUI;
import core.Container;
import core.DatabaseData;
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
	
	public RegisterContainerController() {
		view = new RegisterContainerUI();
		this.Locations = DatabaseData.getLocations();
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
		chosenLocation = Location.findLocation(selected, Locations);
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
		DatabaseData.getDatabase().addToDatabase("Containers", C.toString());
		view.registerString(selected);
	
	}
	
	private void goToLogisticCompanyMenu() {
		view.dispose();
		LogisticCompanyMenuController w = new LogisticCompanyMenuController();
		w.initController();
	}
}
