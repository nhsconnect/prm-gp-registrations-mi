package com.prmgpregistrationsmi.integrationtest.gpc;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponseEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponsePayload;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateStructuredRecordResponseEventBuilder;
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
                .eventId("event-id-test")
                .eventGeneratedTimestamp(315130L)
                .build();

        MigrateStructuredRecordResponsePayload migrateStructuredRecordResponsePayload = MigrateStructuredRecordResponseEventBuilder
                .withDefaultMigrateStructuredRecordResponsePayload()
                .build();

        EventDAO expectedS3UploadEvent = new EventDAO(
                migrateStructuredRecordResponseEventResponse.getEventId(),
                315130L,
                EventType.MIGRATE_STRUCTURED_RECORD_RESPONSE,
                migrateStructuredRecordResponseEventResponse.getRegistrationId(),
                migrateStructuredRecordResponseEventResponse.getReportingSystemSupplier(),
                migrateStructuredRecordResponseEventResponse.getReportingPracticeOdsCode(),
                migrateStructuredRecordResponsePayload
        );

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/registration/" + API_VERSION + "/gpconnect/migrateStructuredRecordResponse", migrateStructuredRecordResponseEventResponse, EventResponse.class);

        EventResponse expectedResponse = new EventResponse("event-id-test");
        assertEquals(expectedResponse, actualResponseEvent);

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                String.format("v1/1970/01/04/15/%s.json", migrateStructuredRecordResponseEventResponse.getEventId()),
                expectedS3UploadEvent.toString()
        );
    }
}
