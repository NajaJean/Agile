package controllers;

import javax.swing.JOptionPane;

import UI.*;
import core.*;

public class AutoAddController {
	
	private AutoAdd view;
	private Automation auto = new Automation();
	
	public AutoAddController(){
		view = new AutoAdd();
	}
	
	public void initController() {
		view.getaddClientButton().addActionListener(e -> addRClient());
		view.getaddConrainerButton().addActionListener(e -> addRContainer());
		view.getaddCJButton().addActionListener(e -> addRCJ());
		view.getDoneButton().addActionListener(e -> goBackToLogisticCompanyMenu());
	}
	
	private void goBackToLogisticCompanyMenu() {
		view.dispose();
		LogisticCompanyMenuController w = new LogisticCompanyMenuController();
		w.initController();
	}

	private void addRClient() {
		
		Client newC = auto.rClient();
		DatabaseData.getDatabase().addToDatabase("Clients", newC.toString());
		
		JOptionPane.showMessageDialog(null, "Client successfully created!");
		
	}
	private void addRContainer() 
	{
		Container C = auto.rContainer();
		DatabaseData.getDatabase().addToDatabase("Containers", C.toString());
	
		JOptionPane.showMessageDialog(null, "Container successfully created!");
	}
	
	private void addRCJ() 
	{
		ContainerJourney cj = auto.rCJs();
		DatabaseData.getDatabase().addToDatabase("Journies", cj.toString());
	
		JOptionPane.showMessageDialog(null, "Journey successfully created!");
	}
	
}
