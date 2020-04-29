package core;

import java.io.File;

public class deletelogs {
	
	public static void deleteFolder(File folder) {
	    File[] files = folder.listFiles();
	    if(files!=null) { //some JVMs return null for empty dirs
	        for(File f: files) {
	            if(f.isDirectory()) {
	                deleteFolder(f);
	            } else {
	                f.delete();
	            }
	        }
	    }
	 
	}
	
	public static void main(String[] args) {
		
		File myObj = new File("src\\logs\\");
		
		deleteFolder(myObj);
	}
}
