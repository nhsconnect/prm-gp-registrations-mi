package com.prmgpregistrationsmi.integrationtest.gpc;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.gpc.EhrIntegrated.EhrIntegratedEvent;
import com.prmgpregistrationsmi.model.gpc.EhrIntegrated.EhrIntegratedPayload;
import com.prmgpregistrationsmi.testhelpers.gpc.EhrIntegratedEventBuilder;
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
class EhrIntegratedEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Test
    void shouldUploadEhrIntegratedEventToS3() {
        EhrIntegratedEvent ehrIntegratedEventRequest = EhrIntegratedEventBuilder
                .withDefaultEventValues()
                .build();

        EhrIntegratedPayload ehrIntegratedPayload = EhrIntegratedEventBuilder
                .withDefaultEhrIntegratedPayload()
                .build();

        EventDAO expectedS3UploadEvent = new EventDAO(
                ehrIntegratedEventRequest.getEventId(),
                ehrIntegratedEventRequest.getEventGeneratedDateTime(),
                EventType.EHR_INTEGRATED,
                TransferProtocol.GP_CONNECT,
                ehrIntegratedEventRequest.getRegistrationId(),
                ehrIntegratedEventRequest.getReportingSystemSupplier(),
                ehrIntegratedEventRequest.getReportingPracticeOdsCode(),
                ehrIntegratedPayload
        );

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/registration/" + API_VERSION + "/gpconnect/ehrIntegrated",
                ehrIntegratedEventRequest, EventResponse.class);

        EventResponse expectedResponse = new EventResponse(expectedS3UploadEvent.getEventId());
        assertEquals(expectedResponse.getEventId(), actualResponseEvent.getEventId());

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                String.format("v1/1970/01/01/03/%s.json", ehrIntegratedEventRequest.getEventId()),
                expectedS3UploadEvent.toString()
        );
    }
}
