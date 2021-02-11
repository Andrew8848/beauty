package com.spring.fixer;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.spring.dao.FixerRest;

@Service
public class FixerFactory {

	private String path;
	private Logger log = LoggerFactory.getLogger(FixerFactory.class);

	private boolean refresingRatesIsStopped = true;

	public FixerFactory() {
		super();
		this.path = "src/main/resources/exchange-rates-eur.json";
		this.refresingRatesIsStopped = false;
	}
	
	public FixerFactory(File path) {
		super();
		this.path = "src/main/resources/exchange-rates-eur.json";
		this.refresingRatesIsStopped = false;
	}
	
	public FixerFactory(File path, Logger log) {
		super();
		this.log = log;
		this.refresingRatesIsStopped = false;
	}

	@Async("refreshingCurrentRates")
	public Future<String> setDynamicExchangeRate() throws IOException {
		while(!this.refresingRatesIsStopped) {
			try {
				while(!refreshRates());
				log.info("CURRENT RATE REFRESHED");
				TimeUnit.HOURS.sleep(8);
			} catch (InterruptedException e) {
				log.error("ERROR REFRESHING CURRENT RATE");
				e.printStackTrace();
			}
		}
		return new AsyncResult<String>("finish");
	}

	public boolean refreshRates() throws IOException {
		Fixer fixer = new FixerRest().getCurrentRates();
		if(fixer.isSuccess()) {
			Writer writer = Files.newBufferedWriter(Paths.get(path));
			Gson gson = new Gson();
			gson.toJson(fixer, writer);
			writer.close();
			return true;
		} else{
			return false;
		}
	}
	
	public void setCurrentRate(Fixer fixer) throws IOException {
		Writer writer = Files.newBufferedWriter(Paths.get(path));
		Gson gson = new Gson();
		gson.toJson(fixer, writer);
		log.info("CURRENT RATE SETTING");
		writer.close();
	}

	public Fixer getCurrentRate() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(path));
		return new Gson().fromJson(reader, Fixer.class);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}
	
	
}
