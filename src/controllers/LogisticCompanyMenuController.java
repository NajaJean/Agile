package controllers;

import UI.AutoAdd;
import UI.LogisticCompanyMenu;
import UI.StartLoginPage;
import core.Calendar;

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
		view.getAutoMenuItem().addActionListener(e -> goToAutoMenu());
		view.getClientInfoItem().addActionListener(e -> goToClientInfoMenu());
		view.getContainerInfoItem().addActionListener(e -> goToContainerInfoMenu());
		view.getTomorrow().addActionListener(e -> goTOmorrow());
		view.getNextWeek().addActionListener(e -> goNextWeek());
	}
	
	private void goTOmorrow() {
		view.dispose();
		Calendar c = new Calendar();
		c.goTomorrow();
		LogisticCompanyMenuController w = new LogisticCompanyMenuController();
		w.initController();
	}
	
	private void goNextWeek() {
		view.dispose();
		Calendar c = new Calendar();
		c.goIntoTheFutureXDays(7);
		LogisticCompanyMenuController w = new LogisticCompanyMenuController();
		w.initController();
	}
	
	private void goToClientInfoMenu() {
		view.dispose();
		LogisticInfoPageController w = new LogisticInfoPageController("Client");
		w.initController();
	}
	private void goToContainerInfoMenu() {
		view.dispose();
		LogisticInfoPageController w = new LogisticInfoPageController("Container");
		w.initController();
	}
	
	private void goToAutoMenu() {
		view.dispose();
		AutoAddController w = new AutoAddController();
		w.initController();
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
