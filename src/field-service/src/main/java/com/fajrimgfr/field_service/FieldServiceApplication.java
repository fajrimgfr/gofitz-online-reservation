package com.fajrimgfr.field_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.fajrimgfr.field_service")
public class FieldServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FieldServiceApplication.class, args);
	}

}
