package com.systemicr2;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FootballManagementApplication {

	private static final Logger log = LoggerFactory.getLogger(FootballManagementApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FootballManagementApplication.class, args);

		log.info("¡Footballclubmanagement API ha arrancado correctamente! Fase 7 en marcha.");
	}

}
