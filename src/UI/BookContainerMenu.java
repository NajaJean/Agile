package UI;

import core.Content;
import core.Location;
import core.DatabaseData;

import java.awt.*;
import javax.swing.*;

public class BookContainerMenu extends JFrame {
	private JComboBox contentBox;
    private JLabel jcomp2;
    private JLabel jcomp3;
    private JComboBox startBox;
    private JLabel jcomp5;
    private JComboBox endBox;
    private JButton bookButton;
    private JButton cancelButton;

	public BookContainerMenu() {
    	//construct preComponents
    	Content[] Contents = DatabaseData.getContents();
    	String[] boxItems1 = new String[Contents.length];
    	for (int i=0; i<Contents.length; i++) {
    		boxItems1[i] = Contents[i].getName();
    	}
    	Location[] Locations = DatabaseData.getLocations();
    	String[] boxItems2 = new String[Locations.length];
    	for (int i=0; i<Locations.length; i++) {
    		boxItems2[i] = Locations[i].getLocationName();
    	}
  
        //construct components
        contentBox = new JComboBox (boxItems1);
        jcomp2 = new JLabel ("Select content:");
        jcomp3 = new JLabel ("Shipped from:");
        startBox = new JComboBox (boxItems2);
        jcomp5 = new JLabel ("Shipped to:");
        endBox = new JComboBox (boxItems2);
        bookButton = new JButton ("Book container");
        cancelButton = new JButton ("Cancel");

        //adjust size and set layout
        setPreferredSize (new Dimension (681, 413));
        setLayout (null);

        //add components
        add (contentBox);
        add (jcomp2);
        add (jcomp3);
        add (startBox);
        add (jcomp5);
        add (endBox);
        add (bookButton);
        add (cancelButton);

        //set component bounds (only needed by Absolute Positioning)
        contentBox.setBounds (110, 15, 145, 25);
        jcomp2.setBounds (15, 15, 100, 25);
        jcomp3.setBounds (15, 45, 100, 25);
        startBox.setBounds (110, 45, 145, 25);
        jcomp5.setBounds (15, 75, 100, 25);
        endBox.setBounds (110, 75, 145, 25);
        bookButton.setBounds (5, 120, 130, 25);
        cancelButton.setBounds (130, 120, 130, 25);
        
        setTitle("Book Container Menu");
        setVisible(true);
        setBounds(10, 10, 600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
	
	public JComboBox getcontentBox() {
		return contentBox;
	}
	public JComboBox getstartBox() {
		return startBox;
	}
	public JComboBox getendBox() {
		return endBox;
	}
	public JButton getBookButton() {
		return bookButton;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}

}
