package com.prmgpregistrationsmi.integrationtest;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.EhrGenerated.EhrGeneratedEvent;
import com.prmgpregistrationsmi.model.EhrGenerated.EhrGeneratedPayload;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.testhelpers.EhrGeneratedEventBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static com.prmgpregistrationsmi.controller.GP2GPController.API_VERSION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EHRGeneratedEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Test
    void shouldUploadEHRGeneratedEventToS3() {
        EhrGeneratedEvent ehrGeneratedEventRequest = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO expectedS3UploadEvent = new EventDAO(
                ehrGeneratedEventRequest.getEventId(),
                ehrGeneratedEventRequest.getEventGeneratedTimestamp(),
                EventType.EHR_GENERATED,
                ehrGeneratedEventRequest.getRegistrationId(),
                ehrGeneratedEventRequest.getReportingSystemSupplier(),
                ehrGeneratedEventRequest.getReportingPracticeOdsCode(),
                ehrGeneratedEventRequest.getPayload()
        );

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/registration/" + API_VERSION + "/ehrGenerated", ehrGeneratedEventRequest, EventResponse.class);

        EventResponse expectedResponse = new EventResponse(expectedS3UploadEvent.getEventId());
        assertEquals(expectedResponse, actualResponseEvent);

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                String.format("v1/1970/01/01/03/%s.json", ehrGeneratedEventRequest.getEventId()),
                expectedS3UploadEvent.toString()
        );
    }
}
