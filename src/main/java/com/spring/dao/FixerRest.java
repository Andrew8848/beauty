package com.spring.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spring.fixer.Fixer;

public class FixerRest {

	static final Properties prop = new Properties();
	private Logger log = LoggerFactory.getLogger(DataStoreREST.class);
	private String path;
	
	private static String URL;

	private static final String LATEST = "latest";
	
	public FixerRest() throws FileNotFoundException, IOException {	
		this.path = "src/main/resources/fixer.properties";
		prop.load(new FileInputStream(this.path));
		URL = prop.getProperty("fixer.url");
	}
	
	public Fixer getCurrentRates() {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("my_other_key", "my_other_value");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("access_key", prop.getProperty("fixer.access.key"));

		HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(map, headers);

		ResponseEntity<String> response = restTemplate.exchange(URL+LATEST+"?access_key=" + prop.getProperty("fixer.access.key"), HttpMethod.GET, request, String.class);
				
		JsonObject json = new Gson().fromJson(response.getBody(), JsonObject.class);
        
        return new Gson().fromJson(json, Fixer.class);
   	}

}
