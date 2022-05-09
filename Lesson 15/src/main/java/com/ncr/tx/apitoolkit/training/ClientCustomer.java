package com.ncr.tx.apitoolkit.training;

import com.ncr.tx.apitoolkit.ApiToolkitConstants;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.ncr.tx.apitoolkit.messages.JsonUtils;
import org.springframework.web.reactive.function.client.ClientResponse;

import java.util.SortedMap;


public class ClientCustomer {

	public static final String PARAMETER_INPUT_STRING = "id";
	public static final String[] INVOKE_REQUIRED_PARAMETERS = {PARAMETER_INPUT_STRING};
	
	public Mono<Integer> invoke(SortedMap<String, Object> fields, SortedMap<String, Object> parameters) {
		return Mono.<Integer> create (sink ->{
			WebClient.Builder clientBuilder = WebClient.builder();
			WebClient client = clientBuilder
					.baseUrl("http://localhost:8080/"+System.getenv("API_INJECTED_PREFIX"))
					.build();
			Mono<ClientResponse> clientResponse = client.get().uri("/v1/parallelHelper/"+ parameters.get(PARAMETER_INPUT_STRING).toString()).exchange();
			clientResponse.flatMap(response -> {
					return response.bodyToMono(String.class);
				}).subscribe(respObj -> {
								JsonUtils.JsontoSortedMap("response.body", JsonUtils.ReadTree(respObj), fields, 100);
								sink.success(0);
							}
					);

		});

	}

}
