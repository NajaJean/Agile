package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//hello
 
class CreateUserFrame extends JFrame implements ActionListener {
    java.awt.Container container = getContentPane();
    JLabel insertUserNameLabel = new JLabel ("Insert User Name:");
	JLabel jcomp2 = new JLabel ("Insert Password:");
	JLabel insertNameLabel = new JLabel ("Insert Client Name:");
	JLabel insertEmailLabel = new JLabel ("Insert Email:");
	JLabel insertAddressLabel = new JLabel ("Insert Address:");
	JTextField userNameTextField = new JTextField (5);
	JTextField passwordTextField = new JTextField (5);
	JTextField nameTextField = new JTextField (5);
	JTextField emailTextField = new JTextField (5);
	JTextField addressTextField = new JTextField (5);
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
        insertNameLabel.setBounds (20, 80, 120, 25);
        insertEmailLabel.setBounds (20, 110, 120, 25);
        insertAddressLabel.setBounds (20, 140, 120, 25);
        userNameTextField.setBounds (140, 20, 100, 25);
        passwordTextField.setBounds (140, 50, 100, 25);
        nameTextField.setBounds (140, 80, 100, 25);
        emailTextField.setBounds (140, 110, 100, 25);
        addressTextField.setBounds (140, 140, 100, 25);
        createButton.setBounds (75, 175, 100, 25);
        returnButton.setBounds (60, 210, 135, 25);
    }
 
    public void addComponentsToContainer() {
    	container.add(insertUserNameLabel);
    	container.add(jcomp2);
    	container.add(insertNameLabel);
    	container.add(insertEmailLabel);
    	container.add(insertAddressLabel);
    	container.add(userNameTextField);
    	container.add(passwordTextField);
    	container.add(nameTextField);
    	container.add(emailTextField);
    	container.add(addressTextField);
    	container.add(createButton);
    	container.add(returnButton);
    }
 
    public void addActionEvent() {
    	createButton.addActionListener(this);
    	returnButton.addActionListener(this);
    }
 
    public boolean isValidUsernameAndPass(String userText, String pwdText) {
    	return (!(userText.equalsIgnoreCase("")) && !(pwdText.equalsIgnoreCase("")));
    }
    
    public boolean allInfoIsGiven(String nameText, String emailText, String addressText) {
    	return (!(nameText.equalsIgnoreCase("")) && !(emailText.equalsIgnoreCase("")) && !(addressText.equalsIgnoreCase("")));
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == createButton) {
            String userText;
            String pwdText;
            String nameText;
            String emailText;
            String addressText;
            userText = userNameTextField.getText();
            pwdText = passwordTextField.getText();
            nameText = nameTextField.getText();
            emailText = emailTextField.getText();
            addressText = addressTextField.getText();
            if (isValidUsernameAndPass(userText, pwdText)) {
                JOptionPane.showMessageDialog(this, "Successfully created new Client!");
                LoginFrame frame = new LoginFrame();
                frame.setTitle("Login Form");
                frame.setVisible(true);
                frame.setBounds(10, 10, 370, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                if (allInfoIsGiven(nameText, emailText, addressText)) { Client client = new Client(userText, pwdText, nameText, emailText, addressText);
                System.out.println(1);}
                else {Client client = new Client(userText, pwdText); System.out.println(2);}
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
