package com.prmgpregistrationsmi.integrationtest.gpc;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestPayload;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateStructuredRecordRequestEventBuilder;
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
class MigrateStructuredRecordRequestEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Test
    void shouldUploadMigrateStructuredRecordRequestEventToS3() {
        MigrateStructuredRecordRequestEvent migrateStructuredRecordRequestEventRequest = MigrateStructuredRecordRequestEventBuilder
                .withDefaultEventValues()
                .eventId("event-id-test")
                .eventGeneratedTimestamp(315130L)
                .build();

        MigrateStructuredRecordRequestPayload migrateStructuredRecordRequestPayload = MigrateStructuredRecordRequestEventBuilder
                .withDefaultMigrateStructuredRecordRequestPayload()
                .build();

        EventDAO expectedS3UploadEvent = new EventDAO(
                migrateStructuredRecordRequestEventRequest.getEventId(),
                315130L,
                EventType.MIGRATE_STRUCTURED_RECORD_REQUEST,
                migrateStructuredRecordRequestEventRequest.getRegistrationId(),
                migrateStructuredRecordRequestEventRequest.getReportingSystemSupplier(),
                migrateStructuredRecordRequestEventRequest.getReportingPracticeOdsCode(),
                migrateStructuredRecordRequestPayload
        );

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/registration/" + API_VERSION + "/gpconnect/migrateStructuredRecordRequest", migrateStructuredRecordRequestEventRequest, EventResponse.class);

        EventResponse expectedResponse = new EventResponse("event-id-test");
        assertEquals(expectedResponse, actualResponseEvent);

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                String.format("v1/1970/01/04/15/%s.json", migrateStructuredRecordRequestEventRequest.getEventId()),
                expectedS3UploadEvent.toString()
        );
    }
}
