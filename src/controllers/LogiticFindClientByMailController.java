package controllers;

import UI.Login;
import UI.LogiticFindClientByMailUI;
import core.Client;
import core.DatabaseData;

public class LogiticFindClientByMailController {
	private LogiticFindClientByMailUI view;
	Client[] Clients;
	Client client;
	
	public LogiticFindClientByMailController() {
		view = new LogiticFindClientByMailUI();
		this.Clients = DatabaseData.getClients();
	}
	
	public void initController() {
		view.getFindClientButton().addActionListener(e -> tryFindClient());
		view.getGoBackButton().addActionListener(e -> goBack());
	}
	public void tryFindClient() {
		String email = view.getEmailField().getText();
		if (Client.findClient(email, Clients) != null) {
			client = Client.findClient(email, Clients);
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
