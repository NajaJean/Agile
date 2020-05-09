package UI;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.data.category.DefaultCategoryDataset;

public class Graph2Pressure extends Graph {
	
	public Graph2Pressure(int ContainerID) {
		super("Pressure inside the container", ContainerID, 2, "Pressure [ATM]", "Pressure in the container");
		createDataset(getValuesFromFile(ContainerID));
	}
	
	private String[][] getValuesFromFile(int ContainerID) {
  		return super.getValuesFromFile(ContainerID, 2); 				
  	}
  	
  	private DefaultCategoryDataset createDataset(String[][] data) {
  		return super.createDataset("Pressure inside the container", data);
  	}
}
