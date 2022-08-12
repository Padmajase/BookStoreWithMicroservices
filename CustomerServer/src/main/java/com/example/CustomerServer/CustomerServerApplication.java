package com.example.CustomerServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class CustomerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServerApplication.class, args);
	}

	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
