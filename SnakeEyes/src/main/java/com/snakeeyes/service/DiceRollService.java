package com.snakeeyes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class DiceRollService {	
	@Value("${random-number-format}")
	private String format;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String rollDice(final int num, final int min, final int max, final int col, final int base) {		
		final String uri = UriComponentsBuilder.newInstance()
			      .scheme("https")
			      .host("www.random.org/integers")
			      .path("/")
			      .query("num={num}")
			      .query("min={min}")
			      .query("max={max}")
			      .query("col={col}")
			      .query("base={base}")
			      .query("format={format}")
			      .buildAndExpand(num, min, max, col, base, format).toUriString();
		
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, getHeaders(), String.class);
		
		return response.getBody();
	}	
	
	private HttpEntity<String> getHeaders() {
		final HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "mozilla");
       
        final HttpEntity<String> entity = new HttpEntity<>(headers);
        
        return entity;
	}
}