package com.ims.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ims.interfaces.IProcessOrder;

public class ProcessOrderImpl implements IProcessOrder{
	List<String> itemsExceedingCap = new ArrayList<>();
	List<String> itemsExceedingStock = new ArrayList<>();
	boolean stockError = false;
	boolean capError = false;

	@Override
	public void processOrder(Map<String,String> order, String card, String inputFile) {
		StockInventoryImpl uniqueInventoryInstance = StockInventoryImpl.getInventoryInstance();
		HashMap<String,Integer> cart = new HashMap<>();
		
		//check category cap
		checkCategoryCapExceeds(uniqueInventoryInstance, order);
		
		// check stock in inventory
		checkStockExceeds(uniqueInventoryInstance, order);
		
		//add items to cart if no error else print errors to text file
		if(stockError) {
			TextFactoryImpl writeText = new TextFactoryImpl();
			writeText.write("Order Quantity is more than items in the inventory, Please correct quantities for - ",itemsExceedingStock);
		}
		if(capError) {
			TextFactoryImpl writeText = new TextFactoryImpl();
			writeText.write("Order Quantity is more than category cap, Please correct quantities for -  ",itemsExceedingCap);
		}
		if(!stockError && !capError) {
			for(Entry<String,String> e : order.entrySet()) {
				int requiredQuanity = Integer.parseInt(order.get(e.getKey()));
				cart.put(e.getKey(), requiredQuanity);
			}
			// calculate price
			float price = 0;
			for(Entry<String, Integer> e : cart.entrySet()) {
				price= price+(uniqueInventoryInstance.getItemPrice(e.getKey())*cart.get(e.getKey()));
			}
			CSVFactoryImpl csvWriter = new CSVFactoryImpl();
			csvWriter.write(String.valueOf(price),new ArrayList<>());
			//checkout  if the order is successfull then, reduce the quantity of items that are checked out
			for(Entry<String,Integer> e : cart.entrySet()) {
				uniqueInventoryInstance.modifyItemQuantity(e.getKey(), cart.get(e.getKey()));
			}
			//check if card is valid? TODO
			// check if you can append total price to the input file itself with the column heading as total price
			
			if(! uniqueInventoryInstance.hasCard(card)) {
				uniqueInventoryInstance.addCard(card);
				System.out.println("Card added to database successfully");
			}
			else {
				System.out.println("Card number provided with this order already exists in database, so card was not added");
			}
		}
	}
	public void checkCategoryCapExceeds(StockInventoryImpl uniqueInventoryInstance, Map<String, String> order) {
		
		//check category cap
				for(Entry<String, String> e : order.entrySet()) {
					String category = uniqueInventoryInstance.getItemCategory(e.getKey());
					int cap = uniqueInventoryInstance.getCap(category);
					int quantityRequested = Integer.parseInt(order.get(e.getKey()));
					if(quantityRequested > cap)
					{
						// add list of invalid items to a list
						capError = true;
						itemsExceedingCap.add(e.getKey());
						
					}
				}
		
	}
	public void checkStockExceeds(StockInventoryImpl uniqueInventoryInstance, Map<String, String> order) {
		// check stock in inventory
		for(Entry<String, String> e : order.entrySet()) {
			int itemStock = uniqueInventoryInstance.getItemStock(e.getKey());
			int requiredQuantity = Integer.parseInt(order.get(e.getKey()));
			
			if(itemStock < requiredQuantity) {
				stockError = true;
				itemsExceedingStock.add(e.getKey());
			}
		}
	}

}
