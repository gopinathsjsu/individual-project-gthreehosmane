package com.ims.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
		}
	}
	public boolean hasCard(String cardNumber) {
		return cards.contains(cardNumber);
	}
	public void addCap(String category, int cap) {
		categoryCap.put(category.toLowerCase(), cap);
	}
	public void printCap() {
		for(Entry<String, Integer> e : categoryCap.entrySet()) {
			System.out.println("Category: "+e.getKey()+" Cap: "+e.getValue());
		}
	}
	public int getCap(String category) {
		return categoryCap.get(category);
	}
    
	public void addItem(String item, String category, String quantity, String ppu) {
		items.put(item.toLowerCase(), new String[] {category.toLowerCase(),quantity,ppu});
	}
	public void modifyItemQuantity(String item, int checkedOutQuantity) {
		int currentQuantity = Integer.parseInt(items.get(item)[1]);
		int newQuantity = currentQuantity - checkedOutQuantity ; 
		items.get(item)[1] = String.valueOf(newQuantity);
	}
	
	public String getItemCategory(String item) {
		return items.get(item)[0];
	}
	
	public int getItemStock(String item) {
		return Integer.valueOf(items.get(item)[1]);
	}
	public int getItemPrice(String item) {
		return Integer.valueOf(items.get(item)[2]);
	}

}
