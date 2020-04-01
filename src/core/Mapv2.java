package core;

import javax.swing.*;


class MapFramev2 extends JFrame{

	   
    //construct components
	
	java.awt.Container map = getContentPane();
	java.awt.Container container = getContentPane();
	JLabel shippingC[] = {new JLabel(new ImageIcon("F:\\projects\\java\\Agile\\src\\containersmall.png")),
						  new JLabel(new ImageIcon("F:\\projects\\java\\Agile\\src\\containersmall.png")),
						  new JLabel(new ImageIcon("F:\\projects\\java\\Agile\\src\\containersmall.png"))};
	//JLabel shippingC2 = new JLabel(new ImageIcon("F:\\projects\\java\\Agile\\src\\container.png"));
    JLabel worldmap = new JLabel (new ImageIcon("F:\\projects\\java\\Agile\\src\\map.jpg"));
    
    double startX[] = {752, 752, 752};
    double startY[] = {136, 136, 136};
    double endX[] = {315 - 25, 1448 - 25, 1520 - 25};
    double endY[] = {225 - 19, 248 - 19, 770 - 19};
    double tempX[] = {752, 752, 752};
    double tempY[] = {136, 136, 136};
    
 
    MapFramev2() {
    	worldmap.setBounds (0, 0, 1800, 900);
        for (int i = 0; i < shippingC.length; i++) {
        	setContainer( i);}
        addComponentsToContainer();
        addComponentsToMap();
    }
 
    
    public void moveContainer() throws InterruptedException {
    	Thread.sleep(1000);
    	int maxDist = -1;
    	
    	for (int i = 0; i < shippingC.length; i++) {
    		if (maxDist < (int)Math.max(Math.abs(endX[i] - startX[i]), Math.abs(endY[i] - startY[i]))) {
    			maxDist = (int)Math.max(Math.abs(endX[i] - startX[i]), Math.abs(endY[i] - startY[i]));
    		}
    	}
	    for (int j = 0; j < maxDist; j++) {
	    	if ((j > 5) && (Math.abs(tempX[0]-endX[0]) > 3)) { shippingC[0].setIcon(new ImageIcon("F:\\projects\\java\\Agile\\src\\ship.png"));}
	    	else {
	    		shippingC[0].setIcon(new ImageIcon("F:\\projects\\java\\Agile\\src\\containersmall.png"));
	    	}
	    	if ((j > 5) && (Math.abs(tempX[1]-endX[1]) > 3)) { shippingC[1].setIcon(new ImageIcon("F:\\projects\\java\\Agile\\src\\plane.png"));}
	    	else {
	    		shippingC[1].setIcon(new ImageIcon("F:\\projects\\java\\Agile\\src\\containersmall.png"));
	    	}
	    	if ((j > 5) && (Math.abs(tempX[2]-endX[2]) > 3)) { shippingC[2].setIcon(new ImageIcon("F:\\projects\\java\\Agile\\src\\plane.png"));}
	    	else {
	    		shippingC[2].setIcon(new ImageIcon("F:\\projects\\java\\Agile\\src\\containersmall.png"));
	    	}
	    	for (int i = 0; i < shippingC.length; i++) {
	    		
	    		if (tempX[i] != endX[i]) {
	    			
	    			if (endX[i] > startX[i]) {
	    				tempX[i] += (Math.max(Math.abs(endX[i] - startX[i]), Math.abs(endY[i] - startY[i])) / Math.max(Math.abs(endX[i] - startX[i]), Math.abs(endY[i] - startY[i])));
	    				}
	    			else {
	    				tempX[i] -= (Math.max(Math.abs(endX[i] - startX[i]), Math.abs(endY[i] - startY[i])) / Math.max(Math.abs(endX[i] - startX[i]), Math.abs(endY[i] - startY[i])));
	    			}
	    			if (endY[i] > startY[i]) {
	    				tempY[i] += (Math.min(Math.abs(endX[i] - startX[i]), Math.abs(endY[i] - startY[i])) / Math.max(Math.abs(endX[i] - startX[i]), Math.abs(endY[i] - startY[i])));
	    			}
	    			else {
	    				tempY[i] -= (Math.min(Math.abs(endX[i] - startX[i]), Math.abs(endY[i] - startY[i])) / Math.max(Math.abs(endX[i] - startX[i]), Math.abs(endY[i] - startY[i])));
	    			}
	    		}
	    		setContainer(i);
	    	}
	    	Thread.sleep(10);
	    }
    	
    	/*double difX = Math.abs(startX - endX);
    	double difY = Math.abs(startY - endY);
    	double tempY = startY;
		double tempX = startX;
		double incrementY = difY / difX;
		double incrementX = difX / difY;
		
    	if (difX > difY) {
    		for (int i = 0; i < difX; i++) {
    			tempX++;
    			tempY += incrementY;
    			refreshContainers(shippingC, tempX, tempY);
    			Thread.sleep(100);
    		}
    	}
    	
    	else {
    		for (int i = 0; i < difY; i++) {
    			tempY++;
    			tempX += incrementX;
    			refreshContainers(shippingC, tempX, tempY);
    			Thread.sleep(100);
    		}
    	}*/
   	}
    
    
    /*public void setLayoutManager() {
        background.setLayout(null);
     
    }*/
    //map offsets connected to picture: -200, -65 CPH:x: 752 - half the pic width y:136 - half the pic height
    
    /*public void refreshContainers(int i) {
    	setContainer(i);
    }*/
    
    //public int getX(int xIWant)
    //{
    //	return ((((xIWant+180-2) * 5) - 200)/*-25*/);//see "user manual" at getY
    //}
    
    //public int getY(int yIWant)
    //{
    //	return ((((90-yIWant + 6) * 5)-65)/*-19*/); //90 is the offset of the system: 0,0 left top instead of middle, + 6 dont really now *magic* *5 scaling -65 vertical offset between the middle of the picture and 0,0 on the map -19 so we move the middle of the container not the top left corner
    //}
    
    public void setContainer(int i) {
    	shippingC[i].setBounds ((int)tempX[i], (int)tempY[i], 50, 38);        
    }
 
    public void addComponentsToContainer() {
    	for (int i = 0; i < shippingC.length; i++) {
    		map.add(shippingC[i]);}
    }
    
    public void addComponentsToMap() {
        map.add(worldmap);
    }
    
    /*public void moveContainer(JLabel shippingC, double startX, double startY, double endX, double endY) throws InterruptedException {
    	double difX = Math.abs(startX - endX);
    	double difY = Math.abs(startY - endY);
    	double tempY = startY;
		double tempX = startX;
		double incrementY = difY / difX;
		double incrementX = difX / difY;
		
    	if (difX > difY) {
    		for (int i = 0; i < difX; i++) {
    			tempX++;
    			tempY += incrementY;
    			refreshContainers(shippingC, tempX, tempY);
    			Thread.sleep(100);
    		}
    	}
    	
    	else {
    		for (int i = 0; i < difY; i++) {
    			tempY++;
    			tempX += incrementX;
    			refreshContainers(shippingC, tempX, tempY);
    			Thread.sleep(100);
    		}
    	}
   	}*/  
}
 
public class Mapv2 {
    public static void main(String[] a) throws InterruptedException {
    	/*int x = 752;
    	int y = 136;
    	int goalx = 315;
    	int goaly = 225;
    	int goalx2 = 315;
    	int goaly2 = 225;*/
    	MapFramev2 frame = new MapFramev2();
    	frame.setTitle("Configure Client");
        frame.setVisible(true);
        frame.setBounds(0, 0, 1810, 950);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.moveContainer();
    }
}



