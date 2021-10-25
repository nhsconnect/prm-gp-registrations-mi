package com.prmgpregistrationsmi.exception;

public class UnableToUploadToS3Exception extends Exception {
    public UnableToUploadToS3Exception(Exception e) {
        super("Unable to upload to S3: " + e.getMessage());
    }
}
