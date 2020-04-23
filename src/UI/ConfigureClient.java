package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import core.Client;

public class ConfigureClient extends JFrame {
	
	java.awt.Container container = getContentPane();
	
	Client c;
	
	//temporary test client
	Client TestClient = new Client("corona", "1234", "labrat", "@", "asd");
	String[] TestC = new String[]{"corona", "1234", "labrat", "@", "asd"};
	
	enum Data{	
		UserName{ int num() {return 0;}},
		Password{ int num() {return 1;}},
		Name{ int num() {return 2;}},
		Email{ int num() {return 3;}},
		Address{ int num() {return 4;}};
	
		abstract int num();
	}
	
	JLabel[] CurrentData = new JLabel[]{ 
		new JLabel ("Current User Name:"),
		new JLabel ("Current Password:"),
		new JLabel ("Current Client Name:"),
    	new JLabel ("Current Email:"),
    	new JLabel ("Current Address:")};
    
	JLabel[] NewData = new JLabel[]{ 
			new JLabel ("New User Name:"),
			new JLabel ("New Password:"),
			new JLabel ("New Client Name:"),
	    	new JLabel ("New Email:"),
	    	new JLabel ("New Address:")};
    
	JLabel[] CurrentDataField = new JLabel[]{
		new JLabel (c.getUserName()),
		new JLabel (c.getPassword()),
		new JLabel (c.getName()),
		new JLabel (c.getEmail()),
		new JLabel (c.getAddress())};
	
	JTextField[] NewTextField = new JTextField[]{
			new JTextField (5),
			new JTextField (5),
			new JTextField (5),
			new JTextField (5),
			new JTextField (5)};
	
	JButton doneButton = new JButton ("Done");
	JButton returnButton = new JButton ("Return to Client Menu");
 
	public ConfigureClient(Client c) {
		this.c = c;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
//        addActionEvent();
        
        setTitle("Configure Client");
        setVisible(true);
        setBounds(10, 10, 600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
 
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
    	
    	int y_dim = 20;
    	for (int i = 0; i < 5; i++) {
    		
    		
    		CurrentData[i].setBounds (20, y_dim, 120, 25);
    		CurrentDataField[i].setBounds (160, y_dim, 100, 25);
    		NewData[i].setBounds (280, y_dim, 120, 25);
    		NewTextField[i].setBounds (400, y_dim, 100, 25);
    		y_dim += 30;
    	}
    	
    	doneButton.setBounds (20, 175, 100, 25);
        returnButton.setBounds (160, 175, 170, 25);
    }
 
    public void addComponentsToContainer() {
    	
    	for (int i = 0; i < 5; i++) {
    	container.add(CurrentData[i]);
    	container.add(CurrentDataField[i]);
    	container.add(NewData[i]);
    	container.add(NewTextField[i]);
    	}
    	
    	
    	container.add(doneButton);
    	container.add(returnButton);
    }
 /*
    public void addActionEvent() {
    	doneButton.addActionListener(this);
    	returnButton.addActionListener(this);
    }*/
    
    //not working properly, takes in the empty value for some reason :\
    public boolean isTheDataChanged(String checkThisData) {
    	return (!(checkThisData.equalsIgnoreCase("")));
    }
    
 
    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (e.getSource() == doneButton) {
            String[] input = new String[5];
            
            input[Data.UserName.num()] = NewTextField[Data.UserName.num()].getText();
            input[Data.Password.num()] = NewTextField[Data.Password.num()].getText();
            input[Data.Name.num()] = NewTextField[Data.Name.num()].getText();
            input[Data.Email.num()] = NewTextField[Data.Email.num()].getText();
            input[Data.Address.num()] = NewTextField[Data.Address.num()].getText();
            
            for (int i = 0; i < 5; i++) {
            	if (isTheDataChanged(input[i])) {
            		TestC[i] = input[i];
            	}
            }
            
            TestClient.setUserName(TestC[Data.UserName.num()]);
            TestClient.setPassword(TestC[Data.Password.num()]);
            TestClient.setName(TestC[Data.Name.num()]);
            TestClient.setEmail(TestC[Data.Email.num()]);
            TestClient.setAddress(TestC[Data.Address.num()]);
             
            
            JOptionPane.showMessageDialog(this, "Changes saved !");
            
            // feedback print
            JOptionPane.showMessageDialog(this, "New User Name: " + TestClient.getUserName() + " \n" + 
            									"New Password: " + TestClient.getPassword() + " \n" +
            									"New Name: " + TestClient.getName() + " \n" +
            									"New Email: " + TestClient.getEmail() + " \n" +
            									"New Address: " + TestClient.getAddress());
            
            //openClientMenu();
        } 
 
        if (e.getSource() == returnButton) {
        	//openClientMenu();
        }
    }
    /*
    public void openClientMenu() {
    	JFrame frame = new JFrame("Client Menu");
    	frame.setTitle("Client Menu");
    	frame.getContentPane().add (new ClientMenu());
    	frame.setVisible(true);
    	frame.pack();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setResizable(false);
    }*/
    
    public JButton getDoneButton() {
    	return doneButton;
    }
    public JButton getReturnButton() {
    	return returnButton;
    }
    public JTextField[] getNewTextField() {
    	return NewTextField;
    }
 
}
/*
public class ConfigureClient {

    public static void main(String[] a) {
    	ConfigureClientFrame frame = new ConfigureClientFrame();
        frame.setTitle("Configure Client");
        frame.setVisible(true);
        frame.setBounds(10, 10, 600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
 
    }
 
}*/
 

