package controllers;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import UI.ClientMenu;
import UI.ConfigureClient;
import UI.Map;
import UI.StartLoginPage;
import core.Calendar;
import core.Client;
import core.Container;
import core.ContainerJourney;
import core.DatabaseData;
import core.NotifyObject;

public class ClientMenuController {
	private ClientMenu view; 
	Client client;
	ContainerJourney[] cJs;
	
	ClientMenuController(Client client) {
		view = new ClientMenu(client);
		this.client = client;
		this.cJs = DatabaseData.getJournies();
	}
	
	public void initController() {
		view.getBook_containerItem().addActionListener(e -> goToBookContainerMenu());
		view.getSign_outItem().addActionListener(e -> signOut());
		view.getConfigure_client_detailsItem().addActionListener(e -> goToConfigureClientMenu());
		view.getShowMyContainersItem().addActionListener(e -> gotoShowMyContainersItem());
		view.getTomorrow().addActionListener(e -> goTOmorrow());
		view.getNextWeek().addActionListener(e -> goNextWeek());
	}
	
	private void goTOmorrow() {
		view.dispose();
		Calendar c = new Calendar();
		c.goTomorrow();
		ClientMenuController w = new ClientMenuController(client);
		w.initController();
	}
	
	private void goNextWeek() {
		view.dispose();
		Calendar c = new Calendar();
		c.goIntoTheFutureXDays(7);
		ClientMenuController w = new ClientMenuController(client);
		w.initController();
	}
	
	private void goToBookContainerMenu() {
		int id = DatabaseData.getDatabase().getEmptyContainer();	

		if(id==0) {
			NotifyObject response = new NotifyObject(33, "No empty containers available");
			JOptionPane.showMessageDialog(null,response.getNotifyMessage());
		}
		else {
			Container container = Container.findContainer(id, DatabaseData.getContainers());
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
	
	private void gotoShowMyContainersItem() {
		
		Map map = new Map(client, cJs);
		map.showAllContainers();
		
	}
}
