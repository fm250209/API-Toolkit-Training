package com.ncr.tx.apitoolkit.training;

import java.util.Iterator;
import java.util.SortedMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class ListProcessAction {

	public Mono<Integer> invoke(SortedMap<String, Object> fields, SortedMap<String, Object> parameters) {
		return Mono.<Integer>create(sink -> {
			WebClient.Builder clientBuilder = WebClient.builder();
			WebClient client = clientBuilder
					.baseUrl("http://localhost:8080")
					.build();
			Mono<String> clientResponse = client.get().uri("/v1/customerData").retrieve().bodyToMono(String.class);
			Mono<String> clientResponse1 = client.get().uri("/v1/accountData").retrieve().bodyToMono(String.class);
			Mono<String> combined = Mono.zip(clientResponse1, clientResponse).map(m -> {
				String m1 = m.getT1();
				String m2 = m.getT2();
				JSONObject combinedObject = new JSONObject();
				Long total = 0L;
				JSONObject custObject = new JSONObject(m2);
				JSONObject getObject = custObject.getJSONObject("customerData");
				Iterator<String> custKeys = getObject.keys();
				while (custKeys.hasNext()) {
					String key = custKeys.next();
					combinedObject.put(key, getObject.get(key));
				}
				JSONObject accountObject = new JSONObject(m1);
				JSONArray getArray = accountObject.getJSONArray("accountData");
				boolean checkingFlag = true;
				for (int i = 0; i < getArray.length(); i++) {
					JSONObject objects = getArray.getJSONObject(i);
					Iterator<String> keys = objects.keys();
					while (keys.hasNext()) {
						String key = keys.next();
						if (key.equalsIgnoreCase("currentBalance") && checkingFlag) {
							combinedObject.put("checkingBalance", objects.get(key));
							total = total + Long.valueOf(objects.get(key).toString());
							checkingFlag = false;
						} else {
							if (key.equalsIgnoreCase("currentBalance")) {
								combinedObject.put("savingsBalance", objects.get(key));
								total = total + Long.valueOf(objects.get(key).toString());
							}
						}
					}
				}
				combinedObject.put("totalBalance", total.toString());
				return combinedObject.toString();
			});
			combined.subscribe(respObj -> {
				fields.put("ext.response.body", respObj);
				sink.success(0);
			});
		});
	}
}
