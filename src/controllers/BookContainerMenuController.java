package controllers;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import UI.BookContainerMenu;
import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Content;
import core.DatabaseData;
import core.Environment;
import core.Location;
import core.NotifyObject;

public class BookContainerMenuController {
	Client client;
	Container container;
	BookContainerMenu view;
	Content[] Contents;
	Location[] Locations;
	
	String selectedContent;
	String selectedStart;
	String selectedEnd;
	
	public BookContainerMenuController(Client client, Container container) {
		this.client = client;
		this.container = container;
		this.view = new BookContainerMenu();
		Contents = DatabaseData.getContents();
		Locations = DatabaseData.getLocations();
		selectedContent = "";
		selectedStart = "";
		selectedEnd = "";
	}
	
	public void initController() {
		JComboBox contentBox = view.getcontentBox();
		JComboBox startBox = view.getstartBox();
		JComboBox endBox = view.getendBox();
		
		view.getBookButton().addActionListener(e -> bookContainer(e));
		view.getCancelButton().addActionListener(e -> cancel());
		contentBox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	selectedContent = (String) contentBox.getSelectedItem();   
		    }
		});
		startBox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	selectedStart = (String) startBox.getSelectedItem();   
		    }
		});
		endBox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	selectedEnd = (String) endBox.getSelectedItem();   
		    }
		});
	}
	
	private void bookContainer(ActionEvent e) {
		if (!(selectedStart.equals(selectedEnd.equals("")))) {
			  Content content = Content.findContent(selectedContent, Contents);
			  
			  Location locStart = Location.findLocation(selectedStart, Locations);
			  Location locEnd = Location.findLocation(selectedEnd, Locations);
			  
			  container.setContainerContent(content);
			  
			  //Set client of container in database
			  DatabaseData.getDatabase().updateDatabase("Containers", "Client_ID",Integer.toString(client.getID()), Integer.toString(container.getContainerID()));
			  //Update container in database (add content)
			  DatabaseData.getDatabase().updateDatabase("Containers", "Content_ID", Integer.toString(content.getContentID()), Integer.toString(container.getContainerID()));
			  
			  //Configure container in container array
			  DatabaseData.updateContainer(container);
			 
			  ContainerJourney cj = new ContainerJourney(locStart, locEnd, container);
			  
			  //Update database by adding cj
			  //Add cj to containerJourney array
			  DatabaseData.getDatabase().addToDatabase("Journies", cj.toString());
			  DatabaseData.addContainerJourney(cj);
			  
			  NotifyObject response = new NotifyObject(31, "Container is succesfully booked");
			  JOptionPane.showMessageDialog(null, response.getNotifyMessage());
			
			  ClientMenuController cm = new ClientMenuController(client);
			  view.dispose();
			  cm.initController();
		}
		else {
			JOptionPane.showMessageDialog(null, "Start and end can not be the same location");
		}
	}
	
	private void cancel() {
		ClientMenuController cm = new ClientMenuController(client);
		view.dispose();
		cm.initController();
	}

}
