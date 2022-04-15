package com.ims.runner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import com.ims.impl.StockInventoryImpl;



public class Billing {
	public static void main(String[] args) {
		File inventory  = null; 
		File order = null;
		HashMap<String,String> orderDetails = new HashMap<>();
		StockInventoryImpl uniqueInventoryInstance = StockInventoryImpl.getInventoryInstance();
		Scanner sc = new Scanner(System.in);
		// process order
		String cardNumber  = null;
		System.out.println("Enter order file name: ");
		String orderFilename = sc.next();
		order = new File(orderFilename);
		FileReader orderfileReader = null;
		try {
			orderfileReader = new FileReader(order);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader orderReader = new BufferedReader(orderfileReader);
		String orderline = "";
		String[] ordervalues = null;
		try {
			while((orderline = orderReader.readLine()) != null)
			{
				ordervalues = orderline.split("\n");
				for(int i=0;i< ordervalues.length;i++) {
					String[] items = ordervalues[i].split(",");
					if( items.length == 3) {
						if(!(items[1].equals("Quantity"))) {
								String item = items[0];
								String quantity = items[1];
								cardNumber = items[2];
								orderDetails.put(item, quantity);
						}		
						
					}
					else {
						
					
								String item = items[0];
								String quantity = items[1];
								orderDetails.put(item, quantity);
						
					}
				}
				
			}
			orderReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Billing billing = new Billing();
		// set category cap
		billing.setCategoryCap(uniqueInventoryInstance);
		uniqueInventoryInstance.printCap();
		//uniqueInventoryInstance.getItem();
		// set inventory
		billing.setInventory(uniqueInventoryInstance);
		//check order details
		for(String key : orderDetails.keySet()) {
			System.out.println(key+"--"+orderDetails.get(key));
		}
		//chain of handlers
		// check card, check inventory stock, check category cap while processing order
		sc.close();

	}
	ArrayList<String> items = new ArrayList<>(Arrays.asList("Clothes","Soap","Shampoo","Milk","Perfume","Chocolates","Handbag","Wallet","Bedsheet","Footware","HomeDecorPiece","Pen","Pencil"));
	ArrayList<String> category = new ArrayList<>(Arrays.asList("Essentials","Essentials","Essentials","Essentials","Luxury","Luxury","Luxury","Luxury","Misc","Misc","Misc","Misc","Misc"));
	ArrayList<String> quantity = new ArrayList<>(Arrays.asList("100","200","200","100","50","300","75","100","150","200","100","400","400"));
	ArrayList<String> ppu = new ArrayList<>(Arrays.asList("20","5","10","5","50","3","150","100","75","25","40","3","3"));
	private void setInventory(StockInventoryImpl uniqueInventoryInstance) {
		for(int i=0;i<items.size();i++) {
		 uniqueInventoryInstance.addItem(items.get(i), category.get(i), quantity.get(i), ppu.get(i));
		}
		
	}
	public void setCategoryCap(StockInventoryImpl uniqueInventoryInstance) {
		uniqueInventoryInstance.addCap("Essentials", 3);
		uniqueInventoryInstance.addCap("Luxury", 4);
		uniqueInventoryInstance.addCap("Miscellaneous", 6);
		
	}

}
