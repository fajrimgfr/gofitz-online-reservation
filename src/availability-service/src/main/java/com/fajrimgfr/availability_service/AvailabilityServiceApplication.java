package com.fajrimgfr.availability_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.fajrimgfr.availability_service")
@EnableCaching
public class AvailabilityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvailabilityServiceApplication.class, args);
	}

}
