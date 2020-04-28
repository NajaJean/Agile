package core;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graphs extends JFrame {
	
	public static void main(String[] args) {
		
		
		JFrame mygraph = new JFrame("PlotGraph v0.1");

		  final Vector<Double> range_t = new Vector<>(5); //get the range of t
		  //create a corresponding vectors of x and y based on values of t
		  final Vector<Double> range_x = new Vector<>(5);
		  final Vector<Double> range_y = new Vector<>(5);

		  
		  range_t.add(500.0);
		  range_t.add(400.0);
		  range_t.add(300.0);
		  range_t.add(200.0);
		  range_t.add(100.0);
		  
		  range_x.add(1000.1);
		  range_x.add(400.1);
		  range_x.add(3000.1);
		  range_x.add(200.1);
		  range_x.add(100.1);
		  
		  range_y.add(500.9);
		  range_y.add(400.9);
		  range_y.add(300.9);
		  range_y.add(200.9);
		  range_y.add(100.9);
		  //draw the graph to a JPanel, our graph is actually just a collection of points connecting 2 points
		  
		  mygraph.add(new JPanel() {


		 public void paintComponent(Graphics g) {
			 g.setColor(Color.WHITE);
			 g.fillRect(0, 0, getWidth(), getHeight());
		          super.paintComponent(g);
		          Graphics2D g2 = (Graphics2D) g;      
		          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		          g2.setColor(Color.BLUE );
		          g2.setStroke(new BasicStroke(1));
		          GeneralPath gpx = new GeneralPath();
		          GeneralPath gpy = new GeneralPath();
		          for (int i = 0; i < 3; i++) {
		            gpy.moveTo(range_t.get(i),range_y.get(i));
		            gpy.curveTo(range_t.get(i),range_y.get(i),range_t.get(i+1),range_y.get(i+1),range_t.get(i+2),range_y.get(i+2));
		         
		            g2.draw(gpx);
		            g2.draw(gpy);
		          }
		          g2.dispose(); 
		        }
		      });

		  mygraph.setBounds(125,25,650,600);
		  mygraph.setLocationRelativeTo(null);
		  mygraph.setDefaultCloseOperation(mygraph.EXIT_ON_CLOSE);
		  mygraph.setVisible(true);
	}
}
