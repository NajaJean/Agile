package controllers;

import UI.ClientFindConUI;
import UI.LogiticFindClientByMailUI;
import core.ArraySearch;
import core.Client;
import core.Container;
import core.DatabaseData;

public class ClientFindConController {

	private ClientFindConUI view;
	Container[] containers;
	Container container;
	Client c;
	
	ArraySearch search;
	
	public ClientFindConController() {
		view = new ClientFindConUI();
		this.c = c;
		this.containers = DatabaseData.getContainers();
		this.search = new ArraySearch(new Container());
	}
	
	public void initController() {
		view.getFindContainerButton().addActionListener(e -> tryFindContainer());
		view.getGoBackButton().addActionListener(e -> goBack());
	}
	public void tryFindContainer() {
		String content = view.getContentField().getText();
		for ()
	
	}
	
	public void goBack() {
		ClientMenuController cm = new ClientMenuController(c);
		view.dispose();
		cm.initController();
	}
}
