package com.prmgpregistrationsmi.integrationtest.gp2gp;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.gp2gp.EhrSent.EhrSentEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrSent.EhrSentPayload;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrSentEventBuilder;
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
class EhrSentEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Test
    void shouldUploadEhrSentEventToS3() {
        EhrSentEvent ehrSentEventRequest = EhrSentEventBuilder
                .withDefaultEventValues()
                .build();

        EhrSentPayload ehrSentPayload = EhrSentEventBuilder
                .withDefaultEhrSentPayload()
                .build();

        EventDAO expectedS3UploadEvent = new EventDAO(
                ehrSentEventRequest.getEventId(),
                ehrSentEventRequest.getEventGeneratedDateTime(),
                EventType.EHR_SENT,
                ehrSentEventRequest.getRegistrationId(),
                ehrSentEventRequest.getReportingSystemSupplier(),
                ehrSentEventRequest.getReportingPracticeOdsCode(),
                ehrSentPayload
        );

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/registration/" + API_VERSION + "/gp2gp/ehrSent", ehrSentEventRequest, EventResponse.class);

        EventResponse expectedResponse = new EventResponse(expectedS3UploadEvent.getEventId());
        assertEquals(expectedResponse, actualResponseEvent);

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                String.format("v1/1970/01/01/03/%s.json", ehrSentEventRequest.getEventId()),
                expectedS3UploadEvent.toString()
        );
    }
}