package com.prmgpregistrationsmi.integrationtest.gpc;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.gpc.MigrateDocumentResponse.MigrateDocumentResponseEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateDocumentResponse.MigrateDocumentResponsePayload;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateDocumentResponseEventBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MigrateDocumentResponseEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Test
    void shouldUploadMigrateDocumentResponseEventToS3() {
        MigrateDocumentResponseEvent migrateDocumentResponseEventRequest = MigrateDocumentResponseEventBuilder
                .withDefaultEventValues()
                .build();

        MigrateDocumentResponsePayload migrateDocumentResponsePayload = MigrateDocumentResponseEventBuilder
                .withDefaultMigrateDocumentResponsePayload()
                .build();

        EventDAO expectedS3UploadEvent = new EventDAO(
                migrateDocumentResponseEventRequest.getEventId(),
                migrateDocumentResponseEventRequest.getEventGeneratedDateTime(),
                EventType.MIGRATE_DOCUMENT_RESPONSE,
                TransferProtocol.GP_CONNECT,
                migrateDocumentResponseEventRequest.getReportingSystemSupplier(),
                migrateDocumentResponseEventRequest.getReportingPracticeOdsCode(),
                migrateDocumentResponsePayload
        );

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/gpconnect/migrateDocumentResponse",
                migrateDocumentResponseEventRequest, EventResponse.class);

        EventResponse expectedResponse = new EventResponse(expectedS3UploadEvent.getEventId());
        assertEquals(expectedResponse.getEventId(), actualResponseEvent.getEventId());

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                String.format("v1/1970/01/01/03/%s.json", migrateDocumentResponseEventRequest.getEventId()),
                expectedS3UploadEvent.toString()
        );
    }
}
