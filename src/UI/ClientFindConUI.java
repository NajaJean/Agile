package UI;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import core.Client;
import core.Container;
import core.DatabaseData;

public class ClientFindConUI extends JFrame {
	java.awt.Container container = getContentPane();
	JLabel contentLabel = new JLabel ("Enter content of container(s):");
	JTextField contentField = new JTextField();
	JList list = new JList();
	JButton findContainerButton = new JButton ("Find container(s)");
	JButton goBackButton = new JButton ("Go back");
	
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int frameWidth = 681;
    private int frameHeight = 413;
    
    public ClientFindConUI() {
    	String[] listItems;
	    Container[] containers = DatabaseData.getContainers();
	    setLayout(null);
	    setLocationAndSize();
	    addComponentsToContainer();
	    setTitle("Find containers by content");
	    setVisible(true);
	
	    setBounds(screenSize.width/2, screenSize.height/2, frameWidth, frameHeight);
	    setLocationRelativeTo(null);
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false);
    }
    
    public JTextField getContentField() {
    	return contentField;
    }
    
    public JButton getFindContainerButton() {
    	return findContainerButton;
    }
    
    public JButton getGoBackButton() {
    	return goBackButton;
    }
    
    public void setLocationAndSize() {
    	contentLabel.setBounds (frameWidth/10, 10, 140, 25);
    	contentField.setBounds (frameWidth/2 - (100/4), 10, 170, 25);
    	list.setBounds(frameWidth/8, 50, 250, 100);
    	findContainerButton.setBounds(frameWidth/11, 155, 180, 25);
    	goBackButton.setBounds (frameWidth/2, 155, 180, 25);
    }
    
    public void addComponentsToContainer() {
    	container.add(contentLabel);
    	container.add(contentField);
    	container.add(findContainerButton);
    	container.add(list);
    	container.add(goBackButton);
    }
    
    public void containerList(Container[] cons) {
    	
    	
    }

}
