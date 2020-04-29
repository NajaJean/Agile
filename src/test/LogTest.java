package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import core.*;

public class LogTest {

	private Automation a;
	private Container[] cs;
	private Logs L;
	private ContainerJourney first;
	private ContainerJourney second;
	private ContainerJourney[] cJs;
	
	
	@Before
	public void prep() {

		a = new Automation();
		cs = new Container[2];
		cs[0] = a.rContainer(); 
		cs[1] =	a.rContainer();
		
		L = new Logs(cs);
		
		first = a.rCJs();
		second = a.rCJs();
		cJs = new ContainerJourney[]{first, second};
	}
		
	
	@Test
	public void testCreateFile() {

		L.createFile("test");
		File testFile = new File("src\\logs\\test.txt");
		assertTrue(testFile.exists());
	}
	
	@Test
	public void testCreateContainerLog() {

		L.createContainerLog(cJs[0].getContaineronJourney());
		File testFile = new File("src\\logs\\Container " + 
								 String.valueOf(cJs[0].getContaineronJourney().getContainerID()) + ".txt");
		assertTrue(testFile.exists());
	}
	
	@Test 
	public void testAppendConteinerLog() {
	
		L.appendContainerLog(cJs[0]);
		
		String expected = String.valueOf(cJs[0].getContaineronJourney().getContainerID()) + "\t" + 
		  		 String.valueOf(cJs[0].getContaineronJourney().getClientofContainer()) + "\t" +
		  		 String.valueOf(cJs[0].getContaineronJourney().getContainerEnvironment()) + "\t" + 
		  		 String.valueOf(cJs[0].getContaineronJourney().getContainerContent()) + "\t" +
		  		 String.format("%.2f", cJs[0].getCurrentX()) + "\t" +
		  		 String.format("%.2f", cJs[0].getCurrentY()) + "\t" + 
 				 String.valueOf(cJs[0].getJourneyID()) + "\t" +
 				 String.valueOf(cJs[0].getStartDate()) + "\t" + 
 				 String.valueOf(Calendar.getSystemDate()) + "\t" +
 				 String.valueOf(cJs[0].getEndDate()) + "\t";
		String inFile = L.readFile("Container " + String.valueOf(cJs[0].getContaineronJourney().getContainerID()));

		assertTrue(expected.equals(inFile));
	}
	
	
	@Test 
	public void testAppendAllConteinerLogs() {
		
		
		L.appendAllContainerLogs(cJs);
		
		for (int i = 0; i < 2; i++) {
		
		
			String expected = String.valueOf(cJs[i].getContaineronJourney().getContainerID()) + "\t" + 
			  		 String.valueOf(cJs[i].getContaineronJourney().getClientofContainer()) + "\t" +
			  		 String.valueOf(cJs[i].getContaineronJourney().getContainerEnvironment()) + "\t" + 
			  		 String.valueOf(cJs[i].getContaineronJourney().getContainerContent()) + "\t" +
			  		 String.format("%.2f", cJs[i].getCurrentX()) + "\t" +
			  		 String.format("%.2f", cJs[i].getCurrentY()) + "\t" + 
	 				 String.valueOf(cJs[i].getJourneyID()) + "\t" +
	 				 String.valueOf(cJs[i].getStartDate()) + "\t" + 
	 				 String.valueOf(Calendar.getSystemDate()) + "\t" +
	 				 String.valueOf(cJs[i].getEndDate()) + "\t";
			String inFile = L.readFile("Container " + String.valueOf(cJs[i].getContaineronJourney().getContainerID()));
	
			System.out.println(expected);
			System.out.println(inFile);
			assertTrue(expected.equals(inFile));
		}
	}
	
}
