package com.prmgpregistrationsmi.integrationtest;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.model.RegistrationStarted.RegistrationStartedPayload;
import com.prmgpregistrationsmi.testhelpers.RegistrationStartedEventBuilder;
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
class RegistrationStartedEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Test
    void shouldUploadRegistrationStartedEventToS3() {
        RegistrationStartedEvent registrationStartedEventRequest = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .eventId("event-id-test")
                .eventGeneratedTimestamp(315130L)
                .build();

        RegistrationStartedPayload registrationStartedPayload = RegistrationStartedEventBuilder
                .withDefaultRegistrationStartedPayload()
                .build();

        EventDAO expectedS3UploadEvent = new EventDAO(
                registrationStartedEventRequest.getEventId(),
                315130L,
                EventType.GP2GP_REGISTRATION_STARTED,
                registrationStartedEventRequest.getRegistrationId(),
                registrationStartedEventRequest.getReportingSystemSupplier(),
                registrationStartedEventRequest.getReportingPracticeOdsCode(),
                registrationStartedPayload
        );

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/registration/" + API_VERSION + "/gp2gpRegistrationStarted", registrationStartedEventRequest, EventResponse.class);

        EventResponse expectedResponse = new EventResponse("event-id-test");
        assertEquals(expectedResponse, actualResponseEvent);

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                String.format("v1/1970/01/04/15/%s.json", registrationStartedEventRequest.getEventId()),
                expectedS3UploadEvent.toString()
        );
    }
}