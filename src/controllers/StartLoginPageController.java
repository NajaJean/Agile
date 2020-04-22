package controllers;

import UI.StartLoginPage;
import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Content;
import core.Database;
import core.Environment;
import core.Location;

public class StartLoginPageController {
	private StartLoginPage view;
	
	
	static Database d; 
	static Client[] Clients;
	static Environment[] Enviros;
	static Content[] Contents;
	static Container[] Containers; 
	static Location[] Locations;
	static ContainerJourney[] Journies;
	
	public StartLoginPageController(StartLoginPage view,
									Client[] Clients,
									Environment[] Enviros,
									Content[] Contents,
									Container[] Containers,
									Location[] Locations,
									ContainerJourney[] Journies,
									Database d){
		this.view = view;
		this.Clients = Clients;
		this.Enviros = Enviros;
		this.Contents = Contents;
		this.Containers = Containers;
		this.Locations = Locations;
		this.Journies = Journies;
		this.d = d;
	}
	
	public void initController() {
		view.getcLoginButton().addActionListener(e -> openLoginFor("Client"));
		view.getlcLoginButton().addActionListener(e -> openLoginFor("Logistic Company"));
	}
	
	private void openLoginFor(String CorL) {
		LoginController lc;
		if (CorL.equals("Client")) {
			lc = new LoginController("Client",Clients,Enviros,Contents,Containers,Locations,Journies,d);
		}
		else {
			lc = new LoginController("Logistic Company",Clients,Enviros,Contents,Containers,Locations,Journies,d);
		}
		view.dispose();
		lc.initController();
	}
	
}
