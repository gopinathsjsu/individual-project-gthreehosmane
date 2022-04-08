package com.ims.runner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import com.ims.impl.StockInventoryImpl;



public class Billing {
	public static void main(String[] args) {
		File inventory  = null; 
		File order = null;
		HashMap<String,String> orderDetails = new HashMap<>();
		HashMap<String,Integer> cart = new HashMap<>();
		StockInventoryImpl uniqueInventoryInstance = StockInventoryImpl.getInventoryInstance();
		// set inventory
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter inventory file name: ");
		String inventoryFilename = sc.next();
		inventory = new File(inventoryFilename);
		FileReader inventoryfileReader = null;
		try {
			inventoryfileReader = new FileReader(inventory);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader inventoryReader = new BufferedReader(inventoryfileReader);
		String line = "";
		String[] values = null;
		try {
			while((line = inventoryReader.readLine()) != null)
			{
				values = line.split("\n");
				for(int i=0;i< values.length;i++) {
					String[] items = values[i].split(",");
					if( items.length == 4) {
						if(!items[0].equals("Item")) {
								String item = items[0];
								String category = items[1];
								String quantity = items[2];
								String ppu = items[3];
								uniqueInventoryInstance.addItem(item, category, quantity, ppu);
						}
					}
				}
				
			}
			inventoryReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
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
					
					if( items.length == 3 && !("Items".equals(items[0]))) {
						
							
								String item = items[0];
								String quantity = items[1];
								cardNumber = items[2];
								orderDetails.put(item, quantity);
								
						
					}
					 if( items.length == 2 && (!items[0].equals("Item"))) {
						
					
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
		uniqueInventoryInstance.getItems();
		//check order details
		for(String key : orderDetails.keySet()) {
			System.out.println(key+"--"+orderDetails.get(key));
		}
		//chain of handlers
		sc.close();

	}
	public void setCategoryCap(StockInventoryImpl uniqueInventoryInstance) {
		uniqueInventoryInstance.addCap("Essentials", 3);
		uniqueInventoryInstance.addCap("Luxury", 4);
		uniqueInventoryInstance.addCap("Miscellaneous", 6);
		
	}

}
