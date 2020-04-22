package UI;

import javax.swing.*;
 
public class Login extends JFrame {
	String CorL;
    java.awt.Container container = getContentPane();
    JLabel userNameLabel = new JLabel ("User Name:");
    JLabel passwordLabel = new JLabel ("Password:");
    JButton loginButton = new JButton ("Login");
    JButton resetButton = new JButton ("Reset");
    JButton goBackButton = new JButton ("Go back");
    JTextField userTextField = new JTextField (5);
    JPasswordField passwordTextField = new JPasswordField (5);
 
    public Login(String CorL) {
    	this.CorL = CorL;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setTitle("Login Form");
        setVisible(true);
        setBounds(10, 10, 370, 600);
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
 
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
        userNameLabel.setBounds (15, 10, 100, 25);
        passwordLabel.setBounds (15, 35, 100, 25);
        loginButton.setBounds (10, 70, 100, 25);
        resetButton.setBounds (105, 70, 100, 25);
        goBackButton.setBounds (57, 100, 100, 25);
        userTextField.setBounds (100, 10, 100, 25);
        passwordTextField.setBounds (100, 35, 100, 25);
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

