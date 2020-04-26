package UI;
import java.awt.*;
import javax.swing.*;

public class LogisticCompanyMenu extends JFrame {
    private JMenuBar menuBar;
    JMenu containerMenu = new JMenu ("Container");
    JMenuItem update_container_statusItem = new JMenuItem ("Update container status");
    JMenuItem register_new_containerItem = new JMenuItem ("Register new container");
    JMenu clientMenu = new JMenu ("Client");
    JMenuItem create_new_clientItem = new JMenuItem ("Create new client");
    JMenu settingsMenu = new JMenu ("Settings");
    JMenuItem sign_outItem = new JMenuItem ("Sign out");

    JMenuItem autoMenuItem = new JMenuItem("Auto generate");
    
    public LogisticCompanyMenu() {
    	
    	settingsMenu.add(autoMenuItem);
        containerMenu.add (update_container_statusItem);
        containerMenu.add (register_new_containerItem);
        clientMenu.add (create_new_clientItem);
        settingsMenu.add (sign_outItem);
        menuBar = new JMenuBar();
        menuBar.add (containerMenu);
        menuBar.add (clientMenu);
        menuBar.add (settingsMenu);
        

        setPreferredSize (new Dimension (681, 404));
        setLayout (null);

        add (menuBar);
        menuBar.setBounds (0, 0, 235, 25);
        
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
}