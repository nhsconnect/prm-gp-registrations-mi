package com.prmgpregistrationsmi.integrationtest.preTransfer;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.preTransfer.PdsGeneralUpdate.PdsGeneralUpdateEvent;
import com.prmgpregistrationsmi.testhelpers.EventDAOBuilder;
import com.prmgpregistrationsmi.testhelpers.preTransfer.PdsGeneralUpdateEventBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PdsGeneralUpdateEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Test
    void shouldUploadPdsGeneralUpdateEventToS3() {
        PdsGeneralUpdateEvent pdsGeneralUpdateEventRequest = PdsGeneralUpdateEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO expectedS3UploadEvent = EventDAOBuilder.withEvent(pdsGeneralUpdateEventRequest)
                .eventType(EventType.PDS_GENERAL_UPDATE)
                .transferProtocol(TransferProtocol.PRE_TRANSFER)
                .build();

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/preTransfer/pdsGeneralUpdate",
                pdsGeneralUpdateEventRequest, EventResponse.class);

        EventResponse expectedResponse = new EventResponse(expectedS3UploadEvent.getEventId());
        assertEquals(expectedResponse.getEventId(), actualResponseEvent.getEventId());

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                String.format("v1/1970/01/01/03/%s.json", pdsGeneralUpdateEventRequest.getEventId()),
                expectedS3UploadEvent.toString()
        );
    }
}
