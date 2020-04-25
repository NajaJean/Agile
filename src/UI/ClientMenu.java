package UI;

import java.awt.*;
import javax.swing.*;

public class ClientMenu extends JFrame {
    private JMenuBar clientMenuBar;
    private JLabel welcomeLabel;
    
    JMenu bookingMenu = new JMenu ("Booking");
    JMenuItem book_containerItem = new JMenuItem ("Book container");
    JMenu my_containersMenu = new JMenu ("My containers");
    JMenu settingsMenu = new JMenu ("Settings");
    JMenuItem sign_outItem = new JMenuItem ("Sign out");
    JMenuItem configure_client_detailsItem = new JMenuItem ("Configure client details");
    
    public ClientMenu() {
        //construct preComponents
        bookingMenu.add (book_containerItem);
        settingsMenu.add (sign_outItem);
        settingsMenu.add (configure_client_detailsItem);

        //construct components
        clientMenuBar = new JMenuBar();
        clientMenuBar.add (bookingMenu);
        clientMenuBar.add (my_containersMenu);
        clientMenuBar.add (settingsMenu);
        welcomeLabel = new JLabel ("Welcome to ContainerManager");

        //adjust size and set layout
        setPreferredSize (new Dimension (681, 405));
        setLayout (null);

        //add components
        add (clientMenuBar);
        add (welcomeLabel);

        //set component bounds (only needed by Absolute Positioning)
        clientMenuBar.setBounds (0, 0, 400, 30);
        welcomeLabel.setBounds (80, 80, 280, 25);
        
        setTitle("Client Menu");
        setVisible(true);
        setBounds(10, 10, 600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    public JMenuItem getBook_containerItem() {
    	return book_containerItem;
    }
    
    public JMenuItem getSign_outItem() {
    	return sign_outItem;
    }
    
    public JMenuItem getConfigure_client_detailsItem() {
    	return configure_client_detailsItem;
    }
}