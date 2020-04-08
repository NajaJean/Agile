package UI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class LogisticCompanyMenuFrame extends JPanel {
    private JMenuBar menuBar;

    public LogisticCompanyMenuFrame() {
    	//construct preComponents
        JMenu containerMenu = new JMenu ("Container");
        JMenuItem update_container_statusItem = new JMenuItem ("Update container status");
        containerMenu.add (update_container_statusItem);
        JMenuItem register_new_containerItem = new JMenuItem ("Register new container");
        containerMenu.add (register_new_containerItem);
        JMenu clientMenu = new JMenu ("Client");
        JMenuItem create_new_clientItem = new JMenuItem ("Create new client");
        clientMenu.add (create_new_clientItem);
        JMenu settingsMenu = new JMenu ("Settings");
        JMenuItem sign_outItem = new JMenuItem ("Sign out");
        settingsMenu.add (sign_outItem);

        //construct components
        menuBar = new JMenuBar();
        menuBar.add (containerMenu);
        menuBar.add (clientMenu);
        menuBar.add (settingsMenu);

        //adjust size and set layout
        setPreferredSize (new Dimension (681, 404));
        setLayout (null);

        //add components
        add (menuBar);

        //set component bounds (only needed by Absolute Positioning)
        menuBar.setBounds (0, 0, 235, 25);
        
        update_container_statusItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                openUpdateContainerStatusMenu();

            }

        });
        register_new_containerItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                openRegisterContainerMenu();
            }

        });
        create_new_clientItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                openCreateNewClientMenu();
            }
        });
        sign_outItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openLoginMenu();
            }
        });

    }

    public void openLoginMenu() {
    	StartLoginPageFrame frame = new StartLoginPageFrame();
        frame.setTitle("Choose Login");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
    
}

public class LogisticCompanyMenu {
	public static void main (String[] args) {
        JFrame frame = new JFrame("Logistic Company Menu");
        frame.setTitle("Logistic Company");
        frame.getContentPane().add (new LogisticCompanyMenuFrame());
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
