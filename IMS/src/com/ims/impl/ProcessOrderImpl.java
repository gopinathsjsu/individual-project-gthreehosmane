package com.ims.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ims.interfaces.IProcessOrder;

public class ProcessOrderImpl implements IProcessOrder{

	@Override
	public void processOrder(HashMap<String,String> order, String card, String inputFile) {
		StockInventoryImpl uniqueInventoryInstance = StockInventoryImpl.getInventoryInstance();
		HashMap<String,Integer> cart = new HashMap<>();
		List<String> itemsExceedingCap = new ArrayList<>();
		List<String> itemsExceedingStock = new ArrayList<>();
		boolean stockError = false;
		boolean capError = false;
		//check category cap
		for(String item : order.keySet()) {
			String category = uniqueInventoryInstance.getItemCategory(item);
			int cap = uniqueInventoryInstance.getCap(category);
			int quantityRequested = Integer.valueOf(order.get(item));
			if(quantityRequested > cap)
			{
				// add list of invalid items to a list
				capError = true;
				itemsExceedingCap.add(item);
				
			}
		}
		// check stock in inventory
		for(String key : order.keySet()) {
			int itemStock = Integer.valueOf(uniqueInventoryInstance.getItemStock(key));
			int requiredQuantity = Integer.valueOf(order.get(key));
			
			if(itemStock < requiredQuantity) {
				stockError = true;
				itemsExceedingStock.add(key);
			}
		}
		//check if card is valid? TODO
		
			if(! uniqueInventoryInstance.hasCard(card)) {
				uniqueInventoryInstance.addCard(card);
			}
		
		//add items to cart
		if(stockError) {
			TextFactoryImpl writeText = new TextFactoryImpl();
			writeText.write("Order Quantity is more than items in the inventory, Please correct quantities for",itemsExceedingStock);
		}
		else if(capError) {
			TextFactoryImpl writeText = new TextFactoryImpl();
			writeText.write("Order Quantity is more than category cap, Please correct quantities for ",itemsExceedingCap);
		}
		else {
			for(String key : order.keySet()) {
				int requiredQuanity = Integer.valueOf(order.get(key));
				cart.put(key, requiredQuanity);
			}
			// calculate price
			float price = 0;
			for(String item : cart.keySet()) {
				price+= uniqueInventoryInstance.getItemPrice(item)*cart.get(item);
			}
			CSVFactoryImpl csvWriter = new CSVFactoryImpl();
			csvWriter.write(String.valueOf(price),new ArrayList<>());
			// if the order is successfull then, reduce the quantity of items that are checked out
			//if output files already exists rewrite them or append
		}
	}

}
