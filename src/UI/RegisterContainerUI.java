package UI;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RegisterContainerUI extends JFrame {
	String loc;
    java.awt.Container container = getContentPane();
	JLabel locationContainerLabel = new JLabel ("What is the location of the container:");
	JTextField locationContainerTextField = new JTextField ();
	JLabel tempLabel = new JLabel ("Insert the temperature of the container:");
	JTextField tempTextField = new JTextField ();
	JLabel presLabel = new JLabel ("Insert the pressure of the container:");
	JTextField presTextField = new JTextField ();
	JLabel humLabel = new JLabel ("Insert the humidity of the container:");
	JTextField humTextField = new JTextField ();
	JButton regContainerButton = new JButton ("Register Container");
	JButton goBackButton = new JButton ("Go back");
	
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int frameWidth = 400;
    private int frameHeight = 200;
    
    public RegisterContainerUI(String loc) {
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
    public JTextField getLocationContainerTextField() {
    	return locationContainerTextField;
    }
    public JTextField getTempTextField() {
    	return tempTextField;
    }
    public JTextField getPresTextField() {
    	return presTextField;
    }
    public JTextField getHumTextField() {
    	return humTextField;
    }
    
    public void setLocationAndSize() {
    	locationContainerLabel.setBounds (frameWidth/6, 10, 100, 25);
    	locationContainerTextField.setBounds ((frameWidth/2) - (100/2), 10, 100, 25);
    	tempLabel.setBounds (frameWidth/6, 35, 100, 25);
    	tempTextField.setBounds ((frameWidth/2) - (100/2), 35, 100, 25);
    	presLabel.setBounds (frameWidth/6, 60, 100, 25);
    	presTextField.setBounds ((frameWidth/2) - (100/2), 60, 100, 25);
    	humLabel.setBounds (frameWidth/6, 85, 100, 25);
    	humTextField.setBounds ((frameWidth/2) - (100/2), 85, 100, 25);
        regContainerButton.setBounds ((frameWidth/2) - (100/2)- (100/2), 100, 100, 25);
        goBackButton.setBounds ((frameWidth/2) - (100/2), 125, 100, 25);
    }
    
    public void addComponentsToContainer() {
    	container.add(locationContainerLabel);
    	container.add(locationContainerTextField);
    	container.add(regContainerButton);
    	container.add(goBackButton);

    }
}
