package com.spring.asyncconfig;

import java.util.concurrent.Executor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
	
	@Bean(name = "refreshingToken")
	public Executor refreshingToken() {
		ThreadPoolTaskExecutor thread = new ThreadPoolTaskExecutor();
		thread.setMaxPoolSize(1);
		thread.setThreadNamePrefix("Token Thread: ");
		thread.initialize();
		return thread;
	}
	
	@Bean(name = "refreshingCurrentRates")
	public Executor refreshingCurrentRates() {
		ThreadPoolTaskExecutor thread = new ThreadPoolTaskExecutor();
		thread.setMaxPoolSize(1);
		thread.setThreadNamePrefix("Fixer Thread: ");
		thread.initialize();
		return thread;
	}
	
}
