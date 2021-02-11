package com.spring.fixer;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Fixer {
	private boolean success;
	private long timestamp;
	private String base;
	private String date;
	
	private Map<String, Double> rates;
	
	public Fixer(long timestamp, String base, String date, Map<String, Double> rates) {
		super();
		this.success = true;
		this.timestamp = timestamp;
		this.base = base;
		this.date = date;
		this.rates = rates;
	}
	
	public Map<String, Double> getFormatedRank(double base, List<String> rates){ 
		DecimalFormatSymbols separate = new DecimalFormatSymbols(Locale.US);
		DecimalFormat df = new DecimalFormat("#.##", separate);
		return this.rates.entrySet().stream()
				.filter(f -> rates.contains(f.getKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, x -> Double.parseDouble(df.format(x.getValue()*base))));
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}
	
	

}
