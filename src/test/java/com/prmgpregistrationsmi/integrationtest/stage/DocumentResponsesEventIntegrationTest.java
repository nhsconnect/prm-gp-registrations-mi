package com.prmgpregistrationsmi.integrationtest.stage;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.stage.DocumentResponses.DocumentResponsesEvent;
import com.prmgpregistrationsmi.testhelpers.EventDAOBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.DocumentResponsesEventBuilder;
import com.prmgpregistrationsmi.utils.UUIDService;
import com.prmgpregistrationsmi.SplunkWebclient.SplunkWebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DocumentResponsesEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @MockBean
    SplunkWebClient splunkWebClient;

    @MockBean
    Clock clock;

    @Autowired
    private EventDAOBuilder eventDAOBuilder;

    @BeforeEach
    public void setup() {
        Clock mockClock = Clock.fixed(LocalDateTime.of(1990, 03, 3, 0, 0, 0).toInstant(ZoneOffset.of("Z")), ZoneId.systemDefault());
        doReturn(mockClock.instant()).when(clock).instant();
        doReturn(mockClock.getZone()).when(clock).getZone();
    }

    @Test
    void shouldUploadDocumentResponsesEventToS3AndSendToSplunkCloud() {
        DocumentResponsesEvent documentResponsesEventRequest = DocumentResponsesEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO expectedS3UploadEvent = eventDAOBuilder.withEvent(documentResponsesEventRequest)
                .eventId(UUIDService.buildUUIDStringFromSeed(
                        documentResponsesEventRequest.getConversationId() +
                                EventType.DOCUMENT_RESPONSES +
                                documentResponsesEventRequest.getRegistrationEventDateTime())
                )
                .eventType(EventType.DOCUMENT_RESPONSES)
                .build();

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/document-responses",
                documentResponsesEventRequest, EventResponse.class);

        assertEquals(expectedS3UploadEvent.getEventId(), actualResponseEvent.getEventId());

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                String.format("v1/2020/01/01/22/%s.json", expectedS3UploadEvent.getEventId()),
                expectedS3UploadEvent.toString()
        );

        verify(splunkWebClient).postEventToSplunkCloud(any(EventDAO.class));
    }
}
