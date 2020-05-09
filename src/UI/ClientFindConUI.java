package UI;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import core.Container;
import core.Content;;

public class ClientFindConUI extends JFrame {
	String[] listItems;
	Content[] content;
	java.awt.Container container = getContentPane();
	JLabel contentLabel = new JLabel ("Enter content of container(s) you want to find:");
	JTextField contentField = new JTextField();
	JLabel conLabel = new JLabel("My containers");
	JList<String> list = new JList();
	JScrollPane listScroller = new JScrollPane();
	JButton findContainerButton = new JButton ("Find container(s)");
	JButton goBackButton = new JButton ("Go back");
	
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int frameWidth = 750;
    private int frameHeight = 413;
    
    public ClientFindConUI() {
	    setLayout(null);
	    setLocationAndSize();
	    addComponentsToContainer();
	    setTitle("Find containers by content");
	    setVisible(true);
	
	    setBounds(screenSize.width/2, screenSize.height/2, frameWidth, frameHeight);
	    setLocationRelativeTo(null);
        listScroller.setPreferredSize(new Dimension(250, 80)); 
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
    	contentLabel.setBounds (frameWidth/14, 10, 260, 25);
    	contentField.setBounds (frameWidth/2 - (100/2), 10, 150, 25);
    	conLabel.setBounds (frameWidth/14, 45, 310, 25);
    	list.setBounds(frameWidth/14, 75, 610, 150);
    	listScroller.setBounds (frameWidth/14, 75, 610, 150);
    	findContainerButton.setBounds(frameWidth/6, 250, 180, 25);
    	goBackButton.setBounds (frameWidth/2 - (100/40), 250, 180, 25);
    }
    
    public void addComponentsToContainer() {
    	container.add(contentLabel);
    	container.add(contentField);
    	container.add(conLabel);
    	container.add(list);
    	container.add(listScroller);
    	container.add(findContainerButton);
    	container.add(goBackButton);
    }
    
    public void containerList(String content, Container[] containers) {
    	conLabel.setText("My containers with content " + content);
    	
    	listItems = new String [containers.length];
		for (int i = 0; i< containers.length;i++) {
			listItems[i] = "ContainerID: " + containers[i].getContainerID() + ".  Temperature: " + containers[i].getContainerEnvironment().getTemp() 
					+ " degrees,  pressure: " + containers[i].getContainerEnvironment().getPressure() + "atm,  humidity: " + containers[i].getContainerEnvironment().getHumidity()
					+ "%.  Content: " + containers[i].getContainerContent().getName() + ".  Location: " + containers[i].getContainerLocation().getLocationName();
		}
        
		list = new JList(listItems);
		listScroller.setAlignmentY(LEFT_ALIGNMENT);
		listScroller.setViewportView(list);

    }
  
}
