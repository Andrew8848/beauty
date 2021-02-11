package com.example.demo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.spring.dao.DataStoreREST;
import com.spring.dao.FixerRest;
import com.spring.fixer.Fixer;
import com.spring.fixer.FixerFactory;
import com.spring.model.Product;

import core.google.datastore.forms.ArrayValue;
import core.google.datastore.forms.Value;
import core.google.datastore.functions.ConverterFromGoogleValue;

@RunWith(JUnit4.class)
public class DemoApplicationTests {

	@Test
	public void contextLoads() throws FileNotFoundException, IOException {
//		String[] stringArray = {"string", "string2"};
//		Value value = new Value(ArrayValue.stringType(Arrays.asList(stringArray)));
		
		DataStoreREST data = new DataStoreREST();
//		data.deleteEntityById(1, "ProductInfo", "Beauty");
//		data.setCurrentIds(0, "ProductInfo", "Beauty");
//	    System.out.println(data.getIdsAmount(1, "ProductInfo", "Beauty"));
//		Object product = data.getEntityById(4, "Product", "Test");
		
		Map<String, String> name = new HashMap();
		name.put("en", "Russian name");
		name.put("ru", "English name");
		name.put("uk", "Ukreanian name");
		Map<String, String> brand = new HashMap();
		brand.put("en", "Russian brand");
		brand.put("ru", "English brand");
		brand.put("uk", "Ukreanian brand");
		Map<String, String> description = new HashMap();
		description.put("en", "Russian description");
		description.put("ru", "English description");
		description.put("uk", "Ukreanian description");
		Map<String, String> applying = new HashMap();
		applying.put("en", "Russian applying");
		applying.put("ru", "English applying");
		applying.put("uk", "Ukreanian applying");
		Map<String, String> madeIn = new HashMap();
		madeIn.put("en", "Russian madeIn");
		madeIn.put("ru", "English madeIn");
		madeIn.put("uk", "Ukreanian madeIn");
		Map<String, String> countryTM = new HashMap();
		countryTM.put("en", "Russian countryTM");
		countryTM.put("ru", "English countryTM");
		countryTM.put("uk", "Ukreanian countryTM");
		Map<String, String> appointment = new HashMap();
		appointment.put("en", "Russian appointment");
		appointment.put("ru", "English appointment");
		appointment.put("uk", "Ukreanian appointment");
		Map<String, String> applicationTime = new HashMap();
		applicationTime.put("en", "Russian applicationTime");
		applicationTime.put("ru", "English applicationTime");
		applicationTime.put("uk", "Ukreanian applicationTime");
		Map<String, String> gender = new HashMap();
		gender.put("en", "Russian gender");
		gender.put("ru", "English gender");
		gender.put("uk", "Ukreanian gender");
		Map<String, String> classification = new HashMap();
		classification.put("en", "Russian classification");
		classification.put("ru", "English classification");
		classification.put("uk", "Ukreanian classification");
		
		Fixer ffixer = new FixerFactory().getCurrentRate();
		String[] rates = {"EUR"};
		
		Product product = new Product(new String[]{"2342345tresg4324523et2weater"},
				ffixer.getFormatedRank(140, Arrays.asList(rates)),
				new String[]{"Nutrition", "Moisturizing"},
				name,
				brand,
				new String[] {"150ml"},
				"18+",
				description,
				applying,
				madeIn,
				countryTM,
				appointment,
				applicationTime,
				gender,
				classification);
//
//		
		data.setEntity(product);
		
//	    ConverterFromGoogleValue map = new ConverterFromGoogleValue(new ObjectMapper().convertValue(product, Map.class));
//	    Object obj = map.toSimplify();
//		Product p = new Gson().fromJson(new Gson().toJson(product), Product.class);
//	    System.out.println(data.getEntityById(4, "Product", "Test").toString());
		
//		FixerRest f = new FixerRest();
//		System.out.println((Object) f.getCurrentRates());

//		Fixer f = new FixerFactory().getCurrentRate();
//		
//		List rates = new ArrayList<String>();
//		rates.add("EUR");
//		rates.add("UAH");
//		rates.add("RUB");
//		rates.add("USD");
//		System.out.println(f.getFormatedRank(3, rates));
//		
//		DecimalFormatSymbols separate = new DecimalFormatSymbols(Locale.US);
//		DecimalFormat df = new DecimalFormat("#.##", separate);
//		System.out.println(df.format(67.23894727489));
	}
	
//	 private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();
//
//	    public static boolean isWrapperType(Class<?> clazz)
//	    {
//	        return WRAPPER_TYPES.contains(clazz);
//	    }
//
//	    private static Set<Class<?>> getWrapperTypes()
//	    {
//	        Set<Class<?>> type = new HashSet<Class<?>>();
//	        type.add(Boolean.class);
//	        type.add(Character.class);
//	        type.add(Byte.class);
//	        type.add(Short.class);
//	        type.add(Integer.class);
//	        type.add(Long.class);
//	        type.add(Float.class);
//	        type.add(Double.class);
//	        type.add(Void.class);
//	        type.add(String.class);
//	        return type;
//	    }
	
}
