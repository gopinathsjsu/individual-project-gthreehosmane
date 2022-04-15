package com.ims.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.ims.interfaces.IStockInventory;

public class StockInventoryImpl implements  IStockInventory{
	
	private ArrayList<String> cards = new ArrayList<>();
	private HashMap<String,String[]> items = new HashMap<>();
	private HashMap<String,Integer> categoryCap = new HashMap<>();
	
	private static StockInventoryImpl uniqueInventoryInstance;
	
	private StockInventoryImpl() {
		
	}
	
	public static synchronized StockInventoryImpl getInventoryInstance() {
		if(uniqueInventoryInstance == null)
		{
			uniqueInventoryInstance = new StockInventoryImpl();
		}
		return uniqueInventoryInstance;
	}
	
	public void addCard(String newCardNumber) {
		if(!cards.contains(newCardNumber)) {
			cards.add(newCardNumber);
			System.out.println("Card added to databse successfully");
		}
	}
	public boolean hasCard(String cardNumber) {
		if(cards.contains(cardNumber))
			return true;
		else
			return false;
	}
	public void addCap(String category, int cap) {
		categoryCap.put(category, cap);
	}
	public void printCap() {
		for(String key : categoryCap.keySet()) {
			System.out.println("Category: "+key+" Cap: "+categoryCap.get(key));
		}
	}
	public int getCap(String category) {
		return categoryCap.get(category);
	}
    
	public void addItem(String item, String category, String quantity, String ppu) {
		items.put(item, new String[] {category,quantity,ppu});
	}
	
	public int getItemStock(String item) {
		return Integer.valueOf(items.get(item)[1]);
	}

}
