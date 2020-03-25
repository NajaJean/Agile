package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
class LoginFrame extends JFrame implements ActionListener {
	String CorL;
    java.awt.Container container = getContentPane();
    JLabel userNameLabel = new JLabel ("User Name:");
    JLabel passwordLabel = new JLabel ("Password:");
    JButton loginButton = new JButton ("Login");
    JButton cancelButton = new JButton ("Cancel");
    JTextField userTextField = new JTextField (5);
    JPasswordField passwordTextField = new JPasswordField (5);
 
    LoginFrame(String CorL) {
    	this.CorL = CorL;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
 
    }
 
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
        userNameLabel.setBounds (15, 10, 100, 25);
        passwordLabel.setBounds (15, 35, 100, 25);
        loginButton.setBounds (10, 70, 100, 25);
        cancelButton.setBounds (105, 70, 100, 25);
        userTextField.setBounds (100, 10, 100, 25);
        passwordTextField.setBounds (100, 35, 100, 25);
    }
 
    public void addComponentsToContainer() {
    	container.add(userNameLabel);
    	container.add(passwordLabel);
    	container.add(loginButton);
    	container.add(cancelButton);
    	container.add(userTextField);
        container.add(passwordTextField);
    }
 
    public void addActionEvent() {
        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }
 
 
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
        if (e.getSource() == cancelButton) {
            userTextField.setText("");
            passwordTextField.setText("");
        }
    }
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
}
 
public class Login {
    public static void main(String[] a) {
        LoginFrame frame = new LoginFrame("");
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
