package UI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
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
//        addActionEvent();
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
 /*
    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        goBackButton.addActionListener(this);
    }
 */
 /*
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordTextField.getText();
            
            if (CorL=="Client") {
	            if (userText.equalsIgnoreCase("corona") && pwdText.equalsIgnoreCase("1234")) {
	                JOptionPane.showMessageDialog(this, "Login Successful");
	                openClientMenu();
	            } else {
	                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
	            }
            }
            if (CorL=="Logistic Company") {
	            if (userText.equalsIgnoreCase("corona") && pwdText.equalsIgnoreCase("1234")) {
	                JOptionPane.showMessageDialog(this, "Login Successful");
	                openLogisticCompanyMenu();
	            } else {
	                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
	            }
            }
        }
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordTextField.setText("");
        }
        if (e.getSource() == goBackButton) {
        	openLoginMenu();
        }
    }*/
    public void openClientMenu() {
    	JFrame frame = new JFrame("Client Menu");
        frame.setTitle("Client Menu");
        frame.getContentPane().add (new ClientMenuFrame());
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
    
    public void openLogisticCompanyMenu() {
    	JFrame frame = new JFrame("Logistic Company Menu");
        frame.setTitle("Logistic Company");
        frame.getContentPane().add (new LogisticCompanyMenuFrame());
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
    
    public void openLoginMenu() {
    	StartLoginPage frame = new StartLoginPage();
        frame.setTitle("Choose Login");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
 /*
public class Login {
    public static void main(String[] a) {
        LoginFrame frame = new LoginFrame("");
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}*/
