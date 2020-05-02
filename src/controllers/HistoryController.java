package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import UI.*;
import core.*;

public class HistoryController {
	
	private History view;
	private int selId;
	private String selParameter;
	private Client client;
	
	HistoryController(Client c) {
		client = c;
		view = new History(c);
		selId = -1;
		selParameter = "";
	
	}
	
	public void initController() {

		view.getShowButton().addActionListener(e -> goToShowHistory());
		view.getCancelButton().addActionListener(e -> cancel());
		JComboBox idBox = view.getIdBox();
		JComboBox paramBox = view.getParamBox();
		
		
		idBox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	selId = Integer.parseInt((String) idBox.getSelectedItem());   
		    }
		});
		
		paramBox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	selParameter = (String) paramBox.getSelectedItem();   
		    }
		});
	}
	
	private void goToShowHistory() 
	{

		if (selParameter == "Temperature") 
		{
			Graph1Temp g = new Graph1Temp(selId);
		}
		else if (selParameter == "Pressure") 
		{
			Graph2Pressure g = new Graph2Pressure(selId);
		}
		else 
		{
			Graph3Humidity g = new Graph3Humidity(selId);
		}
		
	}
	
	private void cancel() {
		ClientMenuController cm = new ClientMenuController(client);
		view.dispose();
		cm.initController();
	}

}
