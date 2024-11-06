/**
 * 
 */
package no.hvl.dat152.obl4.blog.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Util {

	/**
	 * 
	 */
	
	public static void saveComments(String path, String comment, String name) {
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter(path, true))) {
			
			br.write(name+": "+comment+"\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}					
	}
	
	public static void deleteComments(String path) {
		File f = new File(path);
		f.delete();
		// create a clean file
		try {
			f = new File(path);
			f.createNewFile();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static List<String> getComments(String path){
		
		List<String> comments = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			
			String line = "";
			while((line=br.readLine())!= null) 
				comments.add(line);			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return comments;
	}

}
