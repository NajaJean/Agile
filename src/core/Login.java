package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
class LoginFrame extends JFrame implements ActionListener {
 
    Container container = getContentPane();
    JLabel userNameLabel = new JLabel ("User Name:");
    JLabel passwordLabel = new JLabel ("Password:");
    JButton loginButton = new JButton ("Login");
    JButton cancelButton = new JButton ("Cancel");
    JButton newClientButton = new JButton ("Create new Client");
    JButton newManagerButton = new JButton ("Create new Manager");;
    JTextField userTextField = new JTextField (5);
    JPasswordField passwordTextField = new JPasswordField (5);
 
    LoginFrame() {
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
        newClientButton.setBounds (30, 105, 150, 25);
        newManagerButton.setBounds (30, 135, 150, 25);
        userTextField.setBounds (100, 10, 100, 25);
        passwordTextField.setBounds (100, 35, 100, 25);
    }
 
    public void addComponentsToContainer() {
    	container.add(userNameLabel);
    	container.add(passwordLabel);
    	container.add(loginButton);
    	container.add(cancelButton);
    	container.add(newClientButton);
        container.add(newManagerButton);
    	container.add(userTextField);
        container.add(passwordTextField);
    }
 
    public void addActionEvent() {
        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);
        newClientButton.addActionListener(this);
        newManagerButton.addActionListener(this);
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordTextField.getText();
            if (userText.equalsIgnoreCase("corona") && pwdText.equalsIgnoreCase("1234")) {
                JOptionPane.showMessageDialog(this, "Login Successful");
            } 
            else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
 
        }
        //Coding Part of RESET button
        if (e.getSource() == cancelButton) {
            userTextField.setText("");
            passwordTextField.setText("");
        }
        if (e.getSource() == newClientButton) {
        	CreateUserFrame c = new CreateUserFrame();
        	c.setTitle("Create Client");
            c.setVisible(true);
            c.setBounds(10, 10, 370, 600);
            c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            c.setResizable(false);
        }
        if (e.getSource() == newManagerButton) {
        	CreateUserFrame c = new CreateUserFrame();
        	c.setTitle("Create Manager");
            c.setVisible(true);
            c.setBounds(10, 10, 370, 600);
            c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            c.setResizable(false);
        }
    }
 
}
 
public class Login {
    public static void main(String[] a) {
        LoginFrame frame = new LoginFrame();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
 
    }
 
}
