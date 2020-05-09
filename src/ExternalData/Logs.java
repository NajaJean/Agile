package ExternalData;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;  
import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import core.Calendar;
import core.Container;
import core.ContainerJourney;
import core.Environment;
import core.NotifyObject;  


public class Logs {
	
	
	
	public Logs(Container[] cont) {
	
		for (int i = 0; i < cont.length; i++) {
			
			createContainerLog(cont[i]);
		}
	}
	
	public void createFile(String fileName) {
		
		try {
	      File myObj = new File("src\\logs\\" + fileName  + ".txt");
	      if (myObj.createNewFile()) {
	       // System.out.println("File created: " + myObj.getName());
	      } else {
	        //System.out.println("File already exists.");
	      }
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }}  

	
	public String readFile(String fileName) {
		String data = "";
		try {
		      File myObj = new File("src\\logs\\" + fileName + ".txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        data += myReader.nextLine() + "\n";
		        //System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return data;
	}
	
	public void createContainerLog(Container c) 
	{
		createFile("Container " + String.valueOf(c.getContainerID()));
	}
	
	public Double modNum(Double number) 
	{	
		Random r = new Random();
		
		return number * (((double) r.nextInt(20) + 91.0) / 100);
	}
	
	public String randomizeE(Environment e) 
	{	
		
		return "'" + String.valueOf(e.getEnviro_ID()) + "', '" + String.format(Locale.ROOT,"%.2f", modNum(e.getTemp())) + "', '" +
				String.format(Locale.ROOT,"%.2f", modNum(e.getPressure())) + "', '" + String.format(Locale.ROOT,"%.2f", modNum(e.getHumidity())) + "'";
	}
	
	public void appendContainerLog(ContainerJourney c)
	{
		try {
		      FileWriter myWriter = new FileWriter("src\\logs\\" + "Container " + 
		    		  					String.valueOf(c.getContaineronJourney().getContainerID()) + ".txt", true);
		      
			  String isThresholdOK = thresholdCheck(c);	  
		      
		      myWriter.write(String.valueOf(c.getContaineronJourney().getContainerID()) + "\t" + 
		    		  		 String.valueOf(c.getContaineronJourney().getClientofContainer()) + "\t" +
		    		  		 randomizeE(c.getContaineronJourney().getContainerEnvironment()) + "\t" +
		    		  		 String.valueOf(c.getContaineronJourney().getContainerContent()) + "\t" +
		    		  		 String.format(Locale.ROOT,"%.2f", c.getCurrentX()) + "\t" +
		    		  		 String.format(Locale.ROOT,"%.2f", c.getCurrentY()) + "\t" + 
		      				 String.valueOf(c.getJourneyID()) + "\t" +
		      				 String.valueOf(c.getStartDate()) + "\t" + 
		      				 String.valueOf(Calendar.getSystemDate()) + "\t" +
		      				 String.valueOf(c.getEndDate()) + "\t" + 
		      				 isThresholdOK + "\t" + "\n");
		      
		      myWriter.close();
		      //System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}

	private String thresholdCheck(ContainerJourney c) {
		NotifyObject response = c.getContaineronJourney().getContainerEnvironment().checkEnvironment(c.getContaineronJourney().getContainerContent());
		  String isThresholdOK;
		  if (response.getNotifyCode()!=0) {
			  isThresholdOK = "Not OK";
		  }
		  else {
			  isThresholdOK = "OK";
		  }
		return isThresholdOK;
	}
	
	public void appendAllContainerLogs(ContainerJourney[] cJs) {
		for (int i = 0; i < cJs.length;i++) {
			
			appendContainerLog(cJs[i]);
		}
	}

}

