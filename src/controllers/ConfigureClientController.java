package controllers;

import javax.swing.JOptionPane;

import ExternalData.DatabaseData;
import UI.ConfigureClient;
import core.Client;
import core.NotifyObject;

public class ConfigureClientController {
	private ConfigureClient view; 
	NotifyObject response;
	Client c;
	
	ConfigureClientController(Client c) {
		this.c = c;
		view = new ConfigureClient(c);
	}
	
	public void initController() {
		view.getDoneButton().addActionListener(e -> saveChanges());
		view.getReturnButton().addActionListener(e -> goToClientMenu());
	}
	
	public void goToClientMenu() {
		ClientMenuController cm = new ClientMenuController(c);
		view.dispose();
		cm.initController();
	}
	
	enum Data{	
		UserName{ int num() {return 0;}},
		Password{ int num() {return 1;}},
		Name{ int num() {return 2;}},
		Email{ int num() {return 3;}},
		Address{ int num() {return 4;}};
	
		abstract int num();
	}
	
	public void saveChanges() {
		String[] oldInfo = new String[5];
		oldInfo[0] = c.getUsername();
		oldInfo[1] = c.getPassword();
		oldInfo[2] = c.getName();
		oldInfo[3] = c.getEmail();
		oldInfo[4] = c.getAddress();
		
		String[] newInfo = new String[5];
		for (int i=0; i<5; i++) {
			newInfo[i] = view.getNewTextField()[i].getText();
		}
		
		for (int i = 0; i < 5; i++) {
        	if (newInfo[i].equals("")) {
        		newInfo[i] = oldInfo[i];
        	}
        }
		
		c.setUserName(newInfo[Data.UserName.num()]);
        c.setPassword(newInfo[Data.Password.num()]);
        c.setName(newInfo[Data.Name.num()]);
        c.setEmail(newInfo[Data.Email.num()]);
        c.setAddress(newInfo[Data.Address.num()]);
		
		response = c.configure();
		JOptionPane.showMessageDialog(null, response.getNotifyMessage());
		
		JOptionPane.showMessageDialog(null, "New User Name: " + c.getUsername() + " \n" + 
											"New Password: " + c.getPassword() + " \n" +
											"New Name: " + c.getName() + " \n" +
											"New Email: " + c.getEmail() + " \n" +
											"New Address: " + c.getAddress());
		
		DatabaseData.getDatabase().updateDatabase("Clients", "Username",c.getUsername(),Integer.toString(c.getID()));
		DatabaseData.getDatabase().updateDatabase("Clients", "Password",c.getPassword(),Integer.toString(c.getID()));
		DatabaseData.getDatabase().updateDatabase("Clients", "Name",c.getName(),Integer.toString(c.getID()));
		DatabaseData.getDatabase().updateDatabase("Clients", "Email",c.getEmail(),Integer.toString(c.getID()));
		DatabaseData.getDatabase().updateDatabase("Clients", "Address",c.getAddress(),Integer.toString(c.getID()));
		
		ClientMenuController cm = new ClientMenuController(c);
		view.dispose();
		cm.initController();
	}
}
