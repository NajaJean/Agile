package core;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;  
import java.io.IOException;
import java.util.Scanner;  


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
	        System.out.println("File created: " + myObj.getName());
	      } else {
	        //System.out.println("File already exists.");
	      }
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }}
	
	
	public void writeFile(String fileName) {
		try {
	      FileWriter myWriter = new FileWriter("src\\logs\\" + fileName + ".txt", true);
	      myWriter.write("test\n");
	      myWriter.close();
	      System.out.println("Successfully wrote to the file.");
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}
	
	public void readFile(String fileName) {
		try {
		      File myObj = new File("src\\logs\\" + fileName + ".txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public void createContainerLog(Container c) 
	{
		createFile("Container " + String.valueOf(c.getContainerID()));
	}
	
	
	
	public void appendContainerLog(ContainerJourney c)
	{
		try {
		      FileWriter myWriter = new FileWriter("src\\logs\\" + "Container " + 
		    		  					String.valueOf(c.getContaineronJourney().getContainerID()) + ".txt", true);
		      myWriter.write(String.valueOf(c.getContaineronJourney().getContainerID()) + "\t" + 
		    		  		 String.valueOf(c.getContaineronJourney().getClientofContainer()) + "\t" +
		    		  		 String.valueOf(c.getContaineronJourney().getContainerEnvironment()) + "\t" + 
		    		  		 String.valueOf(c.getContaineronJourney().getContainerContent()) + "\t" +
		    		  		 String.format("%.2f", c.getCurrentX()) + "\t" +
		    		  		 String.format("%.2f", c.getCurrentY()) + "\t" + 
		      				 String.valueOf(c.getJourneyID()) + "\t" +
		      				 String.valueOf(c.getStartDate()) + "\t" + 
		      				String.valueOf(Calendar.getSystemDate()) + "\t" +
		      				 String.valueOf(c.getEndDate()) + "\t" + "\n");
		      
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public void appendAllContainerLogs(ContainerJourney[] cJs) {
		for (int i = 0; i < cJs.length;i++) {
			
			appendContainerLog(cJs[i]);
		}
	}
	
	public static void main(String[] args) {
	
		Container[] cont = DatabaseData.getContainers();
		ContainerJourney[] cJs = DatabaseData.getJournies();
		Logs a = new Logs(cont);
		
		for (int i = 0; i < cont.length; i++) {
			a.createContainerLog(cont[i]);
			
			a.appendContainerLog(cJs[i]);
		}
	}
}

