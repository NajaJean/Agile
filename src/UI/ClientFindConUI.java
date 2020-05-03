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
	JLabel contentLabel = new JLabel ("Enter content of container(s):");
	JTextField contentField = new JTextField();
	JList<String> list = new JList<>();
	JScrollPane listScroller = new JScrollPane();
	JButton findContainerButton = new JButton ("Find container(s)");
	JButton goBackButton = new JButton ("Go back");
	
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int frameWidth = 681;
    private int frameHeight = 413;
    
    public ClientFindConUI() {
	    setLayout(null);
	    setLocationAndSize();
	    addComponentsToContainer();
	    setTitle("Find containers by content");
	    setVisible(true);
	
	    setBounds(screenSize.width/2, screenSize.height/2, frameWidth, frameHeight);
	    setLocationRelativeTo(null);
	    JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        listScroller.setAlignmentX(LEFT_ALIGNMENT);
        add (listScroller);
        listScroller.setBounds (frameWidth/10, 50, 490, 150);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false);
	    list.setVisible(false);
        
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
    	contentLabel.setBounds (frameWidth/10, 10, 200, 25);
    	contentField.setBounds (frameWidth/2 - (100/4), 10, 170, 25);
    	list.setBounds(frameWidth/4, 80, 250, 100);
    	findContainerButton.setBounds(frameWidth/11, 240, 180, 25);
    	goBackButton.setBounds (frameWidth/2, 240, 180, 25);
    }
    
    public void addComponentsToContainer() {
    	container.add(contentLabel);
    	container.add(contentField);
    	container.add(findContainerButton);
    	container.add(list);
    	container.add(goBackButton);
    }
    
    public void containerList(Container[] containers) {
		list.setVisible(true);
    	listItems = new String [containers.length];
		for (int i = 0; i< containers.length;i++) {
			listItems[i] = containers[i].toString();
		}
		list = new JList<String>(listItems);
		listScroller = new JScrollPane(list);
    }
  
}
