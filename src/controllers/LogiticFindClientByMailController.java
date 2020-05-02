package controllers;

import UI.Login;
import UI.LogiticFindClientByMailUI;
import core.ArraySearch;
import core.Client;
import core.DatabaseData;

public class LogiticFindClientByMailController {
	private LogiticFindClientByMailUI view;
	Client[] Clients;
	Client client;
	
	ArraySearch search;
	
	public LogiticFindClientByMailController() {
		view = new LogiticFindClientByMailUI();
		this.Clients = DatabaseData.getClients();
		this.search = new ArraySearch(new Client());
	}
	
	public void initController() {
		view.getFindClientButton().addActionListener(e -> tryFindClient());
		view.getGoBackButton().addActionListener(e -> goBack());
	}
	public void tryFindClient() {
		String email = view.getEmailField().getText();
		int clientIDX = search.findIDX(email, Clients);
		if (clientIDX != -1) {
			client = Clients[clientIDX];
			view.theClient(client.getID(), client.getName(), client.getEmail(), client.getAddress());
		}
		else {
			view.failm();
			
		}
	}
	
	public void goBack() {
		LogisticCompanyMenuController lm = new LogisticCompanyMenuController();
		view.dispose();
		lm.initController();
	}

}
