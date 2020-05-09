package UI;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
 
public class Login extends JFrame {
	String CorL;
    java.awt.Container container = getContentPane();
    JLabel userNameLabel = new JLabel ("User Name:");
    JLabel passwordLabel = new JLabel ("Password:");
    JButton loginButton = new JButton ("Login");
    JButton resetButton = new JButton ("Reset");
    JButton goBackButton = new JButton ("Go back");
    JTextField userTextField = new JTextField ();
    JPasswordField passwordTextField = new JPasswordField ();
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int frameWidth = 400;
    private int frameHeight = 200;
 
    public Login(String CorL) {
    	this.CorL = CorL;
        setLayout(null);
        setLocationAndSize();
        addComponentsToContainer();
        setTitle("Login Form");
        setVisible(true);
        
        setBounds(screenSize.width/2, screenSize.height/2, frameWidth, frameHeight);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    public JButton getLoginButton() {
    	return loginButton;
    }
    public JButton getResetButton() {
    	return resetButton;
    }
    public JButton getGoBackButton() {
    	return goBackButton;
    }
    public JTextField getUserTextField() {
    	return userTextField;
    }
    public JPasswordField getPasswordTextField() {
    	return passwordTextField;
    }
 
    public void setLocationAndSize() {
        userNameLabel.setBounds (frameWidth/6, 10, 100, 25);
        passwordLabel.setBounds (frameWidth/6, 35, 100, 25);
        loginButton.setBounds ((frameWidth/2) - (100/2)- (100/2), 70, 100, 25);
        resetButton.setBounds ((frameWidth/2) - (100/2) + (100/2), 70, 100, 25);
        goBackButton.setBounds ((frameWidth/2) - (100/2), 100, 100, 25);
        userTextField.setBounds ((frameWidth/2) - (100/2), 10, 100, 25);
        passwordTextField.setBounds ((frameWidth/2) - (100/2), 35, 100, 25);
    }
 
    public void addComponentsToContainer() {
    	container.add(userNameLabel);
    	container.add(passwordLabel);
    	container.add(loginButton);
    	container.add(resetButton);
    	container.add(goBackButton);
    	container.add(userTextField);
        container.add(passwordTextField);
    }
}

