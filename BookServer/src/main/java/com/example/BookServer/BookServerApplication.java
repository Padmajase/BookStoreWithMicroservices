package com.example.BookServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class BookServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServerApplication.class, args);
	}

	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
