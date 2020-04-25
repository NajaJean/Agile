package controllers;

import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
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
		Locations = DatabaseData.getLocations();
		view = new RegisterContainerUI(Locations);
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
		
/*		String temp = view.getTempTextField().getText();
		String pres = view.getPresTextField().getText();
		String hum = view.getHumTextField().getText(); */
	}
	
	
	private void goToLogisticCompanyMenu() {
		view.dispose();
		LogisticCompanyMenuController w = new LogisticCompanyMenuController();
		w.initController();
	}
	
	

}
