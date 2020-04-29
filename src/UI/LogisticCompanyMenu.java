package UI;
import java.awt.*;
import javax.swing.*;

import core.Calendar;

public class LogisticCompanyMenu extends JFrame {
    private JMenuBar menuBar;
    
    private JLabel date = new JLabel(Calendar.getSystemDate().toString());
    
    JMenu containerMenu = new JMenu ("Container");
    JMenuItem update_container_statusItem = new JMenuItem ("Update container status");
    JMenuItem register_new_containerItem = new JMenuItem ("Register new container");
    JMenu clientMenu = new JMenu ("Client");
    JMenuItem create_new_clientItem = new JMenuItem ("Create new client");
    JMenu settingsMenu = new JMenu ("Settings");
    JMenuItem sign_outItem = new JMenuItem ("Sign out");
    JMenuItem clientInfoItem = new JMenuItem ("Client list");
    JMenuItem clientByMailItem = new JMenuItem ("Find client by e-mail");
    JMenuItem containerInfoItem = new JMenuItem ("Container list");

    JMenuItem autoMenuItem = new JMenuItem("Auto generate");
    JMenu future = new JMenu ("Future");
    JMenuItem tomorrow = new JMenuItem("See Tomorrow");
    JMenuItem nextWeek = new JMenuItem("See Next Week");
    
    public LogisticCompanyMenu() {
    	
    	settingsMenu.add(autoMenuItem);
        containerMenu.add (update_container_statusItem);
        containerMenu.add (register_new_containerItem);
        containerMenu.add (containerInfoItem);
        clientMenu.add (create_new_clientItem);
        clientMenu.add (clientInfoItem);
        clientMenu.add(clientByMailItem);
        settingsMenu.add (sign_outItem);
        future.add(tomorrow);
        future.add(nextWeek);
        menuBar = new JMenuBar();
        menuBar.add (containerMenu);
        menuBar.add (clientMenu);
        menuBar.add (settingsMenu);
        menuBar.add (future);
        
        

        setPreferredSize (new Dimension (681, 404));
        setLayout (null);

        add (menuBar);
        add (date);
        menuBar.setBounds (0, 0, 235, 25);
        date.setBounds(600, 0, 200, 40);
        
        setTitle("Logistic Company");
        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    public JMenuItem getUpdateContainerStatusItem() {
    	return update_container_statusItem;
    }
    public JMenuItem getRegisterContainerItem() {
    	return register_new_containerItem;
    }
    public JMenuItem getCreateNewClientItem() {
    	return create_new_clientItem;
    }
    public JMenuItem getSignOutItem() {
    	return sign_outItem;
    }
    
    public JMenuItem getAutoMenuItem() {
    	return autoMenuItem;    	
    }
    public JMenuItem getClientInfoItem() {
    	return clientInfoItem;    	
    }
    public JMenuItem getClientByMailItem() {
    	return clientByMailItem;    	
    }
    public JMenuItem getContainerInfoItem() {
    	return containerInfoItem;    	
    }
    public JMenuItem getTomorrow() {
    	return tomorrow;
    }
    public JMenuItem getNextWeek() {
    	return nextWeek;
    }
    
    
    
}