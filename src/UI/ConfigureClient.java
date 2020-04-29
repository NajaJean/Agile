package UI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		new JLabel ("Current Username:"),
		new JLabel ("Current Password:"),
		new JLabel ("Current Name:"),
    	new JLabel ("Current Email:"),
    	new JLabel ("Current Address:")};
    
	JLabel[] NewData = new JLabel[]{ 
			new JLabel ("New Username:"),
			new JLabel ("New Password:"),
			new JLabel ("New Name:"),
	    	new JLabel ("New Email:"),
	    	new JLabel ("New Address:")};
    
	JLabel[] CurrentDataField;
	
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
		
		CurrentDataField = new JLabel[]{
				new JLabel (c.getUsername()),
				new JLabel (c.getPassword()),
				new JLabel (c.getName()),
				new JLabel (c.getEmail()),
				new JLabel (c.getAddress())};
		
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
     
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

 

