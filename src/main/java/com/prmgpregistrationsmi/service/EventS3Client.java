package com.prmgpregistrationsmi.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prmgpregistrationsmi.model.EventDAO;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@SuppressFBWarnings(value = "EI_EXPOSE_REP2")
public class EventS3Client {
    private final String outputBucketLocation;
    private final AmazonS3Client amazonS3Client;

    public EventS3Client(final AmazonS3Client amazonS3Client, @Value("${output_bucket}") String outputBucketLocation) {
        this.amazonS3Client = amazonS3Client;
        this.outputBucketLocation = outputBucketLocation;
    }

    public void uploadObject(EventDAO eventDAO) {
        final String eventId = eventDAO.getEventId();
        log.info("Uploading to S3 with eventID: " + eventId);
        amazonS3Client.putObject(outputBucketLocation, eventId + ".json", asJsonString(eventDAO));
        log.info("Successfully uploaded event to S3 with eventID: " + eventId);
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}