package com.prmgpregistrationsmi.integrationtest.gp2gp;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.gp2gp.EhrValidated.EhrValidatedEvent;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrValidatedEventBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EhrValidatedEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Test
    void shouldUploadEhrValidatedEventToS3() {
        EhrValidatedEvent ehrValidatedEventRequest = EhrValidatedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO expectedS3UploadEvent = new EventDAO(
                ehrValidatedEventRequest.getEventId(),
                ehrValidatedEventRequest.getEventGeneratedDateTime(),
                EventType.EHR_VALIDATED,
                TransferProtocol.GP2GP,
                ehrValidatedEventRequest.getReportingSystemSupplier(),
                ehrValidatedEventRequest.getReportingPracticeOdsCode(),
                ehrValidatedEventRequest.getPayload()
        );

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/gp2gp/ehrValidated", ehrValidatedEventRequest, EventResponse.class);

        EventResponse expectedResponse = new EventResponse(expectedS3UploadEvent.getEventId());
        assertEquals(expectedResponse.getEventId(), actualResponseEvent.getEventId());

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                String.format("v1/1970/01/01/03/%s.json", ehrValidatedEventRequest.getEventId()),
                expectedS3UploadEvent.toString()
        );
    }
}