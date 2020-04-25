package controllers;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import UI.RegisterContainerUI;
import core.Container;
import core.DatabaseData;
import core.Location;

public class RegisterContainerController {
	private RegisterContainerUI view;
	Location chosenLocation;
	Container container;
	String loc;
	
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
		String selected = (String) box.getSelectedItem();
		chosenLocation = Location.findLocation(selected, Locations);
	}
	public void tryRegister() {
		
        // Print the selected items and the action command.

        
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
