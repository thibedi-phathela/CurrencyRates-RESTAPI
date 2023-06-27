package io.exchangeratesapi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class ApiController {
	
	@Autowired
	private ApiService apiService;
	
	@RequestMapping("/rates/{base}")
	public Mono<String> getAllRates(@PathVariable String base){
		return apiService.getAllRates(base);		
	}
	
	@RequestMapping("/rate/{base}/{target}")
	public Mono<String> getRate(@PathVariable String target, @PathVariable String base){
		return apiService.getRate(base,target);
	}
	
}
