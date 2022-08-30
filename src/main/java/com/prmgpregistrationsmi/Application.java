package com.prmgpregistrationsmi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

import static com.prmgpregistrationsmi.logging.StructuredLogger.logger;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		logger.info("Application running..." );
	}

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
