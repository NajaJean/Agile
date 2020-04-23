package controllers;

import UI.ClientMenu;
import UI.StartLoginPage;
import core.Client;

public class ClientMenuController {
	private ClientMenu view; 
	
	ClientMenuController(Client client) {
		view = new ClientMenu();
	}
	
	public void initController() {
		view.getBook_containerItem().addActionListener(e -> goToBookContainerMenu());
		view.getSign_outItem().addActionListener(e -> signOut());
		view.getConfigure_client_detailsItem().addActionListener(e -> goToConfigureClientMenu());
	}
	
	private void goToBookContainerMenu() {
		
	}
	
	private void signOut() {
		view.dispose();
		StartLoginPage view = new StartLoginPage();
		StartLoginPageController w = new StartLoginPageController(view);
		w.initController();
	}
	
	private void goToConfigureClientMenu() {
		
	}
}
