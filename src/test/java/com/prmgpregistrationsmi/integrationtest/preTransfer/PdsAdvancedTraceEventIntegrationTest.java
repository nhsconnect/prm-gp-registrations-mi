package com.prmgpregistrationsmi.integrationtest.preTransfer;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.preTransfer.PdsAdvancedTrace.PdsAdvancedTraceEvent;
import com.prmgpregistrationsmi.model.preTransfer.PdsAdvancedTrace.PdsAdvancedTracePayload;
import com.prmgpregistrationsmi.testhelpers.preTransfer.PdsAdvancedTraceEventBuilder;
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
class PdsAdvancedTraceEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Test
    void shouldUploadPdsAdvancedTraceEventToS3() {
        PdsAdvancedTraceEvent ehrIntegratedEventRequest = PdsAdvancedTraceEventBuilder
                .withDefaultEventValues()
                .build();

        PdsAdvancedTracePayload ehrIntegratedPayload = PdsAdvancedTraceEventBuilder
                .withDefaultPdsAdvancedTracePayload()
                .build();

        EventDAO expectedS3UploadEvent = new EventDAO(
                ehrIntegratedEventRequest.getEventId(),
                ehrIntegratedEventRequest.getEventGeneratedDateTime(),
                EventType.PDS_ADVANCED_TRACE,
                TransferProtocol.PRE_TRANSFER,
                ehrIntegratedEventRequest.getRegistrationId(),
                ehrIntegratedEventRequest.getReportingSystemSupplier(),
                ehrIntegratedEventRequest.getReportingPracticeOdsCode(),
                ehrIntegratedPayload
        );

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/registration/" + API_VERSION + "/preTransfer/pdsAdvancedTrace",
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
