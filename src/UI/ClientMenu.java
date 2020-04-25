package UI;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import core.Client;
import core.ContainerJourney;
import core.DatabaseData;

public class ClientMenu extends JFrame {
	
	Client client;
	ContainerJourney[] cJs;
	Map map;
	
    private JMenuBar clientMenuBar;
    private JLabel welcomeLabel;
    private JLabel[] shippingLabels;
    private JLabel backgroundLabel;
    
    
    JMenu bookingMenu = new JMenu ("Booking");
    JMenuItem book_containerItem = new JMenuItem ("Book container");
    JMenu my_containersMenu = new JMenu ("My containers");
    JMenuItem my_containersMenuItem = new JMenuItem ("Show my containers"); 
    JMenu settingsMenu = new JMenu ("Settings");
    JMenuItem sign_outItem = new JMenuItem ("Sign out");
    JMenuItem configure_client_detailsItem = new JMenuItem ("Configure client details");
    
    
    
    public ClientMenu(Client client) { 
    	
    	this.client = client;
		this.cJs = DatabaseData.getJournies();
		map = new Map(client, cJs);
		
		backgroundLabel = map.getMapBackground();
		shippingLabels = map.getContainerLabels();
		
        //construct preComponents
        bookingMenu.add (book_containerItem);
        my_containersMenu.add(my_containersMenuItem);
        settingsMenu.add (sign_outItem);
        settingsMenu.add (configure_client_detailsItem);

        //construct components
        clientMenuBar = new JMenuBar();
        clientMenuBar.add (bookingMenu);
        clientMenuBar.add (my_containersMenu);
        clientMenuBar.add (settingsMenu);
        welcomeLabel = new JLabel ("Welcome to ContainerManager");
        
   
        //adjust size and set layout
        setPreferredSize (new Dimension (1810, 950));
        setLayout (null);

        //add components
        
        add (clientMenuBar);
                
        for (int i = 0; i < shippingLabels.length; i++) {
    		add(shippingLabels[i]);}
        
        //add (welcomeLabel);
        
        
        add (backgroundLabel);

        //set component bounds (only needed by Absolute Positioning)
        clientMenuBar.setBounds (0, 0, 400, 30);
        welcomeLabel.setBounds (80, 80, 280, 25);
        
        setTitle("Client Menu");
        setVisible(true);
        setBounds(10, 10, 1810, 950);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    public JMenuItem getBook_containerItem() {
    	return book_containerItem;
    }
    
    public JMenuItem getSign_outItem() {
    	return sign_outItem;
    }
    
    public JMenuItem getShowMyContainersItem() {
    	return my_containersMenuItem;
    }
    
    public JMenuItem getConfigure_client_detailsItem() {
    	return configure_client_detailsItem;
    }
    /*public static void main(String[] args) {
		ClientMenu menu = new ClientMenu(client);
	}*/
}