package com.ncr.tx.apitoolkit.training;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.SortedMap;

public class AccountAction {
	public Integer invoke(SortedMap<String, Object> fields, SortedMap<String, Object> parameters) {
		JSONObject accResponse = accountResponse(fields, parameters);
		fields.put("ext.response.body", accResponse);
		return 0;
	}

	public JSONObject accountResponse(SortedMap<String, Object> fields, SortedMap<String, Object> parameters) {
		JSONObject accResponse = new JSONObject();
		JSONArray accountData = new JSONArray();
		JSONObject account = new JSONObject();

		account.put("accountNumber", "153700005215");
		account.put("accountShortName", "Test Customer CUR AC");
		account.put("accountType", "40");
		account.put("accountId", "c31f7fbf-9c58-4f89-9ebe-9efc108a8246");
		account.put("productCode", "DDA");
		account.put("renumbered", false);
		account.put("currentBalance", "300010");
		account.put("availableBalance", "456200");
		accountData.put(account);
		account = new JSONObject();
		account.put("accountNumber", "153700005216");
		account.put("accountShortName", "Test Customer SAV AC");
		account.put("accountType", "45");
		account.put("accountId", "c31f7fbf-9c58-4f89-9ebe-9efc108a8278");
		account.put("productCode", "DDA");
		account.put("renumbered", true);
		account.put("currentBalance", "450000");
		account.put("availableBalance", "470000");
		accountData.put(account);
		accResponse.put("accountData", accountData);
		return accResponse;
	}

}
