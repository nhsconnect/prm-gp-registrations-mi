package com.prmgpregistrationsmi.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event;
import com.prmgpregistrationsmi.model.EventDAO;
import com.prmgpregistrationsmi.model.EventType;
import com.prmgpregistrationsmi.utils.JsonHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class S3FileUploaderTest {
    AmazonS3Client amazonS3Client = mock(AmazonS3Client.class);

    S3FileUploader s3FileUploader = new S3FileUploader(amazonS3Client, "test_bucket");

    @Test
    void shouldUploadEventToAmazonS3Location() throws UnableToUploadToS3Exception {
        EventDAO testEvent = EventDAO.fromEvent(Event.builder().eventId("event-id-12345").build(),
                EventType.GP2GP_REGISTRATION_STARTED);
        s3FileUploader.uploadObject(testEvent, "/2021/12/2/15/");

        String expectedOutputS3Key = "/2021/12/2/15/event-id-12345.json";


        verify(amazonS3Client, times(1)).putObject("test_bucket", expectedOutputS3Key,
                JsonHelper.asJsonString(testEvent));
    }


    @Test
    void shouldThrowExceptionWhenUnableToUploadEventToAmazonS3() {
        UnableToUploadToS3Exception exception = assertThrows(UnableToUploadToS3Exception.class, () -> {
            EventDAO testEvent = EventDAO.fromEvent(Event.builder().eventId("event-id-12345").build(),
                    EventType.GP2GP_REGISTRATION_STARTED);
            String expectedOutputFilename = "event-id-12345.json";

            when(amazonS3Client.putObject("test_bucket", expectedOutputFilename, JsonHelper.asJsonString(testEvent))).thenThrow(AmazonServiceException.class);

            s3FileUploader.uploadObject(testEvent, "");
        });

        String exceptionMessage = exception.getMessage();
        String expectedMessage = "Unable to upload to S3";

        assertTrue(exceptionMessage.contains(expectedMessage));
    }
}