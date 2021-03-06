package UI;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import ExternalData.DatabaseData;
import ExternalData.Logs;
import core.*;

public class Graph extends JFrame {
	
	private static final long serialVersionUID = 1L;
  	Container[] cS = DatabaseData.getContainers();
  	Logs L = new Logs(cS);

  	public Graph(String title, int ContainerID, int ColumnIndex, String YLabel, String datasetName) {
  		super(title);
  		 		
  		String[][] a = getValuesFromFile(ContainerID, ColumnIndex);
  		
	    DefaultCategoryDataset dataset = createDataset( datasetName, a );
	    
	    JFreeChart chart = ChartFactory.createLineChart(
	        "", 
	        "Date",
	        YLabel,
	        dataset
	        );
	    
	    CategoryPlot catPlot = chart.getCategoryPlot();
	
	    CategoryAxis axis = chart.getCategoryPlot().getDomainAxis();
	    axis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	    
	    ChartPanel panel = new ChartPanel(chart);
	    add (panel);
	    windowSettings();	  
  	}
  	
  	protected String[][] getValuesFromFile(int ContainerID, int ColumnIndex) {			
  		String inFile;
  		inFile = L.readFile("Container " + ContainerID);
  		
  		String lines[] = inFile.split("\\n");
  		String[] temp = new String[4];
  		String[][] data = new String[lines.length][];
  		String[][] returnData = new String[lines.length][];

  		for (int i = 0; i < lines.length; i++) 
  		{
  			data[i] = lines[i].split("\\t");
  			temp = data[i][2].split(",");
  			returnData[i] = new String[]{temp[ColumnIndex].substring(2, temp[ColumnIndex].length() - 1), data[i][8]};
  		}
  		  		
  		return returnData;
  	}
  	
  	
  	protected DefaultCategoryDataset createDataset(String xAxis, String[][] data) {
	    String series1 = xAxis;

	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    
	    for (int i = 0; i < data.length; i++) {
	    	dataset.addValue(Double.valueOf(data[i][0]), series1, data[i][1]);
	    }
	
	    return dataset;
  }
  
  	public void windowSettings() {
  		setAlwaysOnTop(true);
	    pack();
	    setSize(600, 400);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setVisible(true);
  	}
  	
  
}