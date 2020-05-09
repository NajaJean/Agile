package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ExternalData.DatabaseData;
import ExternalData.Logs;

import java.io.File;
import java.time.LocalDate;
import java.util.Locale;

import core.*;

public class LogTest {

	private Automation a;
	private Container[] cs;
	private Logs L;
	private ContainerJourney first;
	private ContainerJourney second;
	private ContainerJourney[] cJs;
	private Location[] locs;
	
	@Before
	public void prep() {

		a = new Automation();
		cs = new Container[2];
		cs[0] = a.rContainer(); 
		cs[1] =	a.rContainer();
		
		L = new Logs(cs);
		locs = DatabaseData.getLocations();
		
		first = new ContainerJourney(locs[0], locs[1], cs[0], LocalDate.of(2022, 4, 5), LocalDate.of(2022, 6, 6));
		first.getContaineronJourney().setClientofContainer(a.randomClient());
		first.getContaineronJourney().setContainerContent(a.randomContent());
		second = new ContainerJourney(locs[2], locs[3], cs[1], LocalDate.of(2022, 4, 5), LocalDate.of(2022, 6, 6));
		second.getContaineronJourney().setClientofContainer(a.randomClient());
		second.getContaineronJourney().setContainerContent(a.randomContent());
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
		
		String expected1stpart = String.valueOf(cJs[0].getContaineronJourney().getContainerID()) + "\t" + 
		  		 String.valueOf(cJs[0].getContaineronJourney().getClientofContainer());
		  		 
		  		 
		String expected2ndpart = String.valueOf(cJs[0].getContaineronJourney().getContainerContent()) + "\t" + 
		  		 String.format(Locale.ROOT,"%.2f", cJs[0].getCurrentX()) + "\t" +
				 String.format(Locale.ROOT,"%.2f", cJs[0].getCurrentY()) + "\t" +
				 String.valueOf(cJs[0].getJourneyID()) + "\t" +
				 String.valueOf(cJs[0].getStartDate()) + "\t" + 
				 String.valueOf(Calendar.getSystemDate()) + "\t" +
				 String.valueOf(cJs[0].getEndDate()) + "\t";
		
	
 				 
		String inFile = L.readFile("Container " + String.valueOf(cJs[0].getContaineronJourney().getContainerID()));

		assertTrue(inFile.contains(expected1stpart));
		assertTrue(inFile.contains(expected2ndpart));
	}
	
	
	@Test 
	public void testAppendAllConteinerLogs() {
		
		L.appendAllContainerLogs(cJs);
		
		for (int i = 0; i < 2; i++) {
		
			String expected1stpart = String.valueOf(cJs[i].getContaineronJourney().getContainerID()) + "\t" + 
			  		 String.valueOf(cJs[i].getContaineronJourney().getClientofContainer());	  		 
			  		 
			String expected2ndpart = String.valueOf(cJs[i].getContaineronJourney().getContainerContent()) + "\t" + 
			  		 String.format(Locale.ROOT,"%.2f", cJs[i].getCurrentX()) + "\t" +
					 String.format(Locale.ROOT,"%.2f", cJs[i].getCurrentY()) + "\t" +
					 String.valueOf(cJs[i].getJourneyID()) + "\t" +
					 String.valueOf(cJs[i].getStartDate()) + "\t" + 
					 String.valueOf(Calendar.getSystemDate()) + "\t" +
					 String.valueOf(cJs[i].getEndDate()) + "\t";
			
			 
			String inFile = L.readFile("Container " + String.valueOf(cJs[i].getContaineronJourney().getContainerID()));

			assertTrue(inFile.contains(expected1stpart));
			assertTrue(inFile.contains(expected2ndpart));
		}
	}
	
}