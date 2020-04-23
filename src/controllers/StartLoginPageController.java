package controllers;

import UI.StartLoginPage;

public class StartLoginPageController {
	private StartLoginPage view;
	
	public StartLoginPageController(StartLoginPage view){
		this.view = view;
	}
	
	public void initController() {
		view.getcLoginButton().addActionListener(e -> openLoginFor("Client"));
		view.getlcLoginButton().addActionListener(e -> openLoginFor("Logistic Company"));
	}
	
	private void openLoginFor(String CorL) {
		LoginController lc;
		if (CorL.equals("Client")) {
			lc = new LoginController("Client");
		}
		else {
			lc = new LoginController("Logistic Company");
		}
		view.dispose();
		lc.initController();
	}
}
