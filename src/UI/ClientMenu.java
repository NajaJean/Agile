package UI;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import core.Calendar;
import core.Client;
import core.ContainerJourney;
import core.DatabaseData;

public class ClientMenu extends JFrame {
	
	Client client;
	ContainerJourney[] cJs;
	Map map;
	
    private JMenuBar clientMenuBar;
    private JLabel[] shippingLabels;
    private JLabel backgroundLabel;
    private JLabel date = new JLabel(Calendar.getSystemDate().toString());
   
    
    JMenu bookingMenu = new JMenu ("Booking");
    JMenuItem book_containerItem = new JMenuItem ("Book container");
    JMenu my_containersMenu = new JMenu ("My containers");
    JMenuItem my_containersMenuItem = new JMenuItem ("History");
    JMenuItem containerByContentMenuItem = new JMenuItem("Find container(s) by content");
    JMenu settingsMenu = new JMenu ("Settings");
    JMenuItem sign_outItem = new JMenuItem ("Sign out");
    JMenuItem configure_client_detailsItem = new JMenuItem ("Configure client details");
    JMenu future = new JMenu ("Future");
    JMenuItem tomorrow = new JMenuItem("See Tomorrow");
    JMenuItem nextWeek = new JMenuItem("See Next Week");
    
    
    public ClientMenu(Client client) {  	 	
    	
    	this.client = client;
		this.cJs = DatabaseData.getJournies();
		map = new Map(client, cJs);
		
		backgroundLabel = map.getMapBackground();
		shippingLabels = map.getContainerLabels();
		
        //construct preComponents
		bookingMenu.setFont(new Font("Sansa-Serif", Font.PLAIN, 20));
		future.setFont(new Font("Sansa-Serif", Font.PLAIN, 20));
		tomorrow.setFont(new Font("Sansa-Serif", Font.PLAIN, 20));
		nextWeek.setFont(new Font("Sansa-Serif", Font.PLAIN, 20));
		my_containersMenu.setFont(new Font("Sansa-Serif", Font.PLAIN, 20));
		settingsMenu.setFont(new Font("Sansa-Serif", Font.PLAIN, 20));
		book_containerItem.setFont(new Font("Sansa-Serif", Font.PLAIN, 20));
		my_containersMenuItem.setFont(new Font("Sansa-Serif", Font.PLAIN, 20));
		sign_outItem.setFont(new Font("Sansa-Serif", Font.PLAIN, 20));
		configure_client_detailsItem.setFont(new Font("Sansa-Serif", Font.PLAIN, 20));
        bookingMenu.add (book_containerItem);
        my_containersMenu.add(my_containersMenuItem);
        my_containersMenu.add(containerByContentMenuItem);
        settingsMenu.add (sign_outItem);
        settingsMenu.add (configure_client_detailsItem);
        future.add(tomorrow);
        future.add(nextWeek);
        date.setFont(new Font("Sansa-Serif", Font.BOLD, 25));

        //construct components
        clientMenuBar = new JMenuBar();
        clientMenuBar.add (bookingMenu);
        clientMenuBar.add (my_containersMenu);
        clientMenuBar.add (settingsMenu);
        clientMenuBar.add (future);
        
   
        //adjust size and set layout
       
        setPreferredSize (new Dimension (1810, 1010));
        setLayout (null);

        //add components
        
        add (clientMenuBar);
                
        for (int i = 0; i < shippingLabels.length; i++) {
    		add(shippingLabels[i]);}
        
        //add (welcomeLabel);
        
        add (date);
        add (backgroundLabel);

        //set component bounds (only needed by Absolute Positioning)
        clientMenuBar.setBounds (0, 0, 1810, 60);
        backgroundLabel.setBounds (0, 60, 1800, 900);
        date.setBounds(1650, 70, 200, 40);
        
       
        
        setTitle("Client Menu");
        setVisible(true);
        setBounds(10, 10, 1810, 1010);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    public JMenuItem getBook_containerItem() {
    	return book_containerItem;
    }
    
    public JMenuItem getSign_outItem() {
    	return sign_outItem;
    }
    
    public JMenuItem getHistoryItem() {
    	return my_containersMenuItem;
    }
    
    public JMenuItem getConfigure_client_detailsItem() {
    	return configure_client_detailsItem;
    }
    
    public JMenuItem getClientFindCon() {
    	return containerByContentMenuItem;
    }
    
    public JMenuItem getTomorrow() {
    	return tomorrow;
    }
    
    public JMenuItem getNextWeek() {
    	return nextWeek;
    }
    
    /*
    public static void main(String[] args) {
    	Client c = DatabaseData.getClients()[0];
		ClientMenu menu = new ClientMenu(c);
	}*/
}