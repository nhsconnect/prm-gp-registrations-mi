package com.prmgpregistrationsmi.configuration;

import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import org.springframework.context.annotation.Bean;

@Configuration
public class SnsClientSpringConfiguration {
    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder().region(Region.EU_WEST_2).build();
    }
}
