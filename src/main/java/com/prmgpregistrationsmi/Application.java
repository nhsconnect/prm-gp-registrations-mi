package com.prmgpregistrationsmi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.prmgpregistrationsmi.logging.StructuredLogger.logger;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		logger.info("Application running..." );
	}

}
