package controllers;

import UI.LogisticCompanyMenu;
import UI.StartLoginPage;

public class LogisticCompanyMenuController {
	LogisticCompanyMenu view;
	
	public LogisticCompanyMenuController() {
		view = new LogisticCompanyMenu();
	}
	
	public void initController() {
		view.getUpdateContainerStatusItem().addActionListener(e -> openUpdateContainerStatusMenu());
		view.getRegisterContainerItem().addActionListener(e -> openRegisterNewContainerMenu());
		view.getCreateNewClientItem().addActionListener(e -> goToCreateClientMenu());
		view.getSignOutItem().addActionListener(e -> signOut());
	}
	
	private void signOut() {
		view.dispose();
		StartLoginPage view = new StartLoginPage();
		StartLoginPageController w = new StartLoginPageController(view);
		w.initController();
	}
	
	private void openUpdateContainerStatusMenu() {
		view.dispose();
		LogisticUpdateController w = new LogisticUpdateController();
		w.initController();
		
	}
	
	private void openRegisterNewContainerMenu() {
		view.dispose();
		RegisterContainerController w = new RegisterContainerController();
		w.initController();
	}
	
	private void goToCreateClientMenu() {
		view.dispose();
		LogisticCreateClientController w = new LogisticCreateClientController();
		w.initController();
	}
}
