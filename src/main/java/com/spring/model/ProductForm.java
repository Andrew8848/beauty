package com.spring.model;

import java.util.Base64;
import java.util.Map;

public class ProductForm {
	private String[] base64;
	private Map<String, Double> price;
	private String[] type;
	private Map<String, String> name;
	private Map<String, String> brand;
	private String[] volume;
	private String age;
	private Map<String, String> description;
	private Map<String, String> applying;
	private Map<String, String> madeIn;
	private Map<String, String> countryTM;
	private Map<String, String> appointment;
	private Map<String, String> applicationTime;
	private Map<String, String> gender;
	private Map<String, String> classification;
	
	public String[] getBase64() {
		return base64;
	}
	public void setBase64(String[] base64) {
		this.base64 = base64;
	}
	public Map<String, Double> getPrice() {
		return price;
	}
	public void setPrice(Map<String, Double> price) {
		this.price = price;
	}
	public String[] getType() {
		return type;
	}
	public void setType(String[] type) {
		this.type = type;
	}
	public Map<String, String> getName() {
		return name;
	}
	public void setName(Map<String, String> name) {
		this.name = name;
	}
	public Map<String, String> getBrand() {
		return brand;
	}
	public void setBrand(Map<String, String> brand) {
		this.brand = brand;
	}
	public String[] getVolume() {
		return volume;
	}
	public void setVolume(String[] volume) {
		this.volume = volume;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Map<String, String> getDescription() {
		return description;
	}
	public void setDescription(Map<String, String> description) {
		this.description = description;
	}
	public Map<String, String> getApplying() {
		return applying;
	}
	public void setApplying(Map<String, String> applying) {
		this.applying = applying;
	}
	public Map<String, String> getMadeIn() {
		return madeIn;
	}
	public void setMadeIn(Map<String, String> madeIn) {
		this.madeIn = madeIn;
	}
	public Map<String, String> getCountryTM() {
		return countryTM;
	}
	public void setCountryTM(Map<String, String> countryTM) {
		this.countryTM = countryTM;
	}
	public Map<String, String> getAppointment() {
		return appointment;
	}
	public void setAppointment(Map<String, String> appointment) {
		this.appointment = appointment;
	}
	public Map<String, String> getApplicationTime() {
		return applicationTime;
	}
	public void setApplicationTime(Map<String, String> applicationTime) {
		this.applicationTime = applicationTime;
	}
	public Map<String, String> getGender() {
		return gender;
	}
	public void setGender(Map<String, String> gender) {
		this.gender = gender;
	}
	public Map<String, String> getClassification() {
		return classification;
	}
	public void setClassification(Map<String, String> classification) {
		this.classification = classification;
	}
}
