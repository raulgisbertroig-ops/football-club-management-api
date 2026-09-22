package com.systemicr2.footballclubmanagementapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FootballManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballManagementApplication.class, args);
	}

}
