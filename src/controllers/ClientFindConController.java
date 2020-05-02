package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import UI.ClientFindConUI;
import UI.LogiticFindClientByMailUI;
import core.ArraySearch;
import core.Client;
import core.Container;
import core.Content;
import core.DatabaseData;

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
		for (int i = 0; i<contents.length;i++) {
			System.out.println(c.getID());
			int temp = contents[i].findFromStrings(String.valueOf(c.getID()), content, contents);
			System.out.println(temp);
			if (temp != -1) {
				contents[temp].toString();
				conArray.add(contents[temp]);    	      
		        Content[] aCon = new Content[contents.length - 1]; 
		        for (int j = 0, k = 0; j < contents.length; j++) { 
	
		            if (j == temp) {
		                continue; 
		            }  
		            aCon[k++] = contents[j];
		        } 
		        contents = aCon;
			}

		}
		Content[] conList = (Content[]) conArray.toArray();
		view.containerList(conList);
	
	}
	
	public void goBack() {
		ClientMenuController cm = new ClientMenuController(c);
		view.dispose();
		cm.initController();
	}
}
