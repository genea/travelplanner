package com.complexica.travelplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:travelplanner.properties")
public class TravelplannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelplannerApplication.class, args);
	}

}
