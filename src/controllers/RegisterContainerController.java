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
		}
	
	public void locationSelected(ActionEvent e) {
		JComboBox box = (JComboBox) e.getSource();
		selected = (String) box.getSelectedItem();
		chosenLocation = Location.findLocation(selected, Locations);
	}
	
	public void tryRegister() {
		double enviroTemp = chosenLocation.getEnvironment().getTemp();
		double enviroPres = chosenLocation.getEnvironment().getPressure();
		double enviroHum = chosenLocation.getEnvironment().getHumidity();
		view.changeString(selected, enviroTemp, enviroPres, enviroHum);
		
		/*Mathilde: This is how I would update the database with the new registered container
		 * 			Although it doesn't seem to work. The error might come from C.toString
		 *          Since we are here dealing with a container will NULL content and NULL client
		 */
		/*
		Container C = new Container(chosenLocation.getEnvironment(),chosenLocation);
		DatabaseData.getDatabase().addToDatabase("Containers", C.toString());
		DatabaseData.addContainer(C);
		
		JOptionPane.showMessageDialog(null, "Container successfully registered.\n"+
											"The current weather in " + chosenLocation.getLocationName() + " is:\n"+
											"Temperature: "+enviroTemp+" °C\n"+
											"Pressure: "+enviroPres+" atm\n"+
											"Humidity: "+enviroHum+" mg/L");
		view.dispose();
		LogisticCompanyMenuController w = new LogisticCompanyMenuController();
		w.initController();*/
		
	}
	
	private void goToLogisticCompanyMenu() {
		view.dispose();
		LogisticCompanyMenuController w = new LogisticCompanyMenuController();
		w.initController();
	}
}
