package UI;

import core.Content;
import core.Location;

import java.awt.*;
import javax.swing.*;

import ExternalData.DatabaseData;

public class BookContainerMenu extends JFrame {
	
	private JComboBox contentBox;
    private JLabel jcomp2;
    private JLabel jcomp3;
    private JComboBox startBox;
    private JLabel jcomp5;
    private JComboBox endBox;
    private JButton bookButton;
    private JButton cancelButton;
    
    private JLabel startDate;
    private JComboBox startY;
    private JComboBox startM;
    private JComboBox startD;
    
    private JLabel endDate;
    private JComboBox endY;
    private JComboBox endM;
    private JComboBox endD;
    

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
    	
    	String[] boxItemsYears = {"2020", "2021", "2022", "2023", "2024", "2025"};
    	String[] boxItemsMonths = new String[12];
    	for (int i = 0; i < 12; i++) {
    		boxItemsMonths[i] = String.valueOf( i + 1);
    	}
    	
    	String[] boxItemsDays = new String[28];
    	for (int i = 0; i < 28; i++) {
    		boxItemsDays[i] = String.valueOf( i + 1);
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
        
        startDate = new JLabel("Ships on:");
        startY = new JComboBox(boxItemsYears);
        startM = new JComboBox(boxItemsMonths);
        startD = new JComboBox(boxItemsDays);
        
        endDate = new JLabel("Arrives by:");
        endY = new JComboBox(boxItemsYears);
        endM = new JComboBox(boxItemsMonths);
        endD = new JComboBox(boxItemsDays);

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
        
        add (startDate);
        add (startY);
        add (startM);
        add (startD);
        add (endDate);
        add (endY);
        add (endM);
        add (endD);
        
        add (bookButton);
        add (cancelButton);

        //set component bounds (only needed by Absolute Positioning)
        contentBox.setBounds (110, 15, 145, 25);
        jcomp2.setBounds (15, 15, 100, 25);
        jcomp3.setBounds (15, 45, 100, 25);
        startBox.setBounds (110, 45, 145, 25);
        jcomp5.setBounds (15, 75, 100, 25);
        endBox.setBounds (110, 75, 145, 25);
        
        startDate.setBounds (15, 115, 145, 25);
        startY.setBounds (110, 115, 70, 25);
        startM.setBounds (190, 115, 45, 25);
        startD.setBounds (245, 115, 45, 25);
        endDate.setBounds (15, 145, 100, 25);
        endY.setBounds (110, 145, 70, 25);
        endM.setBounds (190, 145, 45, 25);
        endD.setBounds (245, 145, 45, 25);
        
        bookButton.setBounds (5, 180, 130, 25);
        cancelButton.setBounds (130, 180, 130, 25);
        
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
	
	public JComboBox getStartYBox() {
		return startY;
	}
	public JComboBox getStartMBox() {
		return startM;
	}
	public JComboBox getStartDBox() {
		return startD;
	}
	
	public JComboBox getEndYBox() {
		return endY;
	}
	public JComboBox getEndMBox() {
		return endM;
	}
	public JComboBox getEndDBox() {
		return endD;
	}
	
	/*public static void main(String[] args) {
		
		BookContainerMenu b = new BookContainerMenu();
	}*/

}
