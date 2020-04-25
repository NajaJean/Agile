package UI;

import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RegisterContainerUI extends JFrame {
	String loc;
    java.awt.Container container = getContentPane();
	JLabel locationContainerLabel = new JLabel ("Location of the container:");
	JComboBox locationContainerJComboBox;
	JLabel enviroInfoLabel = new JLabel ("");
	JLabel tempInfoLabel = new JLabel ("");
	JLabel presInfoLabel = new JLabel ("");
	JLabel humInfoLabel = new JLabel ("");
	JButton regContainerButton = new JButton ("Register Container");
	JButton goBackButton = new JButton ("Go back");
	
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int frameWidth = 400;
    private int frameHeight = 300;
    
    public RegisterContainerUI() {
        setLayout(null);
        setLocationAndSize();
        addComponentsToContainer();
        setTitle("Register Container Form");
        setVisible(true);
        
        // Center JFrame in the middle of screen when it is initialized
        setBounds(screenSize.width/2, screenSize.height/2, frameWidth, frameHeight);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
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
    	enviroInfoLabel.setBounds(frameWidth/8, 30, 200, 50);
    	tempInfoLabel.setBounds(frameWidth/8, 45, 200, 50);
    	presInfoLabel.setBounds(frameWidth/8, 60, 200, 50);
    	humInfoLabel.setBounds(frameWidth/8, 75, 200, 50);
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
    	container.add(locationContainerJComboBox);
		enviroInfoLabel.setText("The Climate in " + selected + " today is");
		tempInfoLabel.setText("Temperature: " + temp + " degrees");
		presInfoLabel.setText("Pressure: " + pres + " atm");
		humInfoLabel.setText("Humidity: " + hum + "%");

    }
}
