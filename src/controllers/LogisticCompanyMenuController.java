package controllers;

import UI.AutoAdd;
import UI.LogisticCompanyMenu;
import UI.StartLoginPage;
import core.Calendar;
import core.Database;
import core.DatabaseData;
import core.Client;
import core.Container;
import core.Content;
import core.Location;

public class LogisticCompanyMenuController {
	LogisticCompanyMenu view;
	String mostUsedContent;
	String mostVisitedLoc;
	String busiestClient;
	String mostTravelledCon;
	
	Content[] Contents;
	Location[] Locations;
	Client[] Clients;
	Container[] Containers;
	
	public LogisticCompanyMenuController() {
		view = new LogisticCompanyMenu();
		this.Contents = DatabaseData.getContents();
		this.Locations = DatabaseData.getLocations();
		this.Clients = DatabaseData.getClients();
		this.Containers = DatabaseData.getContainers();
	}
	
	public void initController() {
		view.getUpdateContainerStatusItem().addActionListener(e -> openUpdateContainerStatusMenu());
		view.getRegisterContainerItem().addActionListener(e -> openRegisterNewContainerMenu());
		view.getCreateNewClientItem().addActionListener(e -> goToCreateClientMenu());
		view.getSignOutItem().addActionListener(e -> signOut());
		view.getAutoMenuItem().addActionListener(e -> goToAutoMenu());
		view.getClientInfoItem().addActionListener(e -> goToClientInfoMenu());
		view.getClientByMailItem().addActionListener(e -> goToClientByMailMenu());
		view.getContainerInfoItem().addActionListener(e -> goToContainerInfoMenu());
		view.getTomorrow().addActionListener(e -> goTOmorrow());
		view.getNextWeek().addActionListener(e -> goNextWeek());
		getStats();
		view.changeStats(mostTravelledCon,mostUsedContent,busiestClient,mostVisitedLoc);
	}
	
	private void getStats() {
		Database d = DatabaseData.getDatabase();
		String maxContent = "SELECT Content_ID FROM Containers GROUP BY Content_ID"
				+ " HAVING COUNT (Content_ID) = (SELECT MAX(mycount) FROM ("
				+ "SELECT Content_ID, COUNT(Content_ID) mycount FROM Containers GROUP BY Content_ID))";
		mostUsedContent = Contents[Integer.parseInt(d.queryDatabase(maxContent))-1].getName();
		
		String maxLoc = "SELECT End FROM Journies GROUP BY End"
				+ " HAVING COUNT (End) = (SELECT MAX(mycount) FROM ("
				+ "SELECT End, COUNT(End) mycount FROM Journies GROUP BY End))";
		mostVisitedLoc = Locations[Integer.parseInt(d.queryDatabase(maxLoc))-1].getLocationName();
		
		String maxClient = "SELECT Client_ID FROM Containers GROUP BY Client_ID"
				+ " HAVING COUNT (Client_ID) = (SELECT MAX(mycount) FROM ("
				+ "SELECT Client_ID, COUNT(Client_ID) mycount FROM Containers GROUP BY Client_ID))";
		busiestClient = Clients[Integer.parseInt(d.queryDatabase(maxClient))-1].getName() + " ID: " + Clients[Integer.parseInt(d.queryDatabase(maxClient))-1].getID();
		
		String maxContainer = "SELECT Container_ID FROM Journies GROUP BY Container_ID"
				+ " HAVING COUNT (Container_ID) = (SELECT MAX(mycount) FROM ("
				+ "SELECT Container_ID, COUNT(Container_ID) mycount FROM Journies GROUP BY Container_ID))";
		mostTravelledCon = d.queryDatabase(maxContainer);
	}
	
	private void goTOmorrow() {
		view.dispose();
		Calendar c = new Calendar();
		c.goTomorrow();
		LogisticCompanyMenuController w = new LogisticCompanyMenuController();
		w.initController();
	}
	
	private void goNextWeek() {
		view.dispose();
		Calendar c = new Calendar();
		c.goIntoTheFutureXDays(7);
		LogisticCompanyMenuController w = new LogisticCompanyMenuController();
		w.initController();
	}
	
	private void goToClientInfoMenu() {
		view.dispose();
		LogisticInfoPageController w = new LogisticInfoPageController("Client");
		w.initController();
	}
	private void goToClientByMailMenu() {
		view.dispose();
		LogiticFindClientByMailController w = new LogiticFindClientByMailController();
		w.initController();
	}
	
	private void goToContainerInfoMenu() {
		view.dispose();
		LogisticInfoPageController w = new LogisticInfoPageController("Container");
		w.initController();
	}
	
	private void goToAutoMenu() {
		view.dispose();
		AutoAddController w = new AutoAddController();
		w.initController();
	}

	private void signOut() {
		view.dispose();
		StartLoginPage view = new StartLoginPage();
		StartLoginPageController w = new StartLoginPageController(view);
		w.initController();
	}
	
	private void openUpdateContainerStatusMenu() {
		view.dispose();
		LogisticUpdateController w = new LogisticUpdateController();
		w.initController();
	}
	
	private void openRegisterNewContainerMenu() {
		view.dispose();
		RegisterContainerController w = new RegisterContainerController();
		w.initController();
	}
	
	private void goToCreateClientMenu() {
		view.dispose();
		LogisticCreateClientController w = new LogisticCreateClientController();
		w.initController();
	}
}
