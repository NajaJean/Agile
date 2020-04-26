package UI;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import core.DatabaseData;
import core.Location;

public class RegisterContainerUI extends JFrame {
	String loc;
    java.awt.Container container = getContentPane();
	JLabel locationContainerLabel = new JLabel ("Location of the container:");
	JComboBox locationContainerJComboBox;
	JLabel enviroInfoLabel = new JLabel ("");
	JLabel tempInfoLabel = new JLabel ("");
	JLabel presInfoLabel = new JLabel ("");
	JLabel humInfoLabel = new JLabel ("");
	JButton loadClimateButton = new JButton ("See climate for location");
	JButton regContainerButton = new JButton ("Register Container");
	JButton goBackButton = new JButton ("Go back");
	
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int frameWidth = 400;
    private int frameHeight = 230;
    
    public RegisterContainerUI() {
	Location[] Locations = DatabaseData.getLocations();
    	String[] boxItems = new String[Locations.length];
    	for (int i=0; i < Locations.length; i++) {
    		boxItems[i] = Locations[i].getLocationName();
    	}
    	locationContainerJComboBox = new JComboBox (boxItems);
	    
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
    
    public JButton getLoadClimateButton() {
    	return loadClimateButton;
    }
    public JButton getRegContainerButton() {
    	return regContainerButton;
    }
    public JButton getGoBackButton() {
    	return goBackButton;
    }
    public JComboBox getLocationContainerJComboBox() {
    	return locationContainerJComboBox;
    }
    
    public void setLocationAndSize() {
    	locationContainerLabel.setBounds (frameWidth/8, 10, 160, 25);
    	locationContainerJComboBox.setBounds (frameWidth/2, 10, 100, 25);
    	enviroInfoLabel.setBounds(frameWidth/8, 30, 250, 50);
    	tempInfoLabel.setBounds(frameWidth/8, 45, 200, 50);
    	presInfoLabel.setBounds(frameWidth/8, 60, 200, 50);
    	humInfoLabel.setBounds(frameWidth/8, 75, 200, 50);
    	loadClimateButton.setBounds((frameWidth/10), 120, 170, 25);
        regContainerButton.setBounds ((frameWidth/2), 120, 150, 25);
        goBackButton.setBounds ((frameWidth/3), 145, 150, 25);
    }
    
    public void addComponentsToContainer() {
    	container.add(locationContainerLabel);
    	container.add(locationContainerJComboBox);
    	container.add(enviroInfoLabel);
    	container.add(tempInfoLabel);
    	container.add(presInfoLabel);
    	container.add(humInfoLabel);
    	container.add(loadClimateButton);
    	container.add(regContainerButton);
    	container.add(goBackButton);	
    }
    
    public void changeString(String selected, double temp, double pres, double hum) {
    	container.add(locationContainerJComboBox);
		enviroInfoLabel.setText("The Climate in " + selected + " today is");
		tempInfoLabel.setText("Temperature: " + temp + " degrees");
		presInfoLabel.setText("Pressure: " + pres + " atm");
		humInfoLabel.setText("Humidity: " + hum + "%");

    }
}
