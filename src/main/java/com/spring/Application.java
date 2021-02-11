package com.spring;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.spring.dao.DataStoreREST;
import com.spring.fixer.FixerFactory;
import com.spring.model.Product;
import com.spring.model.ProductForm;
import com.spring.security.OAuth2;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	OAuth2 oa;
	
	@Autowired
	FixerFactory ff;
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
	    DataStoreREST dbrest = new DataStoreREST();
		dbrest.setPath("src/main/resources/datastore.properties");
		//Long i = (long) 1; 
        //System.out.println(dbrest.setEntity(new Product(1, 140, new String[]{"Nutrition", "Moisturizing"}, "Coconut body oil, brand", "Elit-Lab", "150ml", "18+", "Coconut oil is a unique natural product", "Body care", "Ukraine", "Ukraine", "nutrition, hydration", "universal", "for women", "professional")));
	    //System.out.println(dbrest.getEntityById((long)6, "Product", "Beauty"));
	}

	@Override
	public void run(String... args) throws Exception {
		oa.setProp("src/main/resources/OAuth.properties");
		Future<String> authBackground = oa.setRefreshingToken();
		ff.setPath("src/main/resources/exchange-rates-eur.json");
		Future<String> fixerBackground = ff.setDynamicExchangeRate();
	}

}
