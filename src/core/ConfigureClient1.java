package core;
<<<<<<< HEAD:core/ConfigureClient1.java
<<<<<<< HEAD:core/ConfigureClient1.java

=======
>>>>>>> ca0ece5598ae99bed77cb2853ca768c6a7bb23df:src/core/ConfigureClient1.java
=======
>>>>>>> a01a6b200e6707b3f9412dfaef98a530865bc0ea:src/core/ConfigureClient1.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ConfigureClientFrame extends JFrame implements ActionListener {
	   
    //construct components
	java.awt.Container container = getContentPane();
    JLabel confLabel = new JLabel ("Configure Client details:");
    JLabel fstNameLabel = new JLabel ("First name:");
    JTextField fstNameTextField = new JTextField (5);
    JLabel lstNameLabel = new JLabel ("Last name:");
    JTextField lstNameTextField = new JTextField (5);
    JLabel addressLabel = new JLabel ("Address:");
    JTextField addressTextField = new JTextField (5);
    JLabel emailLabel = new JLabel ("E-mail:");
    JTextField emailTextField = new JTextField (5);
    JLabel refPersLabel = new JLabel ("Reference Person:");
    JTextField refPersTextField = new JTextField (5);
    JButton saveButton = new JButton ("Save");
    JButton returnButton = new JButton ("Return");
    JLabel passwordLabel = new JLabel ("Password:");
    JTextField passwordTextField = new JTextField (5);
 
    ConfigureClientFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
 
    }
 
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
    	confLabel.setBounds (15, 10, 175, 25);
        fstNameLabel.setBounds (15, 40, 100, 25);
        fstNameTextField.setBounds (90, 40, 145, 25);
        lstNameLabel.setBounds (15, 65, 100, 25);
        lstNameTextField.setBounds (90, 65, 145, 25);
        addressLabel.setBounds (15, 90, 100, 25);
        addressTextField.setBounds (91, 90, 145, 25);
        emailLabel.setBounds (15, 115, 100, 25);
        emailTextField.setBounds (90, 115, 145, 25);
        refPersLabel.setBounds (15, 140, 125, 25);
        refPersTextField.setBounds (130, 140, 145, 25);
        saveButton.setBounds (25, 215, 100, 25);
        returnButton.setBounds (125, 215, 100, 25);
        passwordLabel.setBounds (15, 165, 100, 25);
        passwordTextField.setBounds (90, 165, 145, 25);
    }
 
    public void addComponentsToContainer() {
        container.add(confLabel);
        container.add(fstNameLabel);
        container.add(fstNameTextField);
        container.add(lstNameLabel);
        container.add(lstNameTextField);
        container.add(addressLabel);
        container.add(addressTextField);
        container.add(emailLabel);
        container.add(emailTextField);
        container.add(refPersLabel);
        container.add(refPersTextField);
        container.add(saveButton);
        container.add(returnButton);
        container.add(passwordLabel);
        container.add(passwordTextField);
    }
 
    public void addActionEvent() {
    	saveButton.addActionListener(this);
    	returnButton.addActionListener(this);
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == saveButton) {
            String fstNameText;
            String lstNameText;
            String addressText;
            String emailText;
            String refPersText;
            String passwordText;

            fstNameText = fstNameTextField.getText();
            lstNameText = lstNameTextField.getText();
            addressText = addressTextField.getText();
            emailText = emailTextField.getText();
            refPersText = refPersTextField.getText();
            passwordText = passwordTextField.getText();
            if (fstNameText.equalsIgnoreCase("") ||
            	lstNameText.equalsIgnoreCase("") ||
            	addressText.equalsIgnoreCase("") ||
            	emailText.equalsIgnoreCase("") ||
            	refPersText.equalsIgnoreCase("") ||
            	passwordText.equalsIgnoreCase("")
            	) {
                JOptionPane.showMessageDialog(this, "Could not save, all fields has to be filled");
            } else {
                JOptionPane.showMessageDialog(this, "Changes to client has been saved");
                openClientMenu();
            }
 
        }
        if (e.getSource() == returnButton) {
        	openClientMenu();
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
 
}
 
public class ConfigureClient1 {
    public static void main(String[] a) {
    	ConfigureClientFrame frame = new ConfigureClientFrame();
        frame.setTitle("Configure Client");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}