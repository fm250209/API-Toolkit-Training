package com.ncr.tx.apitoolkit.training;

import org.json.JSONObject;

import java.util.SortedMap;

public class CustomerAction {

	public Integer invoke(SortedMap<String, Object> fields, SortedMap<String, Object> parameters) {
		JSONObject custResponse = customerResponse(fields, parameters);
		fields.put("ext.response.body", custResponse);
		return 0;
	}

	public JSONObject customerResponse(SortedMap<String, Object> fields, SortedMap<String, Object> parameters) {
		JSONObject customer = new JSONObject();
		customer.put("name", "Kane");
		customer.put("address", "281 Main Street");
		JSONObject custResponse = new JSONObject();
		custResponse.put("customerData", customer);
		return custResponse;
	}

}
