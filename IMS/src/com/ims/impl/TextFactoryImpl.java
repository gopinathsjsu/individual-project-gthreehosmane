package com.ims.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import com.ims.interfaces.IOutputWriterFactory;

public class TextFactoryImpl implements IOutputWriterFactory{

	@Override
	public void write(String fileText, List<String> items,Map<String,Integer> cart) {
		try {
		      File output = new File("OUTPUT.txt");
		      boolean result = Files.deleteIfExists(output.toPath());
		      if (output.createNewFile()) {
		        System.out.println("File created: " + output.getName());
		      } else {
		        System.out.println("File with this name already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred while creating file.");
		      e.printStackTrace();
		    }
		FileWriter outputWriter = null;
		try {
		      outputWriter = new FileWriter("OUTPUT.txt");
		      outputWriter.write("Error processing your order - "+fileText);
		      if(! items.isEmpty()) {
		    	  for(String item : items) {
		    		  outputWriter.write(item+", ");
		    	  }
		      }
		      System.out.println("Successfully wrote error message to the file.");
		      System.out.println("Please check OUTPUT.txt at this location -  " + System.getProperty("user.dir"));
		      outputWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred while creating file.");
		      e.printStackTrace();
		    }
	}

}
