package com.ims.interfaces;

import java.util.List;
import java.util.Map;

public interface IOutputWriterFactory {
	
	public void write(String fileText, List<String> items, Map<String,Integer> cart);


}
