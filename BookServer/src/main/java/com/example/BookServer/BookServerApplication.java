package com.example.BookServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BookServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServerApplication.class, args);
	}

}
