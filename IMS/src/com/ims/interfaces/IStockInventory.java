package com.ims.interfaces;

public interface IStockInventory {
	
	public void addCap(String category, int cap);
	public int getCap(String category);
	public void addItem(String item, String category, String quantity, String ppu);

}
