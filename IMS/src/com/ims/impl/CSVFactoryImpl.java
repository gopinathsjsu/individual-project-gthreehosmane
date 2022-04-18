package com.ims.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.ims.interfaces.IOutputWriterFactory;

public class CSVFactoryImpl implements IOutputWriterFactory{



	@Override
	public void write(String fileText, List<String> items) {
		/*try {
		      File output = new File("Bill.csv");
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
		      FileWriter outputWriter = new FileWriter("Bill.csv");
		      outputWriter.write("Order placed succesfully: cost of your order - "+fileText);
		      outputWriter.close();
		      System.out.println("Successfully wrote cost to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred while creating file.");
		      e.printStackTrace();
		    }*/
		try(FileWriter fw = new FileWriter("OUTPUT.csv", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
				{
				    out.println("Order placed succesfully: cost of your order -  - "+fileText);
				    System.out.println("Total price added to csv file");
				} catch (IOException e) {
					 System.out.println("An error occurred while creating file.");
				}
		
	}

}
