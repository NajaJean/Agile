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
//		view.getGoBackButton().addActionListener(e -> viewBack.openLoginFor("Logistic Company"));
	}
	
	public void tryLogin(String CorL) {
		String username = view.getUserTextField().getText();
		String password = view.getPasswordTextField().getText();
		
		if (CorL.contentEquals("Client")) {
			if (d.checkUser(username, password)) {
				c = Client.findClient(username, password, Clients);
				System.out.println(c.getName());
				response = c.logIn(true);
				JOptionPane.showMessageDialog(null, response.getNotifyMessage());
				view.dispose();
				
				//Client menu controller tiiiiime
				
			}
			else {
				response = c.logIn(false);
				JOptionPane.showMessageDialog(null, response.getNotifyMessage());
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
		Login newView = new Login(CorL);
		view.dispose();
		this.view = newView;
		
	}

}
