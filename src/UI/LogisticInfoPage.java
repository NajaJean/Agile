package UI;

import java.awt.*;
import javax.swing.*;

import core.Client;
import core.Container;

public class LogisticInfoPage extends JFrame {
	private JList list;
    private JLabel jcomp2;
    private JButton returnButton;

    public LogisticInfoPage(String ClorCo, Client[] Clients, Container[] Containers) {
    	
    	String[] listItems;
    	if (ClorCo.equals("Client")) {
    		listItems = new String [Clients.length]; 
        	for(int i = 0; i<Clients.length;i++) {
        		listItems[i] = "ID: "+Clients[i].getID()+
        					   ", Name: "+Clients[i].getName()+
        					   ", Address: "+Clients[i].getAddress()+
        					   ", Email: "+Clients[i].getEmail();
        	}
    	}
    	else {
    		listItems = new String [Containers.length]; 
        	for(int i = 0; i<Containers.length;i++) {
        		listItems[i] = "ID: "+Containers[i].getContainerID();
        		if (!(Containers[i].getClientofContainer()==null)) {
        			listItems[i]=listItems[i]+", Assigned to client: "+Integer.toString(Containers[i].getClientofContainer().getID())
        									 +", Content: "+Containers[i].getContainerContent();
        		}
        		else {
        			listItems[i]=listItems[i]+", Container has not been booked by a client";
        		}
        	}
    	}
    	
    	list = new JList (listItems);
        jcomp2 = new JLabel ("My "+ClorCo.toLowerCase()+"s:");
        returnButton = new JButton ("Return to menu");

        setPreferredSize (new Dimension (681, 413));
        setLayout (null);

        add (list);
        add (jcomp2);
        add (returnButton);

        list.setBounds (15, 35, 490, 150);
        jcomp2.setBounds (15, 10, 100, 25);
        returnButton.setBounds (10, 195, 135, 25);
        
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        listScroller.setAlignmentX(LEFT_ALIGNMENT);
        add (listScroller);
        listScroller.setBounds (15, 35, 490, 150);
        
        setTitle("My "+ClorCo+"s");
        setVisible(true);
        setBounds(10, 10, 600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    public JButton getreturnButton() {
    	return returnButton;
    }
}