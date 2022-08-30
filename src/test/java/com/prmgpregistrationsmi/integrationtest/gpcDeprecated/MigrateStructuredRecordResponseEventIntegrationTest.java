package com.prmgpregistrationsmi.integrationtest.gpcDeprecated;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.deprecated.Event.EventDAO;
import com.prmgpregistrationsmi.model.deprecated.Event.EventResponse;
import com.prmgpregistrationsmi.model.deprecated.Event.EventType;
import com.prmgpregistrationsmi.model.deprecated.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.deprecated.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponseEvent;
import com.prmgpregistrationsmi.testhelpers.EventDAOBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateStructuredRecordResponseEventBuilder;
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
class MigrateStructuredRecordResponseEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Test
    void shouldUploadMigrateStructuredRecordResponseEventToS3() {
        MigrateStructuredRecordResponseEvent migrateStructuredRecordResponseEventResponse = MigrateStructuredRecordResponseEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO expectedS3UploadEvent = EventDAOBuilder.withEvent(migrateStructuredRecordResponseEventResponse)
                .eventId(UUIDService.buildUUIDStringFromSeed(
                        migrateStructuredRecordResponseEventResponse.getConversationId() +
                                EventType.MIGRATE_STRUCTURED_RECORD_RESPONSE +
                                migrateStructuredRecordResponseEventResponse.getEventGeneratedDateTime().toString())
                )
                .eventType(EventType.MIGRATE_STRUCTURED_RECORD_RESPONSE)
                .transferProtocol(TransferProtocol.GP_CONNECT)
                .build();

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/migrateStructuredRecordResponse", migrateStructuredRecordResponseEventResponse, EventResponse.class);

        assertEquals(expectedS3UploadEvent.getEventId(), actualResponseEvent.getEventId());

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                String.format("v1/1970/01/01/03/%s.json", expectedS3UploadEvent.getEventId()),
                expectedS3UploadEvent.toString()
        );
    }
}
