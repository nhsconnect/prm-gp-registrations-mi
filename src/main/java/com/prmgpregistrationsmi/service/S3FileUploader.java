package com.prmgpregistrationsmi.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import static com.prmgpregistrationsmi.utils.JsonHelper.asJsonString;

@Slf4j
@Repository
@SuppressFBWarnings(value = "EI_EXPOSE_REP2")
public class S3FileUploader {
    private final String outputBucketLocation;
    private final AmazonS3Client amazonS3Client;

    public S3FileUploader(final AmazonS3Client amazonS3Client, @Value("${output_bucket}") String outputBucketLocation) {
        this.amazonS3Client = amazonS3Client;
        this.outputBucketLocation = outputBucketLocation;
    }

    public void uploadJsonObject(Object object, String s3Key) throws UnableToUploadToS3Exception {
        String jsonString = asJsonString(object);
        log.info("Uploading object to S3: " + jsonString + " to location: " + s3Key);

        try {
            amazonS3Client.putObject(outputBucketLocation, s3Key, jsonString);
            log.info("Successfully uploaded JSON to S3");
        } catch (AmazonClientException amazonClientException) {
            throw new UnableToUploadToS3Exception(amazonClientException);
        }
    }
}