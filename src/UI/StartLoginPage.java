package UI;
import javax.swing.*;

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
}