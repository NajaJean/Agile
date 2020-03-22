package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.Client;
import core.Container;
import core.Content;
import core.Environment;

public class ContainerTest {
	
	private Client cl1, cl2;
	private Environment e1, e2;
	private Content con1, con2;
	
	@Before 
	public void createContainers() {
		cl1 = new Client("M", "1234", "Mathilde","mathildesemail@gmail.com","Anker Egelundsvej 1");
		e1 = new Environment(5.3,1.1,0.85);
		con1 = new Content("Banana",new Environment(5.0,1.0,0.85), 0.1);
		
		cl2 = new Client("N", "4321", "Naja","najasemail@gmail.com","Rådhuspladsen 100");
		e2 = new Environment(2.1,0.9,0.55);
		con2 = new Content("Apple",new Environment(2.0,0.8,0.5), 0.1);
	}
	
	@Test
	public void testContainer() {	
		Container c1 = new Container(cl1, e1, con1);
		Container c2 = new Container(cl2, e2, con2);
		
		assertEquals(1, c1.getContainerID());
		assertEquals(2, c2.getContainerID());
		
		assertEquals(cl1,c1.getClientofContainer());
		assertEquals(e1,c1.getContainerEnvironment());
		assertEquals(con1,c1.getContainerContent());
	}
	
	@Test
	public void testConfigureContainer() {
		Container c1 = new Container(cl1, e1, con1);
		
		c1.setClientofContainer(cl2);
		assertEquals(cl2,c1.getClientofContainer());
		
		c1.setContainerEnvironment(e2);
		assertEquals(e2,c1.getContainerEnvironment());
		
		c1.setContainerContent(con2);
		assertEquals(con2,c1.getContainerContent());
	}
}
