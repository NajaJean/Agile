package UI;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.data.category.DefaultCategoryDataset;

 public class Graph3Humidity extends Graph {
	
	public Graph3Humidity(int ContainerID) 	{
		super("Humidity inside the container", ContainerID, 3, "Humidity [%]", "Humidity in the container");
		createDataset(getValuesFromFile(ContainerID));
	}
	
	private String[][] getValuesFromFile(int ContainerID) {
  		return super.getValuesFromFile(ContainerID, 3); 				
  	}
  	
  	
  	private DefaultCategoryDataset createDataset(String[][] data) {
  		return super.createDataset("Humidity inside the container", data);
  	}
}
