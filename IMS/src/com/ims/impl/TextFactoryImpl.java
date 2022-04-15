package com.ims.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.ims.interfaces.IOutputWriterFactory;

public class TextFactoryImpl implements IOutputWriterFactory{

	@Override
	public void write(String fileText) {
		try {
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
		      outputWriter.close();
		      System.out.println("Successfully wrote error message to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred while creating file.");
		      e.printStackTrace();
		    }
	}

}
