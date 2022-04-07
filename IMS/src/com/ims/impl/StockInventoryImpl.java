package com.ims.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.ims.interfaces.IStockInventory;

public class StockInventoryImpl implements  IStockInventory{
	
	private ArrayList<Long> cards = new ArrayList<>();
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
	
	public void addCard(Long newCardNumber) {
		if(!cards.contains(newCardNumber)) {
			cards.add(newCardNumber);
			System.out.println("Card added to databse successfully");
		}
	}
	public boolean hasCard(Long cardNumber) {
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
    
	public void addItem(String item, String category, String quanity, String ppu) {
		items.put(item, new String[] {category,quanity,ppu});
	}
	
	public void getItems() {
		for(String key: items.keySet()) {
			System.out.println("---"+key+"---"+items.get(key)[0]+"---"+items.get(key)[1]+"---"+items.get(key)[2]);
		}
	}

}
