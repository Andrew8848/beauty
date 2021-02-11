package com.spring.security;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


@Service
public class OAuth2{

	private Properties prop;
	private Logger log = LoggerFactory.getLogger(OAuth2.class);
	private boolean refreshingTokenIsStopped = true;
	private String path;
	
	public OAuth2() throws FileNotFoundException, IOException {
		this.prop = new Properties();
		this.path = "src/main/resources/OAuth.properties";
		this.prop.load(new FileInputStream(this.path));
		log.info("Setting OAuth path");
		this.refreshingTokenIsStopped = false;
	}

	@Async("refreshingToken")
	public Future<String> setRefreshingToken() throws IOException {
		while(!this.refreshingTokenIsStopped) {
			try {
				refreshToken();
				log.info("Token Refreshed");
				TimeUnit.MINUTES.sleep(50);
			} catch (InterruptedException e) {
				log.error("ERROR REFRESHING TOKEN");
				e.printStackTrace();
			}
		}
		return new AsyncResult<String>("finish");
	}

	private void refreshToken() {
		String url = prop.getProperty("refresfh.token.url");

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();
		map.add("client_id", prop.getProperty("client.id"));
		map.add("client_secret", prop.getProperty("client.secret"));
		map.add("refresh_token", prop.getProperty("refresh.token"));
		map.add("grant_type", "refresh_token");

		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request , String.class);
		JsonObject json = new Gson().fromJson(response.getBody(), JsonObject.class);
		prop.setProperty("access.token", json.get("access_token").toString());
		try {
			prop.store(new FileOutputStream(this.path), null);
		} catch (FileNotFoundException e) {
			log.error("OAuth File not found");
			e.printStackTrace();
		} catch (IOException e) {
			log.error("OAuth IO ERROR");
			e.printStackTrace();
		}

	}

	public void stopRefreshingToken() {
		this.refreshingTokenIsStopped = true;
	}
	
	public String getAccessToken() {
		return prop.getProperty("access.token");
	}

	public void setProp(String path) {
		this.prop = new Properties();
		try {
			this.path = path;
			prop.load(new FileInputStream(this.path));
			log.info("Setting OAuth path");
		} catch (IOException e) {
			log.error("ERROR Setting OAuth path");
			e.printStackTrace();
		}
	}



}

