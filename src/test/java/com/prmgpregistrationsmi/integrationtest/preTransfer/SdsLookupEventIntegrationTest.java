package com.prmgpregistrationsmi.integrationtest.preTransfer;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.preTransfer.SdsLookup.SdsLookupEvent;
import com.prmgpregistrationsmi.model.preTransfer.SdsLookup.SdsLookupPayload;
import com.prmgpregistrationsmi.testhelpers.preTransfer.SdsLookupEventBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SdsLookupEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Test
    void shouldUploadSdsLookupEventToS3() {
        SdsLookupEvent sdsLookupEventRequest = SdsLookupEventBuilder
                .withDefaultEventValues()
                .build();

        SdsLookupPayload sdsLookupPayload = SdsLookupEventBuilder
                .withDefaultSdsLookupPayload()
                .build();

        EventDAO expectedS3UploadEvent = new EventDAO(
                sdsLookupEventRequest.getEventId(),
                sdsLookupEventRequest.getEventGeneratedDateTime(),
                EventType.SDS_LOOKUP,
                TransferProtocol.PRE_TRANSFER,
                sdsLookupEventRequest.getReportingSystemSupplier(),
                sdsLookupEventRequest.getReportingPracticeOdsCode(),
                sdsLookupPayload
        );

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/preTransfer/sdsLookup",
                sdsLookupEventRequest, EventResponse.class);

        EventResponse expectedResponse = new EventResponse(expectedS3UploadEvent.getEventId());
        assertEquals(expectedResponse.getEventId(), actualResponseEvent.getEventId());

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                String.format("v1/1970/01/01/03/%s.json", sdsLookupEventRequest.getEventId()),
                expectedS3UploadEvent.toString()
        );
    }
}
