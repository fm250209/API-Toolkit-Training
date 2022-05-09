package com.ncr.tx.apitoolkit.training;

import java.util.SortedMap;


public class CalculateTotal {
	
	public Integer invoke(SortedMap<String, Object> fields, SortedMap<String, Object> parameters) {
		

		if (!fields.containsKey("totalBalance")){
			fields.put("totalBalance", fields.get("current.currentBalance").toString());
		} else {
			int oldTotal = Integer.parseInt(fields.get("totalBalance").toString());
			int newTotal = oldTotal + Integer.parseInt(fields.get("current.currentBalance").toString());
			fields.put("totalBalance", Integer.toString(newTotal));

		}
		
		return 0;
	}

}
