package com.prmgpregistrationsmi.integrationtest.gp2gp;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.gp2gp.RegistrationCompleted.RegistrationCompletedEvent;
import com.prmgpregistrationsmi.testhelpers.gp2gp.RegistrationCompletedEventBuilder;
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
class RegistrationCompletedEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Test
    void shouldUploadRegistrationCompletedEventToS3() {
        RegistrationCompletedEvent registrationCompletedEventRequest = RegistrationCompletedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO expectedS3UploadEvent = new EventDAO(
                registrationCompletedEventRequest.getEventId(),
                registrationCompletedEventRequest.getEventGeneratedDateTime(),
                EventType.REGISTRATION_COMPLETED,
                TransferProtocol.GP2GP,
                registrationCompletedEventRequest.getRegistrationId(),
                registrationCompletedEventRequest.getReportingSystemSupplier(),
                registrationCompletedEventRequest.getReportingPracticeOdsCode(),
                RegistrationCompletedEventBuilder.withDefaultRegistrationCompletedPayload().build()
        );

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/registration/" + API_VERSION + "/gp2gp/registrationCompleted",
                registrationCompletedEventRequest, EventResponse.class);

        EventResponse expectedResponse = new EventResponse(expectedS3UploadEvent.getEventId());
        assertEquals(expectedResponse.getEventId(), actualResponseEvent.getEventId());

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                String.format("v1/1970/01/01/03/%s.json", registrationCompletedEventRequest.getEventId()),
                expectedS3UploadEvent.toString()
        );
    }
}
