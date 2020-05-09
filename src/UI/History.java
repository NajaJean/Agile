package UI;

import core.*;

import java.awt.*;
import javax.swing.*;

import ExternalData.DatabaseData;


public class History extends JFrame {
	
	private ContainerJourney[] cJs;
	private JComboBox idBox;
    private JLabel showContLabel;
    private JLabel historyLabel;
    private JComboBox parametersBox;
    private JButton showButton;
    private JButton cancelButton;

	public History(Client c) {
		
		this.cJs = c.getClientsCJs(DatabaseData.getJournies());
    	String[] idBoxItems1 = new String[cJs.length];
    	for (int i=0; i < cJs.length; i++) {
    		idBoxItems1[i] = String.valueOf(cJs[i].getContaineronJourney().getContainerID());
    	}
    	
    	String[] parameters = {"Temperature", "Pressure", "Humidity"};
    	String[] parameterBoxItems = new String[parameters.length];
    	for (int i=0; i < parameters.length; i++) {
    		parameterBoxItems[i] = parameters[i];
    	}
  
        idBox = new JComboBox (idBoxItems1);
        showContLabel = new JLabel ("Show container ");
        historyLabel = new JLabel (" history.");
        parametersBox = new JComboBox (parameterBoxItems);
        showButton = new JButton ("Show history");
        cancelButton = new JButton ("Cancel");
        
        setPreferredSize (new Dimension (400, 100));
        setLayout (null);

        add (idBox);
        add (showContLabel);
        add (historyLabel);
        add (parametersBox);
        add (showButton);
        add (cancelButton);

        showContLabel.setBounds ( 13, 15, 100, 25);
        idBox.setBounds ( 125, 15, 35, 25);
        parametersBox.setBounds ( 180, 15, 100, 25);
        historyLabel.setBounds ( 290, 15, 100, 25);
    
        showButton.setBounds (5, 50, 130, 25);
        cancelButton.setBounds (130, 50, 130, 25);
        
        setTitle("Book Container Menu");
        setVisible(true);
        setBounds(10, 10, 400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

	public JButton getShowButton() {
		return showButton;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}
	public JComboBox getIdBox() {
		return idBox;
	}
	public JComboBox getParamBox() {
		return parametersBox;
	}
}
