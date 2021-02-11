package com.spring.restcontroller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spring.dao.DataStoreREST;
import com.spring.dao.ProductDAO;
import com.spring.model.Product;
import com.spring.model.ProductForm;

@CrossOrigin(origins = "http://localhost:4200")
@RestController //(value = "/api/products")
public class ProductRestController {

	@Autowired
	private ProductDAO prodactDAO;

	
//	@RequestMapping(value = "/api/products",
//			method = RequestMethod.GET,
//			produces = {MediaType.APPLICATION_JSON_VALUE,
//					MediaType.APPLICATION_XML_VALUE})
//	@ResponseBody
//	public List<Product> getProductBlock(){
//		List<Product> list = prodactDAO.getAllProducts();
//		return list;
//	}
	
	@RequestMapping(value="/api/products/lastId",
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public Integer getLastIdOfProducts() throws FileNotFoundException, IOException {
		DataStoreREST data = new DataStoreREST();
		return data.getLastId();
	}

	@RequestMapping(value="/api/products/{productId}",
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public Product getProductById(@PathVariable("productId") Integer productId) throws FileNotFoundException, IOException{
		DataStoreREST data = new DataStoreREST();
		return data.getEntityById(productId, "Product", "Beauty");
	}
	
	@RequestMapping(value="/api/products/{startIndex}/{lastIndex}",
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public List getProductByRangeOfIndexes(@PathVariable("startIndex") Integer startIndex, @PathVariable("lastIndex") Integer lastIndex) throws FileNotFoundException, IOException{
		DataStoreREST data = new DataStoreREST();
		return data.getProductByRangeOfIndexes(startIndex, lastIndex, "Product", "Beauty");
	}
	
	@RequestMapping(value = "/api/product/set", 
			method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	
	public String setProduct(@RequestBody Product product) throws FileNotFoundException, IOException {
		DataStoreREST data = new DataStoreREST();
		data.setEntity(product);
		return "Status: [OK]: " + product;
	}
	
	
}
