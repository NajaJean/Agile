package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ExternalData.DatabaseData;
import core.Client;

public class LogiticFindClientByMailUI extends JFrame {
	java.awt.Container container = getContentPane();
	JLabel emailLabel = new JLabel ("Enter e-mail of client:");
	JTextField emailField = new JTextField();
	JLabel clientLabel = new JLabel("");
	JButton findClientButton = new JButton ("Find client");
	JButton goBackButton = new JButton ("Go back");
	
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int frameWidth = 400;
    private int frameHeight = 230;
    
    public LogiticFindClientByMailUI() {
        setLayout(null);
        setLocationAndSize();
        addComponentsToContainer();
        setTitle("Find Client by e-mail");
        setVisible(true);

        setBounds(screenSize.width/2, screenSize.height/2, frameWidth, frameHeight);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    public JTextField getEmailField() {
    	return emailField;
    }
    
    public JButton getFindClientButton() {
    	return findClientButton;
    }
    
    public JButton getGoBackButton() {
    	return goBackButton;
    }
    
    public void setLocationAndSize() {
    	emailLabel.setBounds (frameWidth/10, 10, 140, 25);
    	emailField.setBounds (frameWidth/2 - (100/4), 10, 170, 25);
    	clientLabel.setBounds(frameWidth/8, 50, 250, 100);
    	findClientButton.setBounds(frameWidth/11, 155, 180, 25);
    	goBackButton.setBounds (frameWidth/2, 155, 180, 25);
    }
    
    public void addComponentsToContainer() {
    	container.add(emailLabel);
    	container.add(emailField);
    	container.add(findClientButton);
    	container.add(clientLabel);
    	container.add(goBackButton);
    }
    public void theClient(int ID, String name, String email, String adresse) {
    	clientLabel.setForeground(Color.BLUE);
    	clientLabel.setText("<html> Client ID: " + ID + "<br> Name: "+ name 
    			+"<br> E-mail: " + email + "<br> Adresse: " + adresse + "</html>");
    	
    }
    public void failm() {
    	clientLabel.setForeground(Color.RED);
    	clientLabel.setText("Can't find a client with the given e-mail");
    }
}
