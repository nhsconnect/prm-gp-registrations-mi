package com.prmgpregistrationsmi.integrationtest.gpc;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.PatientSwitchingStandardType;
import com.prmgpregistrationsmi.model.gpc.InternalTransfer.InternalTransferPayload;
import com.prmgpregistrationsmi.model.gpc.InternalTransfer.InternalTransferEvent;
import com.prmgpregistrationsmi.testhelpers.gpc.InternalTransferEventBuilder;
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
class InternalTransferEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Test
    void shouldUploadInternalTransferEventToS3() {
        InternalTransferEvent internalTransferEventRequest = InternalTransferEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO expectedS3UploadEvent = new EventDAO(
                internalTransferEventRequest.getEventId(),
                internalTransferEventRequest.getEventGeneratedDateTime(),
                EventType.INTERNAL_TRANSFER,
                PatientSwitchingStandardType.GP_CONNECT,
                internalTransferEventRequest.getRegistrationId(),
                internalTransferEventRequest.getReportingSystemSupplier(),
                internalTransferEventRequest.getReportingPracticeOdsCode(),
                InternalTransferEventBuilder
                        .withDefaultInternalTransferPayload()
                        .build()
        );

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/registration/" + API_VERSION + "/gpconnect/internalTransfer",
                internalTransferEventRequest, EventResponse.class);

        EventResponse expectedResponse = new EventResponse(expectedS3UploadEvent.getEventId());
        assertEquals(expectedResponse.getEventId(), actualResponseEvent.getEventId());

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                String.format("v1/1970/01/01/03/%s.json", internalTransferEventRequest.getEventId()),
                expectedS3UploadEvent.toString()
        );
    }
}
