package com.zbodya.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;




@Service
public class ExchangeService 
{
	
	@Autowired
	private RestTemplate rest;
	
	public void getAllExchangeRatesFromYesterday()
	{
		ResponseEntity<JsonNode> json = rest.getForEntity("http://api.nbp.pl/api/exchangerates/tables/a/2021-08-20", JsonNode.class);		
		for(JsonNode node : json.getBody().findValue("rates")) 
		{
			System.out.println("Currency: " + node.path("currency").asText() + " code: " + node.path("code").asText() + " mid: " + node.path("mid").asText());
		}	
	}
	
	public void getExchangeRatesOfUSDollarForLast10Days() 
	{
		ResponseEntity<JsonNode> json = rest.getForEntity("http://api.nbp.pl/api/exchangerates/rates/a/usd/2021-08-11/2021-08-21/", JsonNode.class);
		System.out.println("Dollar rates for last 10 days:");
		for(JsonNode node : json.getBody().findValue("rates")) 
		{
			System.out.println("Effective date: " + node.path("effective_day").asText() + " code: " + node.path("code").asText() + " mid: " + node.path("mid").asText());
		}
	}
	
	
	
}
