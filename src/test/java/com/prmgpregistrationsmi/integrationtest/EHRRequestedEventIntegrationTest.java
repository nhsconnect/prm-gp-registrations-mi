package com.prmgpregistrationsmi.integrationtest;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.EhrRequested.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.EhrRequested.EhrRequestedPayload;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.testhelpers.EhrRequestedEventBuilder;
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
class EHRRequestedEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Test
    void shouldUploadEHRRequestedEventToS3() {
        EhrRequestedEvent ehrRequestedEventRequest = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .eventId("event-id-test")
                .eventGeneratedTimestamp(315130L)
                .registrationId("registration-id-test-12345")
                .reportingSystemSupplier("system-a")
                .reportingPracticeOdsCode("A12345")
                .build();

        EhrRequestedPayload ehrRequestedPayload = EhrRequestedEventBuilder
                .withDefaultEhrRequestedPayload()
                .build();

        EventDAO expectedS3UploadEvent = new EventDAO(
                "event-id-test",
                315130L,
                EventType.EHR_REQUESTED,
                "registration-id-test-12345",
                "system-a",
                "A12345",
                ehrRequestedPayload
        );

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/registration/" + API_VERSION + "/ehrRequested", ehrRequestedEventRequest, EventResponse.class);

        EventResponse expectedResponse = new EventResponse("event-id-test");
        assertEquals(expectedResponse, actualResponseEvent);

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                "v1/1970/01/04/15/event-id-test.json",
                expectedS3UploadEvent.toString()
        );
    }
}
