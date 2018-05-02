package com.example.myapi.demo.controller;

import org.json.simple.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.myapi.demo.model.Holiday;
import com.example.myapi.demo.model.HolidayResponse;

@RestController
public class HolidayController {
	@SuppressWarnings("unchecked")
	@RequestMapping("/holidays/{country1}/{country2}/{date}")
	public Object getHoliday(@PathVariable String country1,@PathVariable String country2, @PathVariable String date) {
		try {
		String url = "https://holidayapi.com/v1/holidays";
		String key = "c2c58492-8417-486a-9fb3-571c1ba15a31";
		String convDate = date.replace("-", "");
		String year = convDate.substring(0,4);
		String month = convDate.substring(4,6);
		String day = convDate.substring(6);
		JSONObject obj = new JSONObject();
		
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(url)
				.queryParam("key", key)		
				.queryParam("year", year)
				.queryParam("month", month)
				.queryParam("day", day);
		
		Holiday holiday1 = getHolidayByContry(builder,country1);
		Holiday holiday2 = getHolidayByContry(builder,country2);
		if(holiday1 != null && holiday2 != null) {
			obj.put("date", holiday1.getDate());
			obj.put(country1, holiday1.getName());
			obj.put(country2, holiday2.getName());
		}
		
		return obj;
		}catch(Exception e) {
			return null;
		}
	}
	
	private Holiday getHolidayByContry(UriComponentsBuilder builder,String country) {
		try {
			builder.queryParam("country", country);			
			RestTemplate restTemplate =  new RestTemplate();
			ResponseEntity<HolidayResponse> response = restTemplate.exchange(builder.toUriString(),HttpMethod.GET,null,HolidayResponse.class);
			return response.getBody().getHolidays().get(0);
		}catch(Exception e) {
			throw e;
		}
	}
	
}
