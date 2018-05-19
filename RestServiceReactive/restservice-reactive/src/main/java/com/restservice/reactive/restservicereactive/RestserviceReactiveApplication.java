package com.restservice.reactive.restservicereactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@SpringBootApplication
public class RestserviceReactiveApplication {


	public static void main(String[] args) {
		SpringApplication.run(RestserviceReactiveApplication.class, args);
	}
}
