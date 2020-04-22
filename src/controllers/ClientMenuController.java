package controllers;

import UI.ClientMenu;
import UI.Login;
import UI.StartLoginPage;
import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Content;
import core.Database;
import core.Environment;
import core.Location;

public class ClientMenuController {
	private ClientMenu view; 
	Database d; 
	Client[] Clients;
	Environment[] Enviros;
	Content[] Contents;
	Container[] Containers; 
	Location[] Locations;
	ContainerJourney[] Journies;
	
	ClientMenuController(Client c,
						 Client[] Clients,
			   			 Environment[] Enviros,
			   			 Content[] Contents,
			   			 Container[] Containers,
			   			 Location[] Locations,
			   			 ContainerJourney[] Journies,
			   			 Database d) {
		view = new ClientMenu();
		this.Clients = Clients;
		this.Enviros = Enviros;
		this.Contents = Contents;
		this.Containers = Containers;
		this.Locations = Locations;
		this.Journies = Journies;
		this.d = d;
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
		StartLoginPage v = new StartLoginPage();
		StartLoginPageController w = new StartLoginPageController(v, Clients, Enviros, Contents, Containers, Locations, Journies, d);
		w.initController();
	}
	
	private void goToConfigureClientMenu() {
		
	}
}
