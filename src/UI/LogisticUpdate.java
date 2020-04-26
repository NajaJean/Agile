package UI;

import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.*;

import core.ContainerJourney;

public class LogisticUpdate extends JFrame {
	ContainerJourney[] Journies;
	private JLabel gpsLatitudeLabel;
    private JTextField gpsLatitudeField;
    private JLabel gpsLongitudeLabel;
    private JTextField gpsLongitudeField;
    private JButton cancelButton;
    private JButton saveButton;
    private JComboBox containerBox;
    private JLabel containerIDLabel;
    private JCheckBox arrivedCheckBox;
    
    public LogisticUpdate(ContainerJourney[] Journies) {
    	//construct preComponents
    	this.Journies=Journies;
    	
    	String[] containerBoxItems = new String [Journies.length]; 
    	for(int i = 0; i<Journies.length;i++) {
    		containerBoxItems[i] = Integer.toString(Journies[i].getContaineronJourney().getContainerID());
    	}
    	
    	Arrays.sort(containerBoxItems);
    	
        //construct components
        gpsLatitudeLabel = new JLabel ("GPS latitude:");
        gpsLatitudeField = new JTextField (5);
        gpsLongitudeLabel = new JLabel ("GPS longitude:");
        gpsLongitudeField = new JTextField (5);
        cancelButton = new JButton ("Cancel");
        saveButton = new JButton ("Save");
        containerBox = new JComboBox (containerBoxItems);
        containerIDLabel = new JLabel ("Choose Container ID:");
        arrivedCheckBox = new JCheckBox ("Arrived");

        //adjust size and set layout
        setPreferredSize (new Dimension (681, 416));
        setLayout (null);

        //add components
        add (gpsLatitudeLabel);
        add (gpsLatitudeField);
        add (gpsLongitudeLabel);
        add (gpsLongitudeField);
        add (cancelButton);
        add (saveButton);
        add (containerBox);
        add (containerIDLabel);
        add (arrivedCheckBox);

        //set component bounds (only needed by Absolute Positioning)
        gpsLatitudeLabel.setBounds (20, 45, 115, 25);
        gpsLatitudeField.setBounds (150, 45, 100, 25);
        gpsLongitudeLabel.setBounds (20, 90, 125, 25);
        gpsLongitudeField.setBounds (150, 90, 100, 25);
        cancelButton.setBounds (5, 195, 100, 25);
        saveButton.setBounds (155, 195, 100, 25);
        containerBox.setBounds (155, 15, 100, 25);
        containerIDLabel.setBounds (20, 15, 135, 25);
        arrivedCheckBox.setBounds (15, 135, 100, 25);
        
        setTitle("Update container menu");
        setVisible(true);
        setBounds(10, 10, 600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    
    }
    
    public JTextField getgpsLatitudeField() {
    	return gpsLatitudeField;
    }
    
    public JTextField getgpsLongitudeField() {
    	return gpsLongitudeField;
    }
    
    
    public JButton getcancelButton() {
    	return cancelButton;
    }
    public JButton getsaveButton() {
    	return saveButton;
    }
    public JComboBox getcontainerBox() {
    	return containerBox;
    }
    
    public JCheckBox getarrivedCheckBox() {
    	return arrivedCheckBox;
    }	

}
