package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.ArraySearch;
import core.Client;
import core.Container;
import core.Content;
import core.DatabaseData;
import core.Environment;
import core.Location;

public class ContainerTest {
	
	Client cl1, cl2;
	Environment e1, e2;
	Content con1, con2;
	Location loc1, loc2;
	Container[] containers = DatabaseData.getContainers();
	Environment[] toAssign = DatabaseData.getEnvironments();
	int expectedID = 1;
	ArraySearch search;
	
	@Before 
	public void createContainers() {
		search = new ArraySearch(new Container());
		Container.resetCount();
		
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
		Container c1 = new Container(cl1, con1, loc1);
		Container c2 = new Container(cl2, con2, loc2);
		
		assertEquals(expectedID, c1.getContainerID()); 
		assertEquals(expectedID +1, c2.getContainerID()); 
		
		assertEquals(cl1,c1.getClientofContainer());
		assertEquals(toAssign[3].toString(), c1.getContainerEnvironment().toString());
		assertEquals(con1, c1.getContainerContent());
		assertEquals(loc1, c1.getContainerLocation());
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
		
		assertEquals(c1, cons[search.findIDX(String.valueOf(cl1.getID()), cons)]); // Find from clientID
		
		assertEquals(c1, cons[search.findIDX(expectedID, cons)]);
		System.out.println(c1.getContainerID());
		assertEquals(1, c1.findFromStrings("1","1", containers));
		
	}
}
