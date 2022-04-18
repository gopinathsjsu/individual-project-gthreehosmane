package com.ims.interfaces;

import java.util.HashMap;

public interface IProcessOrder {
	
	public void processOrder(HashMap<String,String> order, String card, String inputFile);

}
