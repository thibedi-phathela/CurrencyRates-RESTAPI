package io.exchangeratesapi.api;

import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
	
	private final WebClient webClient;
	private final String apiKey;
	private final ObjectMapper objectMapper; // JSON manipulation

    
    public ApiService(WebClient.Builder webClientBuilder, @Value("${api.key}") String apiKey, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.build();
		this.apiKey = apiKey;
        this.objectMapper = objectMapper;
    }
    
   //Method to make request to API
  	private Mono<JsonNode> request(String base){
  		return webClient.get()
                  .uri(apiKey+base)
                  .retrieve()
                  .bodyToMono(JsonNode.class);
  	
  	}
	
    //Method to retrieve all exchange rates for a given base currency
	public Mono<String> getAllRates(String base){
		return request(base)
                .map(jsonNode -> jsonNode.get("conversion_rates").toString());
		
	}
	
	//Method to retrieve a specific exchange rate for given base currency and target
	public Mono<String> getRate(String base, String target) {
	
		try {
			return request(base)
					.flatMap(jsonNode -> Mono.justOrEmpty(jsonNode.get("conversion_rates").get(target)))
					.map(currencyCode -> { 
						ObjectNode result = objectMapper.createObjectNode();
						result.put("currency", base+target);
						result.set("exchangeRate", currencyCode);
                    return result.toString();
                });
			
    }
		catch(Exception e) {
		    return null;
		    
		}
		
	}
	
}
