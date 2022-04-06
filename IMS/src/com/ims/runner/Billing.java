package com.ims.runner;

import com.ims.impl.StockInventoryImpl;

public class Billing {
	public static void main(String[] args) {
		StockInventoryImpl uniqueInventoryInstance = StockInventoryImpl.getInventoryInstance();
		// read input
		// set category cap
		// process order
		//chain of handlers
		Billing billing = new Billing();
		billing.setCategoryCap(uniqueInventoryInstance);
		uniqueInventoryInstance.printCap();

	}
	public void setCategoryCap(StockInventoryImpl uniqueInventoryInstance) {
		uniqueInventoryInstance.addCap("Essentials", 3);
		uniqueInventoryInstance.addCap("Luxury", 4);
		uniqueInventoryInstance.addCap("Miscellaneous", 6);
		
	}
	public void readInput() {
		
	}
	public void processOrder() {
		
	}

}
