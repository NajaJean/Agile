package controllers;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import UI.BookContainerMenu;
import core.ArraySearch;
import core.Client;
import core.Container;
import core.ContainerJourney;
import core.Content;
import core.DatabaseData;
import core.Location;
import core.NotifyObject;

public class BookContainerMenuController {
	Client client;
	Container container;
	BookContainerMenu view;
	Content[] Contents;
	Location[] Locations;
	ContainerJourney[] Journies;
	
	ArraySearch search;
	
	String selectedContent;
	String selectedStart;
	String selectedEnd;
	
	String selSY;
	String selSM;
	String selSD;
	
	String selEY;
	String selEM;
	String selED;
	
	LocalDate selectedStartDate;
	LocalDate selectedEndDate;
	
	
	
	public BookContainerMenuController(Client client, Container container) {
		this.client = client;
		this.container = container;
		this.view = new BookContainerMenu();
		Contents = DatabaseData.getContents();
		Locations = DatabaseData.getLocations();
		Journies = DatabaseData.getJournies();
		selectedContent = "";
		selectedStart = "";
		selectedEnd = "";
		selSY = "";
		selSM = "";
		selSD = "";
		selEY = "";
		selEM = "";
		selED = "";
		this.search = new ArraySearch();
	}
	
	public void initController() {
		JComboBox contentBox = view.getcontentBox();
		JComboBox startBox = view.getstartBox();
		JComboBox endBox = view.getendBox();
		
		JComboBox sY = view.getStartYBox();
		JComboBox sM = view.getStartMBox();
		JComboBox sD = view.getStartDBox();
		
		JComboBox eY = view.getEndYBox();
		JComboBox eM = view.getEndMBox();
		JComboBox eD = view.getEndDBox();
		
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
		
		sY.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	selSY = (String) sY.getSelectedItem();   
		    }
		});
		
		sM.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	selSM = (String) sM.getSelectedItem();   
		    }
		});
		
		sD.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	selSD = (String) sD.getSelectedItem();   
		    }
		});
		

		eY.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	selEY = (String) eY.getSelectedItem();   
		    }
		});
		
		eM.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	selEM = (String) eM.getSelectedItem();   
		    }
		});
		
		eD.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	selED = (String) eD.getSelectedItem();   
		    }
		});
	}
	
	private void bookContainer(ActionEvent e) {
		
		if (!(selectedContent.equals("")|selectedStart.equals("")|selectedEnd.equals("")
				|selSY.equals("")|selSM.equals("")|selSD.equals("")
				|selEY.equals("")|selEM.equals("")|selED.equals(""))) {
		
		if (!(selectedStart.equals(selectedEnd))) {
			
			LocalDate startDate = LocalDate.of(Integer.parseInt(selSY), Integer.parseInt(selSM), Integer.parseInt(selSD) );
			LocalDate endDate = LocalDate.of(Integer.parseInt(selEY), Integer.parseInt(selEM), Integer.parseInt(selED));
			
			if (ChronoUnit.DAYS.between(startDate, endDate) >= 0) {
			  search.setSearch(new Content());
			  int contentIDX = search.findIDX(selectedContent, Contents);
			  if (contentIDX == -1) {
				  JOptionPane.showMessageDialog(null, "Error booking container");
				  return;
			  }
			  Content content = Contents[contentIDX];
			  
			  search.setSearch(new Location());
			  int locationStartIDX = search.findIDX(selectedStart, Locations);
			  int locationEndIDX = search.findIDX(selectedEnd, Locations);
			  if (locationStartIDX == -1 || locationEndIDX == -1) {
				  JOptionPane.showMessageDialog(null, "Error booking container");
				  return;
			  }
			  Location locStart = Locations[locationStartIDX];
			  Location locEnd = Locations[locationEndIDX];
			  
			  container.setContainerContent(content);
			  
			  //Set client of container in database
			  DatabaseData.getDatabase().updateDatabase("Containers", "Client_ID",Integer.toString(client.getID()), Integer.toString(container.getContainerID()));
			  //Update container in database (add content)
			  DatabaseData.getDatabase().updateDatabase("Containers", "Content_ID", Integer.toString(content.getContentID()), Integer.toString(container.getContainerID()));
			  //Update location of container in database 
			  DatabaseData.getDatabase().updateDatabase("Containers", "Location_ID", Integer.toString(locEnd.getLocationID()), Integer.toString(container.getContainerID()));
			 
			  ContainerJourney cj = new ContainerJourney(locStart, locEnd, container, startDate, endDate);
			  
			  //Update database by adding cj
			  //Add cj to containerJourney array
			  DatabaseData.getDatabase().addToDatabase("Journies", cj.toString());
			  
			  NotifyObject response = new NotifyObject(31, "Container is succesfully booked");
			  JOptionPane.showMessageDialog(null, response.getNotifyMessage());
			  
			  ClientMenuController cm = new ClientMenuController(client);
			  view.dispose();
			  cm.initController();
			}
			else {
				JOptionPane.showMessageDialog(null, "The end date has to be later then the start date");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Start and end can not be the same location");
		}
		}
		else {
			JOptionPane.showMessageDialog(null, "Please select an item from all scroll down menus");
		}
	}
	
	private void cancel() {
		ClientMenuController cm = new ClientMenuController(client);
		view.dispose();
		cm.initController();
	}

}
