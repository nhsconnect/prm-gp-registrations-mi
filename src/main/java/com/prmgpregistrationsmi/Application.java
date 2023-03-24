package com.prmgpregistrationsmi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.time.Clock;

import static com.prmgpregistrationsmi.logging.StructuredLogger.logger;
import static org.springframework.security.config.Customizer.withDefaults;

//@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@SpringBootApplication()
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
