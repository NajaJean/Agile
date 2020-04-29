package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.Client;
import core.Container;
import core.Content;
import core.DatabaseData;
import core.Environment;
import core.Location;

public class ContainerTest {
	
	private Client cl1, cl2;
	private Environment e1, e2;
	private Content con1, con2;
	private Location loc1, loc2;
	private Environment[] toAssign;
	private int expectedID = 13;
	
	@Before 
	public void createContainers() {
		toAssign = DatabaseData.getEnvironments();
		cl1 = new Client("M", "1234", "Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		e1 = new Environment(5.3,1.1,0.85);
		con1 = new Content("Banana",new Environment(5.0,1.0,0.85), 0.1);
		double[] cphgps = {730.0, 188.0};
		loc1 = new Location("Copenhagen",cphgps);
		
		cl2 = new Client("N", "4321", "Naja","najasemail@gmail.com","Rådhuspladsen 100");
		e2 = new Environment(2.1,0.9,0.55);
		con2 = new Content("Apple",new Environment(2.0,0.8,0.5), 0.1);
		double[] sydgps = {1495.0, 810.0};
		loc2 = new Location("Sydney",sydgps);
	}
	
	@Test
	public void testContainer() {	
		Container c1 = new Container(cl1, con1,loc1);
		Container c2 = new Container(cl2, con2,loc2);
		
		assertEquals(expectedID, c1.getContainerID()); 
		assertEquals(expectedID +1 , c2.getContainerID()); 
		
		assertEquals(cl1,c1.getClientofContainer());
		assertEquals(toAssign[3],c1.getContainerEnvironment());
		assertEquals(con1,c1.getContainerContent());
		assertEquals(loc1,c1.getContainerLocation());
	}
	
	@Test
	public void testConfigureContainer() {
		Container c1 = new Container(cl1, con1,loc1);
		
		c1.setClientofContainer(cl2);
		assertEquals(cl2,c1.getClientofContainer());
		
		c1.setContainerEnvironment(toAssign[7]);
		assertEquals(toAssign[7],c1.getContainerEnvironment());
		
		c1.setContainerContent(con2);
		assertEquals(con2,c1.getContainerContent());
		
		c1.setContainerLocation(loc2);
		assertEquals(loc2,c1.getContainerLocation());
	}
	
	@Test
	public void testFindContainer() {
		Container c1 = new Container(cl1, con1,loc1);
		Container c2 = new Container(cl2, con2,loc2);
		Container[] cons = {c1,c2};
		
		assertEquals(c1, Container.findContainer(expectedID + 2, cons));
		assertEquals("'" + String.valueOf((expectedID + 2)) + "'" +", '12', '4', '17', '23'", c1.toString());
		
		
	}
}
