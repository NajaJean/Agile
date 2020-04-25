package controllers;

import UI.LogisticCompanyMenu;
import UI.RegisterContainerUI;
import UI.StartLoginPage;
import core.Container;
import core.DatabaseData;
import core.Environment;
import core.Location;

public class RegisterContainerController {
	private RegisterContainerUI view;
	Location location;
	Container container;
	String loc;
	
	Location[] Locations;
	
	public RegisterContainerController(String loc) {
		view = new RegisterContainerUI(loc);
		this.loc = loc;
		this.Locations = DatabaseData.getLocations();
	}
	
	public void initController() {
		view.getRegContainerButton().addActionListener(e -> tryRegister(loc));
		view.getGoBackButton().addActionListener(e -> goToLogisticCompanyMenu());
	}
	
	public void tryRegister(String loc) {
		String loca = view.getLocationContainerTextField().getText();
		String temp = view.getTempTextField().getText();
		String pres = view.getPresTextField().getText();
		String hum = view.getHumTextField().getText();
	}
	
	private void goToLogisticCompanyMenu() {
		view.dispose();
		LogisticCompanyMenuController w = new LogisticCompanyMenuController();
		w.initController();
	}
	

}
