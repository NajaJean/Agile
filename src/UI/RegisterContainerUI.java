package UI;

import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import core.Location;

public class RegisterContainerUI extends JFrame {
	Location[] Locs;
	String[] locStrings;
    java.awt.Container container = getContentPane();
	JLabel locationContainerLabel = new JLabel ("Location of the container:");
	JComboBox locationContainerJComboBox;
	JLabel enviroInfoLabel = new JLabel ("");
	JLabel tempInfoLabel = new JLabel ("");
	JLabel presInfoLabel = new JLabel ("");
	JLabel humInfoLabel = new JLabel ("");
	
/*	JLabel tempLabel = new JLabel ("Insert the temperature of the container:");
	JTextField tempTextField = new JTextField ();
	JLabel presLabel = new JLabel ("Insert the pressure of the container:");
	JTextField presTextField = new JTextField ();
	JLabel humLabel = new JLabel ("Insert the humidity of the container:");
	JTextField humTextField = new JTextField ();*/
	
	JButton regContainerButton = new JButton ("Register Container"); 
	JButton goBackButton = new JButton ("Go back");
	
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int frameWidth = 400;
    private int frameHeight = 300;
    
    public void getLoca() {  
    	String[] LocationNames = new String[Locs.length]; 
    	for(int i = 0; i < Locs.length; i++) {
    		LocationNames[i] = Locs[i].getLocationName();	
    	}
    	locationContainerJComboBox = new JComboBox(LocationNames);
    }
    public RegisterContainerUI(Location[] Locs) {
    	this.Locs = Locs;
    	getLoca();
        setLayout(null);
        setLocationAndSize();
        addComponentsToContainer();
        setTitle("Register Container Form");
        setVisible(true);
        
        setBounds(screenSize.width/2, screenSize.height/2, frameWidth, frameHeight);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    public JButton getGoBackButton() {
    	return goBackButton;
    }
    public JComboBox getLocationContainerJComboBox() {
    	return locationContainerJComboBox;
    }
    public JButton getRegContainerButton() {
    	return regContainerButton;
  }
/*  public JTextField getTempTextField() {
    	return tempTextField;
    }
    public JTextField getPresTextField() {
    	return presTextField;
    }
    public JTextField getHumTextField() {
    	return humTextField;
    }*/
    
    
    public void setLocationAndSize() {
    	locationContainerLabel.setBounds (frameWidth/8, 10, 160, 25);
    	locationContainerJComboBox.setBounds (frameWidth/2, 10, 100, 25);
    	enviroInfoLabel.setBounds(frameWidth/8, 30, 200, 50);
    	tempInfoLabel.setBounds(frameWidth/8, 45, 200, 50);
    	presInfoLabel.setBounds(frameWidth/8, 60, 200, 50);
    	humInfoLabel.setBounds(frameWidth/8, 75, 200, 50);
/*    	tempLabel.setBounds (frameWidth/6, 35, 100, 25);
    	tempTextField.setBounds ((frameWidth/2) - (100/2), 35, 100, 25);
    	presLabel.setBounds (frameWidth/6, 60, 100, 25);
    	presTextField.setBounds ((frameWidth/2) - (100/2), 60, 100, 25);
    	humLabel.setBounds (frameWidth/6, 85, 100, 25);
    	humTextField.setBounds ((frameWidth/2) - (100/2), 85, 100, 25);*/
        regContainerButton.setBounds ((frameWidth/2) - (100/2), 120, 150, 25);
        goBackButton.setBounds ((frameWidth/2) - (100/2), 145, 150, 25);
    }
    
    public void addComponentsToContainer() {
    	container.add(locationContainerLabel);
    	container.add(locationContainerJComboBox);
    	container.add(enviroInfoLabel);
    	container.add(tempInfoLabel);
    	container.add(presInfoLabel);
    	container.add(humInfoLabel);
    	container.add(regContainerButton);
    	container.add(goBackButton);
    	
    }
    public void changeString(String selected, double temp, double pres, double hum) {
		enviroInfoLabel.setText("The Climate in " + selected + " today is");
		tempInfoLabel.setText("Temperature: " + temp + " degrees");
		presInfoLabel.setText("Pressure: " + pres + " atm");
		humInfoLabel.setText("Humidity: " + hum + "%");
    }
}
