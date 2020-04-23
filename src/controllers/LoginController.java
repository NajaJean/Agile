package controllers;

import javax.swing.JOptionPane;

import UI.Login;
import UI.StartLoginPage;
import core.Client;
import core.DatabaseData;
import core.LogisticCompany;
import core.NotifyObject;

public class LoginController {
	private Login view; 
	
	Client[] Clients;
	
	Client client;
	LogisticCompany l = new LogisticCompany();
	NotifyObject response;
	String CorL;
	
	public LoginController(String CorL) {
		view = new Login(CorL);
		this.CorL = CorL;
		this.Clients = DatabaseData.Clients;
	}
	
	public void initController() {
		view.getLoginButton().addActionListener(e -> tryLogin(CorL));
		view.getResetButton().addActionListener(e -> reset());
		view.getGoBackButton().addActionListener(e -> goToStartLoginPage());
	}
	
	public void tryLogin(String CorL) {
		String username = view.getUserTextField().getText();
		String password = view.getPasswordTextField().getText();
		
		if (CorL.contentEquals("Client")) {
			if (DatabaseData.d.checkUser(username, password)) {
				client = Client.findClient(username, password, Clients);
				response = client.logIn(true);
				JOptionPane.showMessageDialog(null, response.getNotifyMessage());
				
				ClientMenuController cm = new ClientMenuController(client);
				view.dispose();
				
				cm.initController();
				
			}
			else {
				//response = client.logIn(false);
				JOptionPane.showMessageDialog(null, "Incorrect username or password");
			}
		}
		else {
			if (username.equals("MSK") && password.equals("1234")) {
				response = l.logIn(true);
				JOptionPane.showMessageDialog(null, response.getNotifyMessage());
				view.dispose();
				
				//LogisticCompany menu controller tiiiiime
				
			}
			else {
				response = l.logIn(false);
				JOptionPane.showMessageDialog(null, response.getNotifyMessage());
			}
		}
	}
	
	private void reset() {
		view.getUserTextField().setText("");
		view.getPasswordTextField().setText("");
	}

	private void goToStartLoginPage() {
		view.dispose();
		StartLoginPage view = new StartLoginPage();
		StartLoginPageController w = new StartLoginPageController(view);
		w.initController();
	}
}
