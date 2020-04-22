package controllers;

import UI.ClientMenu;
import UI.ConfigureClient;
import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Content;
import core.Database;
import core.Environment;
import core.Location;

public class ConfigureClientController {
	private ConfigureClient view; 
	Database d; 
	Client[] Clients;
	Environment[] Enviros;
	Content[] Contents;
	Container[] Containers; 
	Location[] Locations;
	ContainerJourney[] Journies;
	
	ConfigureClientController(Client c,
						 	  Client[] Clients,
						 	  Environment[] Enviros,
						 	  Content[] Contents,
						 	  Container[] Containers,
						 	  Location[] Locations,
						 	  ContainerJourney[] Journies,
						 	  Database d) {
		view = new ConfigureClient();
		this.Clients = Clients;
		this.Enviros = Enviros;
		this.Contents = Contents;
		this.Containers = Containers;
		this.Locations = Locations;
		this.Journies = Journies;
		this.d = d;
	}
	
	public void initController() {
		view.getDoneButton().addActionListener(e -> goToClientMenu());
//		view.getSign_outItem().addActionListener(e -> signOut());
//		view.getConfigure_client_detailsItem().addActionListener(e -> goToConfigureClientMenu());
	}
	
	public void goToClientMenu() {
		ClientMenuController cm = new ClientMenuController(c, Clients,Enviros,Contents,Containers,Locations,Journies,d);
		view.dispose();
		
		cm.initController();
	}
}
