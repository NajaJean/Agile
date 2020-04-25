package controllers;

import UI.ClientMenu;
import UI.ConfigureClient;
import UI.Map;
import UI.StartLoginPage;
import core.Client;
import core.ContainerJourney;
import core.DatabaseData;

public class ClientMenuController {
	private ClientMenu view; 
	Client client;
	ContainerJourney[] cJs;
	
	ClientMenuController(Client client) {
		view = new ClientMenu();
		this.client = client;
		this.cJs = DatabaseData.getJournies();
	}
	
	public void initController() {
		view.getBook_containerItem().addActionListener(e -> goToBookContainerMenu());
		view.getSign_outItem().addActionListener(e -> signOut());
		view.getConfigure_client_detailsItem().addActionListener(e -> goToConfigureClientMenu());
		view.getShowMyContainersItem().addActionListener(e -> gotoShowMyContainersItem());
	}
	

	
	private void goToBookContainerMenu() {
		
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
		System.out.println(cJs[0].toString());
		System.out.println(cJs[1].toString());
		System.out.println(cJs[2].toString());
		Map map = new Map(cJs);
		map.showAllContainers();
		
	}
}
