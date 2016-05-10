package com.smartYummy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartYummyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartYummyApplication.class, args);
	}
}
