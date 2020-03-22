package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
class CreateUserFrame extends JFrame implements ActionListener {
    java.awt.Container container = getContentPane();
    JLabel insertUserNameLabel = new JLabel ("Insert User Name:");
	JLabel jcomp2 = new JLabel ("Insert Password:");
	JTextField userNameTextField = new JTextField (5);
	JTextField passwordTextField = new JTextField (5);
	JButton createButton = new JButton ("Create ");
	JButton returnButton = new JButton ("Return to Login");
 
	CreateUserFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
 
    }
 
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
    	insertUserNameLabel.setBounds (20, 20, 120, 25);
        jcomp2.setBounds (20, 50, 115, 25);
        userNameTextField.setBounds (140, 20, 100, 25);
        passwordTextField.setBounds (140, 50, 100, 25);
        createButton.setBounds (75, 85, 100, 25);
        returnButton.setBounds (60, 115, 135, 25);
    }
 
    public void addComponentsToContainer() {
    	container.add(insertUserNameLabel);
    	container.add(jcomp2);
    	container.add(userNameTextField);
    	container.add(passwordTextField);
    	container.add(createButton);
    	container.add(returnButton);
    }
 
    public void addActionEvent() {
    	createButton.addActionListener(this);
    	returnButton.addActionListener(this);
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == createButton) {
            String userText;
            String pwdText;
            userText = userNameTextField.getText();
            pwdText = passwordTextField.getText();
            if (!(userText.equalsIgnoreCase("")) && !(pwdText.equalsIgnoreCase(""))) {
                JOptionPane.showMessageDialog(this, "Successfully created new Client!");
                LoginFrame frame = new LoginFrame();
                frame.setTitle("Login Form");
                frame.setVisible(true);
                frame.setBounds(10, 10, 370, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
 
        }
        if (e.getSource() == returnButton) {
        	LoginFrame frame = new LoginFrame();
            frame.setTitle("Login Form");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
        }
    }
 
}
 
public class CreateUser {

    public static void main(String[] a) {
    	CreateUserFrame frame = new CreateUserFrame();
        frame.setTitle("Create Client");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
 
    }
 
}
