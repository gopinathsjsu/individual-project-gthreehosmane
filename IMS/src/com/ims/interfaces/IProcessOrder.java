package com.ims.interfaces;

import java.util.Map;

public interface IProcessOrder {
	
	public void processOrder(Map<String,String> order, String card, String inputFile);

}
