package controllers;

import UI.Login;
import UI.StartLoginPage;
import core.Client;
import core.Database;

public class LoginController extends StartLoginPageController{
	private Login view; 
	private StartLoginPage viewBack;
	Client c;
	
	public LoginController(String CorL) {
		view = new Login(CorL);
	}
	
	public void initController() {
		view.getLoginButton().addActionListener(e -> tryLogin());
		view.getResetButton().addActionListener(e -> view.openLoginFor("Logistic Company"));
		view.getGoBackButton().addActionListener(e -> viewBack.openLoginFor("Logistic Company"));
	}
	
	public void tryLogin() {
		String username = view.getUserTextField().getText();
		String password = view.getPasswordTextField().getText();
		
		if (d.checkUser(username, password)) {
			//Fetch client from database via username/psasword.
			
			c = findClient(username, password, Clients);
			//do stuff
		}
		else {
			//do stuff
		}
	}

}
