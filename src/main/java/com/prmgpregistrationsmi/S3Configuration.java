package com.prmgpregistrationsmi;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class S3Configuration {
    @Bean
    public static AmazonS3Client amazonS3Client() {
        String AWS_REGION = "eu-west-2";
        return (AmazonS3Client) AmazonS3ClientBuilder
                .standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(AWS_REGION)
                .build();
    }
}
