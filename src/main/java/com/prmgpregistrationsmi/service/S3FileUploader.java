package com.prmgpregistrationsmi.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.EventDAO;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import static com.prmgpregistrationsmi.utils.JsonHelper.asJsonString;

@Slf4j
@Repository
@SuppressFBWarnings(value = "EI_EXPOSE_REP2")
public class S3FileUploader {
    private static final String OUTPUT_EXTENSION = ".json";
    private final String outputBucketLocation;
    private final AmazonS3Client amazonS3Client;

    public S3FileUploader(final AmazonS3Client amazonS3Client, @Value("${output_bucket}") String outputBucketLocation) {
        this.amazonS3Client = amazonS3Client;
        this.outputBucketLocation = outputBucketLocation;
    }

    public void uploadObject(EventDAO eventDAO) throws UnableToUploadToS3Exception {
        final String eventId = eventDAO.getEventId();
        log.info("Uploading to S3 with eventID: " + eventId);

        try {
            amazonS3Client.putObject(outputBucketLocation, eventId + OUTPUT_EXTENSION, asJsonString(eventDAO));
            log.info("Successfully uploaded event to S3 with eventID: " + eventId);
        } catch (AmazonClientException amazonClientException) {
            throw new UnableToUploadToS3Exception(amazonClientException);
        }
    }
}