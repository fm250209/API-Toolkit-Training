package com.ncr.tx.apitoolkit.training;

import java.util.SortedMap;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;

public class Unflattened {

	public Integer invoke(SortedMap<String, Object> fields, SortedMap<String, Object> parameters) {

		JsonNode jn = (JsonNode) fields.get("ext.request.body");
		JsonNode nameNode = jn.at("/addressData/streetName");
		JsonNode nameNode1 = jn.at("/addressData/streetNumber");
		String s1 = nameNode.asText();
		String s2 = nameNode1.asText();
		String s3 = s2 + " " + s1;
		JSONObject response = new JSONObject();
		response.put("Address", s3);
		fields.put("ext.response.body", response);

		return 0;
	}
}
