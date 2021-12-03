package com.prmgpregistrationsmi.integrationtest;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.EhrGenerated.EhrGeneratedEvent;
import com.prmgpregistrationsmi.model.EhrGenerated.EhrGeneratedPayload;
import com.prmgpregistrationsmi.model.EhrRequested.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.EhrRequested.EhrRequestedPayload;
import com.prmgpregistrationsmi.model.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.model.RegistrationStarted.RegistrationStartedPayload;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.testhelpers.EhrGeneratedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.EhrRequestedEventBuilder;
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
class UploadEventToS3IntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Test
    void shouldUploadRegistrationStartedEventToS3WhenPostRequestIsMadeGivenValidEvent() {
        RegistrationStartedEvent registrationStartedEventRequest = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .eventId("event-id-test")
                .eventGeneratedTimestamp(315130L)
                .registrationId("registration-id-test-12345")
                .reportingSystemSupplier("system-a")
                .reportingPracticeOdsCode("A12345")
                .build();

        RegistrationStartedPayload registrationStartedPayload = RegistrationStartedEventBuilder
                .withDefaultRegistrationStartedPayload()
                .build();


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

    @Test
    void shouldUploadEHRRequestedEventToS3WhenPostRequestIsMadeGivenValidEvent() {
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


    @Test
    void shouldUploadEHRGeneratedEventToS3WhenPostRequestIsMadeGivenValidEvent() {
        EhrGeneratedEvent ehrGeneratedEventRequest = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .eventId("event-id-test")
                .eventGeneratedTimestamp(315130L)
                .registrationId("registration-id-test-12345")
                .reportingSystemSupplier("system-a")
                .reportingPracticeOdsCode("A12345")
                .build();

        EhrGeneratedPayload ehrGeneratedPayload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedPayload()
                .build();

        EventDAO expectedS3UploadEvent = new EventDAO(
                "event-id-test",
                315130L,
                EventType.EHR_GENERATED,
                "registration-id-test-12345",
                "system-a",
                "A12345",
                ehrGeneratedPayload
        );

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/registration/" + API_VERSION + "/ehrGenerated", ehrGeneratedEventRequest, EventResponse.class);

        EventResponse expectedResponse = new EventResponse("event-id-test");
        assertEquals(expectedResponse, actualResponseEvent);

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                "v1/1970/01/04/15/event-id-test.json",
                expectedS3UploadEvent.toString()
        );
    }
}
