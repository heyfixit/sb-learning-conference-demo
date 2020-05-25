package com.pluralsight.conferencedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Spring Boot is an opinionated rapid building application framework like django and rails


// Annotation - tells spring boot loader to run application as a spring/boot app and configure it like one
@SpringBootApplication
public class ConferenceDemoApplication {

	// Main entry point - how program is able to run standalone rather than deployed to container
	public static void main(String[] args) {
		SpringApplication.run(ConferenceDemoApplication.class, args);
	}

}
