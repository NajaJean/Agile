package controllers;

import javax.swing.JOptionPane;

import ExternalData.Database;
import UI.Login;
import UI.StartLoginPage;
import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Content;
import core.Environment;
import core.Location;
import core.LogisticCompany;
import core.NotifyObject;

public class MapController {

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

	
	public MapController( Client[] Clients, Environment[] Enviros, Content[] Contents,
						  Container[] Containers, Location[] Locations, ContainerJourney[] Journies,
						  Database d) 
	{
		this.Clients = Clients;
		this.Enviros = Enviros;
		this.Contents = Contents;
		this.Containers = Containers;
		this.Locations = Locations;
		this.Journies = Journies;
		this.d = d;
	}
}	