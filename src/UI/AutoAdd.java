package UI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;


public class AutoAdd extends JFrame {
	java.awt.Container container = getContentPane();
    
    JButton addClient = new JButton ("Add Random Client");
    JButton addContainer = new JButton ("Add Random Container");
    
    JButton done = new JButton ("Done");
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int frameWidth = 400;
    private int frameHeight = 300;
 
    public AutoAdd() {
        setLayout(null);
        setLocationAndSize();
        addComponentsToContainer();
        setTitle("Random generator");
        setVisible(true);
        
        setBounds(screenSize.width/2, screenSize.height/2, frameWidth, frameHeight);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    public void setLocationAndSize() {    	
    	addClient.setBounds ((frameWidth/4) , 35, 200, 50);
    	addContainer.setBounds ((frameWidth/2) - (200/2), 85, 200, 50);
    	done.setBounds ((frameWidth/4) , 135, 200, 30);
    } 
 
    public void addComponentsToContainer() {
    	container.add(addClient);
    	container.add(addContainer);
        container.add(done);
    }
    
    public JButton getaddClientButton() {
    	return addClient;
    }
    
    public JButton getaddConrainerButton() {
    	return addContainer;
    }
    
    public JButton getDoneButton() {
    	return done;
    }
    
}