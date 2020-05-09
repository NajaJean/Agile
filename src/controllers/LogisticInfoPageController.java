package controllers;

import ExternalData.DatabaseData;
import UI.LogisticInfoPage;
import core.Client;
import core.Container;

public class LogisticInfoPageController {
	Container[] Containers;
	Client[] Clients;
	LogisticInfoPage view;
	
	public LogisticInfoPageController(String ClorCo){
		Containers = DatabaseData.getContainers();
		Clients = DatabaseData.getClients();
		this.view = new LogisticInfoPage(ClorCo,Clients,Containers);
	}
	
	public void initController() {
		view.getreturnButton().addActionListener(e -> goBack());
	}
	
	public void goBack() {
		LogisticCompanyMenuController lm = new LogisticCompanyMenuController();
		view.dispose();
		lm.initController();
	}

}
