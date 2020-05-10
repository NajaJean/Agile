package controllers;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ExternalData.DatabaseData;
import ExternalData.Logs;
import UI.ClientMenu;
import UI.ConfigureClient;
import UI.Map;
import UI.StartLoginPage;
import core.ArraySearch;
import core.Calendar;
import core.Client;
import core.Container;
import core.ContainerJourney;
import core.NotifyObject;

public class ClientMenuController {
	private ClientMenu view; 
	Client client;
	ContainerJourney[] cJs;
	Container[] Containers;
	
	Logs Log;
	ArraySearch search;
	
	ClientMenuController(Client client) {
		view = new ClientMenu(client);
		this.client = client;
		this.cJs = DatabaseData.getJournies();
		this.Containers = DatabaseData.getContainers();
		this.search = new ArraySearch(new Container());
		
		Log = new Logs(Containers);
		
		String thresholdMessage = containersOutsideThreshold(client);
		if (!thresholdMessage.equals("")) {
			JOptionPane.showMessageDialog(null,thresholdMessage);
		}
	}
	
	public void initController() {
		view.changeStats(getStats());
		view.getBook_containerItem().addActionListener(e -> goToBookContainerMenu());
		view.getSign_outItem().addActionListener(e -> signOut());
		view.getConfigure_client_detailsItem().addActionListener(e -> goToConfigureClientMenu());
		view.getHistoryItem().addActionListener(e -> gotoHistoryItem());
		view.getTomorrow().addActionListener(e -> goTOmorrow());
		view.getNextWeek().addActionListener(e -> goNextWeek());
		view.getClientFindCon().addActionListener(e -> goToClientFindCon());
	}
	
	private String getStats() {
		ContainerJourney[] ClientJournies = ContainerJourney.clientJournies(DatabaseData.getJournies(), client);
		String s;
		try {
			int longestTravelledCon = ContainerJourney.longestJourney(ClientJournies).getJourneyID();
	 		ContainerJourney journey = ContainerJourney.longestJourney(ClientJournies);
	 		double distance = Math.round(journey.getEndLocation().euclideanDistance(journey.getCurrentLocationDoubleA()));
	 		s = Integer.toString(longestTravelledCon) + " Distance: " + Double.toString(distance) + "km From: " + 
	 		journey.getStartLocation().getLocationName() + " - " + journey.getEndLocation().getLocationName();
		}
 		catch(Exception e) {
 			s = "You have no journies logged - past or present";
 		}
 		return s;
	}
	
	private void goTOmorrow() {
		Calendar c = new Calendar();
		c.goTomorrow();
		ClientMenuController w = new ClientMenuController(client);
		w.initController();
		view.dispose();
	}
	
	private void goNextWeek() {
		Calendar c = new Calendar();
		c.goIntoTheFutureXDays(7);
		ClientMenuController w = new ClientMenuController(client);
		w.initController();
		view.dispose();
	}
	
	private void goToBookContainerMenu() {
		int id = DatabaseData.getDatabase().getEmptyContainer();	

		if(id==0) {
			NotifyObject response = new NotifyObject(33, "No empty containers available");
			JOptionPane.showMessageDialog(null,response.getNotifyMessage());
		}
		else {
			int containerIDX = search.findIDX(id, Containers);
			if (containerIDX == -1) {
				JOptionPane.showMessageDialog(null, "Error finding empty container");
				return;
			}
			Container container = Containers[containerIDX];
			container.setClientofContainer(client);
			
			view.dispose();
			BookContainerMenuController wBook = new BookContainerMenuController(client, container);
			wBook.initController();
		}
	}
	
	private void signOut() {
		view.dispose();
		StartLoginPage view = new StartLoginPage();
		StartLoginPageController w = new StartLoginPageController(view);
		w.initController();
	}
	
	private void goToConfigureClientMenu() {
		view.dispose();
		ConfigureClientController wConf = new ConfigureClientController(client);
		wConf.initController();
	}
	
	private void goToClientFindCon() {
		view.dispose();
		ClientFindConController w = new ClientFindConController(client);
		w.initController();
	}
	
	private void gotoHistoryItem() {
		
		if (client.getClientsCJs(cJs).length != 0) {	
			view.dispose();
			HistoryController wConf = new HistoryController(client);
			wConf.initController();
		}
		else 
		{
			NotifyObject noContainers = new NotifyObject(111, "You dont have any active containers");
			JOptionPane.showMessageDialog(null, noContainers.getNotifyMessage());
		}
	}
	
	private String containersOutsideThreshold(Client client) {
		String thresholdMessage = "";
		Container[] clientContainers = client.findClientContainers(Containers);
		for (int i=0; i<clientContainers.length; i++) {
			String log = Log.readFile("Container "+clientContainers[i].getContainerID());
			
			if(!log.equals("")) {
				String lines[] = log.split("\\n");
		  		String[][] data = new String[lines.length][];
		  		String[][] returnData = new String[lines.length][];
		  		
		  		String outsideDates = "";
		  		for (int j = 0; j < lines.length; j++) 
		  		{   data[j] = lines[j].split("\\t");
		  			returnData[j] = new String[]{data[j][data[j].length-1], data[j][data[j].length-3]};
		  			
		  			if (returnData[j][0].equals("Not OK")) {
		  				outsideDates = outsideDates + returnData[j][1] + ", ";
		  			}
		  		}
		  		if (!outsideDates.equals("")) {
		  			thresholdMessage = thresholdMessage + 
		  							   "Container "+clientContainers[i].getContainerID()+ 
		  							   " with "+clientContainers[i].getContainerContent().getName() + 
		  							   " has exceeded it's threshold at date: "+outsideDates+"\n\n";
		  		}
			}
		}
		return thresholdMessage;
	}
}
