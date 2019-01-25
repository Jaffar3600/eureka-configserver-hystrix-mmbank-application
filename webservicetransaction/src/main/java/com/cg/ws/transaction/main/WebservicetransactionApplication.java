package com.cg.ws.transaction.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@EnableDiscoveryClient
@SpringBootApplication
public class WebservicetransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebservicetransactionApplication.class, args);
	}

	
	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}
	
	
	
	
}

