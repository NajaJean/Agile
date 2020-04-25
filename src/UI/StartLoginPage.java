package UI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;

public class StartLoginPage extends JFrame {
	java.awt.Container container = getContentPane();
    JLabel welcomeLabel = new JLabel ("Welcome to ContainerSystem");
    JButton lcLogin = new JButton ("Logistic Company Login");
    JButton cLogin = new JButton ("Client Login");
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int frameWidth = 400;
    private int frameHeight = 200;
 
    public StartLoginPage() {
        setLayout(null);
        setLocationAndSize();
        addComponentsToContainer();
        setTitle("Choose Login");
        setVisible(true);
        
        // Center JFrame in the middle of screen when it is initialized
        setBounds(screenSize.width/2, screenSize.height/2, frameWidth, frameHeight);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    public void setLocationAndSize() {
    	// Set all JComponents into the center of the JFrame
    	welcomeLabel.setFont(new Font("Serif", Font.BOLD, 16));
    	welcomeLabel.setBounds ((frameWidth/2) - (200/2), 10, 200, 25);
        lcLogin.setBounds ((frameWidth/2) - (200/2), 35, 200, 50);
        cLogin.setBounds ((frameWidth/2) - (200/2), 85, 200, 50);
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
}