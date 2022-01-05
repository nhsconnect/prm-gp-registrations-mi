package com.prmgpregistrationsmi.integrationtest.gp2gp;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.gp2gp.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.model.gp2gp.RegistrationStarted.RegistrationStartedPayload;
import com.prmgpregistrationsmi.testhelpers.gp2gp.RegistrationStartedEventBuilder;
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
                .build();

        RegistrationStartedPayload registrationStartedPayload = RegistrationStartedEventBuilder
                .withDefaultRegistrationStartedPayload()
                .build();

        EventDAO expectedS3UploadEvent = new EventDAO(
                registrationStartedEventRequest.getEventId(),
                registrationStartedEventRequest.getEventGeneratedTimestamp(),
                EventType.REGISTRATION_STARTED,
                registrationStartedEventRequest.getRegistrationId(),
                registrationStartedEventRequest.getReportingSystemSupplier(),
                registrationStartedEventRequest.getReportingPracticeOdsCode(),
                registrationStartedPayload
        );

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/registration/" + API_VERSION + "/gp2gp/registrationStarted", registrationStartedEventRequest, EventResponse.class);

        EventResponse expectedResponse = new EventResponse(expectedS3UploadEvent.getEventId());
        assertEquals(expectedResponse, actualResponseEvent);

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                String.format("v1/1970/01/01/03/%s.json", registrationStartedEventRequest.getEventId()),
                expectedS3UploadEvent.toString()
        );
    }
}
