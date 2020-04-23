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
	Client c;
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
		this.c = c;
		view = new ConfigureClient(c);
		this.Clients = Clients;
		this.Enviros = Enviros;
		this.Contents = Contents;
		this.Containers = Containers;
		this.Locations = Locations;
		this.Journies = Journies;
		this.d = d;
	}
	
	public void initController() {
		view.getDoneButton().addActionListener(e -> saveChanges());
		view.getReturnButton().addActionListener(e -> goToClientMenu());
	}
	
	public void goToClientMenu() {
		ClientMenuController cm = new ClientMenuController(c, Clients,Enviros,Contents,Containers,Locations,Journies,d);
		view.dispose();
		cm.initController();
	}
	
	public void saveChanges() {
		String NewUsername = view.getNewTextField()[0].getText();
		String NewPassword = view.getNewTextField()[1].getText();
		String NewName = view.getNewTextField()[2].getText();
		String NewEmail = view.getNewTextField()[3].getText();
		String NewAddress = view.getNewTextField()[4].getText();
		
		
		
	}
}
