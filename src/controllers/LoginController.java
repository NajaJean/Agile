package controllers;

import javax.swing.JOptionPane;

import UI.Login;
import UI.StartLoginPage;
import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Content;
import core.Database;
import core.Environment;
import core.Location;
import core.LogisticCompany;
import core.NotifyObject;

public class LoginController {
	private Login view; 
	private StartLoginPage viewBack;
	Database d; 
	Client[] Clients;
	Environment[] Enviros;
	Content[] Contents;
	Container[] Containers; 
	Location[] Locations;
	ContainerJourney[] Journies;
	
	Client c;
	LogisticCompany l = new LogisticCompany();
	NotifyObject response;
	String CorL;
	
	public LoginController(String CorL,
						   Client[] Clients,
						   Environment[] Enviros,
						   Content[] Contents,
						   Container[] Containers,
						   Location[] Locations,
						   ContainerJourney[] Journies,
						   Database d) {
		view = new Login(CorL);
		this.CorL = CorL;
		this.Clients = Clients;
		this.Enviros = Enviros;
		this.Contents = Contents;
		this.Containers = Containers;
		this.Locations = Locations;
		this.Journies = Journies;
		this.d = d;
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
			if (d.checkUser(username, password)) {
				c = Client.findClient(username, password, Clients);
				response = c.logIn(true);
				JOptionPane.showMessageDialog(null, response.getNotifyMessage());
				view.dispose();
				
				//Client menu controller tiiiiime
				
			}
			else {
//				response = c.logIn(false);
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
		StartLoginPage v = new StartLoginPage();
		StartLoginPageController w = new StartLoginPageController(v, Clients, Enviros, Contents, Containers, Locations, Journies, d);
		w.initController();
	}
	
}
