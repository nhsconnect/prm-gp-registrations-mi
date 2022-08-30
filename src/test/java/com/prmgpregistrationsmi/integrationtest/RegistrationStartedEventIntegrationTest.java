package com.prmgpregistrationsmi.integrationtest;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.deprecated.Event.EventDAO;
import com.prmgpregistrationsmi.model.deprecated.Event.EventResponse;
import com.prmgpregistrationsmi.model.deprecated.Event.EventType;
import com.prmgpregistrationsmi.model.deprecated.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.testhelpers.EventDAOBuilder;
import com.prmgpregistrationsmi.testhelpers.preTransfer.RegistrationStartedEventBuilder;
import com.prmgpregistrationsmi.utils.UUIDService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

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

        EventDAO expectedS3UploadEvent = EventDAOBuilder.withEvent(registrationStartedEventRequest)
                .eventId(UUIDService.buildUUIDStringFromSeed(
                        registrationStartedEventRequest.getConversationId() +
                                EventType.REGISTRATION_STARTED +
                                registrationStartedEventRequest.getEventGeneratedDateTime().toString())
                )
                .eventType(EventType.REGISTRATION_STARTED)
                .transferProtocol(TransferProtocol.PRE_TRANSFER)
                .build();

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/registrationStarted", registrationStartedEventRequest, EventResponse.class);

        assertEquals(expectedS3UploadEvent.getEventId(), actualResponseEvent.getEventId());

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                String.format("v1/1970/01/01/03/%s.json", expectedS3UploadEvent.getEventId()),
                expectedS3UploadEvent.toString()
        );
    }
}
