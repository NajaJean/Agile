package core;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

//import test.Database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StartLoginPageFrame extends JFrame implements ActionListener {
	java.awt.Container container = getContentPane();
    JLabel welcomeLabel = new JLabel ("Welcome to ContainerSystem");
    JButton lcLogin = new JButton ("Logistic Company Login");
    JButton cLogin = new JButton ("Client Login");

 
    StartLoginPageFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
 
    }
 
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
    	welcomeLabel.setBounds (45, 5, 195, 25);
        lcLogin.setBounds (35, 35, 200, 25);
        cLogin.setBounds (85, 65, 100, 25);
    }
 
    public void addComponentsToContainer() {
    	container.add(welcomeLabel);
    	container.add(lcLogin);
        container.add(cLogin);
    }
 
    public void addActionEvent() {
    	lcLogin.addActionListener(this);
    	cLogin.addActionListener(this);
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == lcLogin) {
        	openLoginFor("Logistic Company");
        }
        if (e.getSource() == cLogin) {
        	openLoginFor("Client");
        }
    }
    
    public void openLoginFor(String CorL) {
    	LoginFrame frame = new LoginFrame(CorL);
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
 
public class StartLoginPage {
    public static void main(String[] a) {
    	StartLoginPageFrame frame = new StartLoginPageFrame();
        frame.setTitle("Choose Login");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}