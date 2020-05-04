package controllers;

import javax.swing.JOptionPane;

import UI.LogisticCreateClient;
import core.Client;
import core.DatabaseData;

public class LogisticCreateClientController {
	private LogisticCreateClient view;
	
	public LogisticCreateClientController() {
		view = new LogisticCreateClient();
	}
	
	public void initController() {
		view.getCreateButton().addActionListener(e -> createClient());
		view.getReturnButton().addActionListener(e -> goToLogisticCompanyMenu());
	}
	
	private void goToLogisticCompanyMenu() {
		LogisticCompanyMenuController lm = new LogisticCompanyMenuController();
		view.dispose();
		
		lm.initController();
	}
	
	private void createClient() {
		String userText = view.getUserNameTextField().getText();
		String pwdText = view.getPasswordTextField().getText();
		String nameText = view.getNameTextField().getText();
		String emailText = view.getEmailTextField().getText();
		String addressText = view.getAddressTextField().getText();
		
		if (!(userText.equals("")|pwdText.equals("")|nameText.equals("")|emailText.equals("")|addressText.equals(""))) {
			Client c = new Client(userText, pwdText, nameText, emailText, addressText);
			
			DatabaseData.getDatabase().addToDatabase(c);
			
			JOptionPane.showMessageDialog(null, c.createNewClient().getNotifyMessage());
			
			LogisticCompanyMenuController lm = new LogisticCompanyMenuController();
			view.dispose();
			
			lm.initController();
		}
		else {
			JOptionPane.showMessageDialog(null, "All fields have to be filled");
		}
		
	}	
}
