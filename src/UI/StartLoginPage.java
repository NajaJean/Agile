package UI;
//import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

//import test.Database;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

public class StartLoginPage extends JFrame {
	java.awt.Container container = getContentPane();
    JLabel welcomeLabel = new JLabel ("Welcome to ContainerSystem");
    JButton lcLogin = new JButton ("Logistic Company Login");
    JButton cLogin = new JButton ("Client Login");
 
    public StartLoginPage() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setTitle("Choose Login");
        setVisible(true);
        setBounds(10, 10, 370, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
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
    
    public JButton getcLoginButton() {
    	return cLogin;
    }
    
    public JButton getlcLoginButton() {
    	return lcLogin;
    }
 
 /*
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == lcLogin) {
        	openLoginFor("Logistic Company");
        }
        if (e.getSource() == cLogin) {
        	openLoginFor("Client");
        }
    }*/
    /*
    public void openLoginFor(String CorL) {
    	Login frame = new Login(CorL);
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        dispose();
    }*/


}
/* 
public class StartLoginPage {
    public static void main(String[] a) {
    	StartLoginPageFrame frame = new StartLoginPageFrame();
        frame.setTitle("Choose Login");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}*/