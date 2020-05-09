package UI;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.data.category.DefaultCategoryDataset;

public class Graph1Temp extends Graph {
	
	public Graph1Temp(int ContainerID) 
	{
		super("Temperature inside the container", ContainerID, 1, "Temperature [\u00B0C]", "Temperature in the container");
		createDataset(getValuesFromFile(ContainerID));	
	}
	
	private String[][] getValuesFromFile(int ContainerID) {
  		return super.getValuesFromFile(ContainerID, 1); 				
  	}
  	
  	
  	private DefaultCategoryDataset createDataset(String[][] data) {
  		return super.createDataset("Temperature inside the container", data);
  	}
}
