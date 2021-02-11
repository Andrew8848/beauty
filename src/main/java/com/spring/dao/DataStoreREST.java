package com.spring.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spring.fixer.Fixer;
import com.spring.fixer.FixerFactory;
import com.spring.model.Product;
import com.spring.security.OAuth2;

import core.google.datastore.forms.ArrayValue;
import core.google.datastore.forms.EntityValue;
import core.google.datastore.forms.Key;
import core.google.datastore.forms.Mutations;
import core.google.datastore.forms.PartitionId;
import core.google.datastore.forms.Path;
import core.google.datastore.forms.ReadOptions;
import core.google.datastore.forms.Value;
import core.google.datastore.functions.ConverterFromGoogleValue;
import core.google.datastore.operations.Insert;

@Service
public class DataStoreREST {

	static final Properties prop = new Properties();
	private Logger log = LoggerFactory.getLogger(DataStoreREST.class);
	private String path;

	private static String URL;

	private static final String BEGINTRANSACTION = ":beginTransaction";
	private static final String LOOKUP = ":lookup";
	private static final String COMMIT = ":commit";
	
	private static final String TRANSACTIONAL = "TRANSACTIONAL";
	private static final String MODE_UNSPECIFIED = "MODE_UNSPECIFIED";
	private static final String NON_TRANSACTIONAL = "NON_TRANSACTIONAL";

	private OAuth2 oauth;

	public DataStoreREST() throws FileNotFoundException, IOException {	
		this.oauth = new OAuth2();
		this.path = "src/main/resources/datastore.properties";
		prop.load(new FileInputStream(this.path));
		URL = prop.getProperty("db.url");
	}
	
	private Integer getAndToUpdateId() throws IOException {
		Integer idsAmount =  Integer.parseInt(prop.getProperty("product.info.ids")) + 1;
        prop.setProperty("product.info.ids", idsAmount.toString());
        prop.store(new FileOutputStream(this.path), null);
	    log.info("Updating ids: " + Integer.parseInt(prop.getProperty("product.info.ids")));
		return Integer.parseInt(prop.getProperty("product.info.ids"));
	}
	
	public Integer getLastId() {
		return Integer.parseInt(prop.getProperty("product.info.ids"));
	}
	
	public String BeginTransaction() throws IOException {	

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("my_other_key", "my_other_value");
		headers.setBearerAuth(this.oauth.getAccessToken());

		MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();

		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

		ResponseEntity<String> response = restTemplate.exchange(URL+BEGINTRANSACTION, HttpMethod.POST, request , String.class);
		JsonObject json = new Gson().fromJson(response.getBody(), JsonObject.class);

		return json.get("transaction").getAsString();
	}
	
	
	public Product getEntityById(Integer id, String type, String namespaceId) throws IOException {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("my_other_key", "my_other_value");
		headers.setBearerAuth(this.oauth.getAccessToken());

		ReadOptions readOption = new ReadOptions(this.BeginTransaction());

		List<Path> path = new ArrayList<Path>();
		path.add(new Path(id, "Product", null));

		Key key = new Key(new PartitionId(namespaceId, prop.getProperty("db.project.id")), path);

		List<Object> keys = new ArrayList<Object>();
		keys.add(key);	

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("readOptions", readOption);
		map.put("keys", keys);

		HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(map, headers);

		ResponseEntity<String> response = restTemplate.exchange(URL+LOOKUP, HttpMethod.POST, request, String.class);
				
		JsonObject json = new Gson().fromJson(response.getBody(), JsonObject.class);
		
		System.out.println(json.toString());
		
		Object properties = new Gson().fromJson(json.get("found").getAsJsonArray().get(0).getAsJsonObject()
				.get("entity").getAsJsonObject().get("properties").toString(), Object.class);
        
        ConverterFromGoogleValue simplifyBody = new ConverterFromGoogleValue(new ObjectMapper().convertValue(properties, Map.class));
        System.out.println(simplifyBody.toSimplify());
        return new Gson().fromJson(new Gson().toJson(simplifyBody.toSimplify()), Product.class);
	}
	
	public List getProductByRangeOfIndexes(Integer startIndex, Integer lastIndex, String type, String namespaceId) throws IOException {
		List<Product> array = new ArrayList<Product>();
		int currentLastIndex = lastIndex;
		if(Integer.parseInt(prop.getProperty("product.info.ids")) <= lastIndex) {
			currentLastIndex = Integer.parseInt(prop.getProperty("product.info.ids"));
		}
		for(int i = startIndex; i <= currentLastIndex; i++) {
			array.add(getEntityById(i, type, namespaceId));
		}
		return array;
	}

	public String setEntity(Product product) throws IOException {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("my_other_key", "my_other_value");
		headers.setBearerAuth(this.oauth.getAccessToken());
		
		String[] rates = {"EUR", "UAH", "RUB", "USD"};
		Fixer fixer = new FixerFactory().getCurrentRate();
		
		Map<String, Value> properties = new HashMap<String, Value>();
		properties.put("base64", new Value(ArrayValue.stringType(Arrays.asList(product.getBase64()))));
		properties.put("price", new Value(EntityValue.DoubleType(fixer.getFormatedRank(product.getPrice().get("EUR"), Arrays.asList(rates)))));
		properties.put("type", new Value(ArrayValue.stringType(Arrays.asList(product.getType()))));
		properties.put("name", new Value(EntityValue.stringType(product.getName())));
		properties.put("brand", new Value(EntityValue.stringType(product.getBrand())));
		properties.put("volume", new Value(ArrayValue.stringType(Arrays.asList(product.getVolume()))));
		properties.put("age", new Value(product.getAge()));
		properties.put("description", new Value(EntityValue.stringType(product.getDescription())));
		properties.put("applying", new Value(EntityValue.stringType(product.getApplying())));
		properties.put("madeIn", new Value(EntityValue.stringType(product.getMadeIn())));
		properties.put("countryTM", new Value(EntityValue.stringType(product.getCountryTM())));
		properties.put("appointment", new Value(EntityValue.stringType(product.getAppointment())));
		properties.put("applicationTime", new Value(EntityValue.stringType(product.getApplicationTime())));
		properties.put("gender", new Value(EntityValue.stringType(product.getGender())));
		properties.put("classification", new Value(EntityValue.stringType(product.getClassification())));

		List<Path> path = new ArrayList<Path>();
//		path.add(new Path(getAndToUpdateId(), "Product", null));
		path.add(new Path(4, "Product", null));

		
		Key key = new Key(new PartitionId("Test", prop.getProperty("db.project.id")), path);

		List<Mutations> mutations = new ArrayList<Mutations>();
		mutations.add(new Mutations(new Insert(properties, key), null, null, null));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mutations", mutations);
		map.put("mode", TRANSACTIONAL);
		map.put("transaction", this.BeginTransaction());

		HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(map, headers);

		ResponseEntity<String> response = restTemplate.exchange("http://localhost:3000/posts", HttpMethod.POST, request, String.class);
		
		log.info(response.getBody().toString());
		
		return null;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) throws FileNotFoundException, IOException {
		this.path = path;
	}

}

