import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientMenu extends JPanel {
    private JMenuBar clientMenuBar;
    private JLabel welcomeLabel;

    public ClientMenu() {
        //construct preComponents
        JMenu bookingMenu = new JMenu ("Booking");
        JMenuItem book_containerItem = new JMenuItem ("Book container");
        bookingMenu.add (book_containerItem);
        JMenu my_containersMenu = new JMenu ("My containers");
        JMenu settingsMenu = new JMenu ("Settings");
        JMenuItem sign_outItem = new JMenuItem ("Sign out");
        settingsMenu.add (sign_outItem);
        JMenuItem configure_client_detailsItem = new JMenuItem ("Configure client details");
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
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new ClientMenu());
        frame.pack();
        frame.setVisible (true);
    }
}