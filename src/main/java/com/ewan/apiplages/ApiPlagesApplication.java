package com.ewan.apiplages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


// @SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ApiPlagesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPlagesApplication.class, args);
	}

}
