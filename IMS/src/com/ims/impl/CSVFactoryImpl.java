package com.ims.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ims.interfaces.IOutputWriterFactory;

public class CSVFactoryImpl implements IOutputWriterFactory{

    

	@Override
	public void write(String fileText, List<String> items, Map<String,Integer> cart) {
		StockInventoryImpl uniqueInventoryInstance = StockInventoryImpl.getInventoryInstance();
		try {
		      File output = new File("OUTPUT.csv");
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
		
		   try {
		      FileWriter outputWriter = new FileWriter("OUTPUT.csv");
		      StringBuilder sb = new StringBuilder();
		      sb.append("Item");
		      sb.append(',');
		      sb.append("Quantity");
		      sb.append(',');
		      sb.append("Price");
		      sb.append(',');
		      sb.append("TotalPrice");
		      sb.append('\n');
		      int count = 0;
              for(Entry<String, Integer> e : cart.entrySet()) {
            	  count++;
            	  sb.append(e.getKey());
    		      sb.append(',');
    		      sb.append(e.getValue());
    		      sb.append(',');
    		      int price = uniqueInventoryInstance.getItemPrice(e.getKey())*e.getValue();
    		      sb.append(price);
    		      if(count==1) {
    		    	  sb.append(',');
    		    	  sb.append(fileText);
    		      }
    		      sb.append('\n');
              }
		      

		      outputWriter.write(sb.toString());
		      outputWriter.write("Order placed succesfully: cost of your order - "+fileText);
		      outputWriter.close();
		      System.out.println("Successfully wrote cost to the file.");
		      System.out.println("Please check OUTPUT.csv at this location -  " + System.getProperty("user.dir"));
		    } catch (IOException e) {
		      System.out.println("An error occurred while creating file.");
		      e.printStackTrace();
		    }
		
	}

}
