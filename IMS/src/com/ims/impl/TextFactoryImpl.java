package com.ims.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.ims.interfaces.IOutputWriterFactory;

public class TextFactoryImpl implements IOutputWriterFactory{

	@Override
	public void write(String fileText, List<String> items) {
		/*try {
		      File output = new File("OUTPUT.txt");
		      if (output.createNewFile()) {
		        System.out.println("File created: " + output.getName());
		      } else {
		        System.out.println("File with this name already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred while creating file.");
		      e.printStackTrace();
		    }
		
		try {
		      FileWriter outputWriter = new FileWriter("OUTPUT.txt");
		      outputWriter.write("Error processing your order - "+fileText);
		      if(! items.isEmpty()) {
		    	  for(String item : items) {
		    		  outputWriter.write(item);
		    	  }
		      }
		      outputWriter.close();
		      System.out.println("Successfully wrote error message to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred while creating file.");
		      e.printStackTrace();
		    }*/
		
		try(FileWriter fw = new FileWriter("OUTPUT.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
				{
				    out.println("Error processing your order - "+fileText);
				    if(! items.isEmpty()) {
				    	  for(String item : items) {
				    		  out.println(item);
				    	  }
				      }
				    System.out.println("Error message added to text file");
				} catch (IOException e) {
					 System.out.println("An error occurred while creating file.");
				}
	}

}
