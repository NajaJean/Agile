package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ExternalData.DatabaseData;
import UI.ClientFindConUI;
import UI.LogiticFindClientByMailUI;
import core.ArraySearch;
import core.Client;
import core.Container;
import core.Content;

public class ClientFindConController {

	private ClientFindConUI view;
	Container[] containers;
	Container container;
	Content[] contents;
	Content content;
	Client c;
	ArraySearch search;
	String[] listItems;
	List<Content> conArray = new ArrayList<Content>();

	public ClientFindConController(Client c) {
		view = new ClientFindConUI();
		this.containers = DatabaseData.getContainers();
		this.contents = DatabaseData.getContents();
		this.search = new ArraySearch(new Container());
		this.c = c;	
	}
	
	public void initController() {
		view.getFindContainerButton().addActionListener(e -> tryFindContainer());
		view.getGoBackButton().addActionListener(e -> goBack());
	}
	
	public void tryFindContainer() {
		String content = view.getContentField().getText();
		Container[] clientContainers = c.findClientContentContainers(content, containers);
	
		view.containerList(content, clientContainers);
	}
	
	
	public void goBack() {
		ClientMenuController cm = new ClientMenuController(c);
		view.dispose();
		cm.initController();
	}
}
