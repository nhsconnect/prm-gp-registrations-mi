package com.prmgpregistrationsmi;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static com.prmgpregistrationsmi.controller.RegistrationController.API_VERSION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UploadEventToS3IntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Test
    void eventToS3Uploaded() {
        RegistrationStartedDetails registrationStartedDetails = RegistrationStartedDetails.builder()
                .registrationStartedTimestamp(313130L)
                .registrationType("newRegistrant")
                .requestingPracticeOdsCode("B12345").build();

        RegistrationStartedPayload registrationStartedPayload = RegistrationStartedPayload.builder()
                .registration(registrationStartedDetails).build();

        RegistrationStartedEvent registrationStartedEventRequest = RegistrationStartedEvent.builder()
                .eventGeneratedTimestamp(315130L)
                .registrationId("registration-id-test-12345")
                .reportingSystemSupplier("system-a")
                .reportingPracticeOdsCode("A12345")
                .eventId("event-id-test")
                .payload(registrationStartedPayload).build();

        EventDAO expectedS3UploadEvent = new EventDAO(
                "event-id-test",
                315130L,
                EventType.GP2GP_REGISTRATION_STARTED,
                "registration-id-test-12345",
                "system-a",
                "A12345",
                registrationStartedPayload
        );

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/registration/" + API_VERSION + "/gp2gpRegistrationStarted", registrationStartedEventRequest, EventResponse.class);

        EventResponse expectedResponse = new EventResponse("event-id-test");
        assertEquals(expectedResponse, actualResponseEvent);

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                "v1/1970/01/04/15/event-id-test.json",
                expectedS3UploadEvent.toString()
        );
    }
}
