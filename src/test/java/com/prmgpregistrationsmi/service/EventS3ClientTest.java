package com.prmgpregistrationsmi.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.Event;
import com.prmgpregistrationsmi.model.EventDAO;
import com.prmgpregistrationsmi.model.EventType;
import org.junit.jupiter.api.Test;
import com.prmgpregistrationsmi.utils.JsonHelper;

import static org.mockito.Mockito.*;

class EventS3ClientTest {
    AmazonS3Client amazonS3Client = mock(AmazonS3Client.class);

    S3FileUploader s3FileUploader = new S3FileUploader(amazonS3Client, "test_bucket");

    @Test
    void shouldUploadEventToAmazonS3(){

        EventDAO testEvent = EventDAO.fromEvent(Event.builder().eventId("event-id-12345").build(), EventType.GP2GP_REGISTRATION_STARTED);
        s3FileUploader.uploadObject(testEvent);

        String expectedOutputFilename = "event-id-12345.json";

        verify(amazonS3Client, times(1)).putObject("test_bucket", expectedOutputFilename, JsonHelper.asJsonString(testEvent));
    }
}