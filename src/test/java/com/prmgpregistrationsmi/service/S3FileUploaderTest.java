package com.prmgpregistrationsmi.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.utils.JsonHelper;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class S3FileUploaderTest {
    AmazonS3Client amazonS3Client = mock(AmazonS3Client.class);

    S3FileUploader s3FileUploader = new S3FileUploader(amazonS3Client, "test_bucket");

    private JSONObject generateTestObject() {
        JSONObject testObject = new JSONObject();
        testObject.put("bool", true);
        testObject.put("else",2);
        testObject.put("something", "value");
        return testObject;
    }

    @Test
    void shouldUploadObjectToAmazonS3Location() throws UnableToUploadToS3Exception {
        JSONObject testObject = generateTestObject();
        String outputS3Key = "/2021/12/2/15/object.json";

        s3FileUploader.uploadJsonObject(testObject, outputS3Key);

        verify(amazonS3Client, times(1)).putObject("test_bucket", outputS3Key,
                JsonHelper.asJsonString(testObject));
    }


    @Test
    void shouldThrowExceptionWhenUnableToUploadObjectToAmazonS3() {
        UnableToUploadToS3Exception exception = assertThrows(UnableToUploadToS3Exception.class, () -> {
            JSONObject testObject = generateTestObject();
            String outputS3Key = "object.json";

            when(amazonS3Client.putObject("test_bucket", outputS3Key, JsonHelper.asJsonString(testObject))).thenThrow(AmazonServiceException.class);

            s3FileUploader.uploadJsonObject(testObject, outputS3Key);
        });

        String exceptionMessage = exception.getMessage();
        String expectedMessage = "Unable to upload to S3";

        assertTrue(exceptionMessage.contains(expectedMessage));
    }
}