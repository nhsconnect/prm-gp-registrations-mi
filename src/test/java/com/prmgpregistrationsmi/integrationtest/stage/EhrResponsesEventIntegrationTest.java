package com.prmgpregistrationsmi.integrationtest.stage;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.OdsPortalWebClient.OdsPortalWebClient;
import com.prmgpregistrationsmi.SplunkWebclient.SplunkWebClient;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.stage.EhrResponses.EhrResponsesEvent;
import com.prmgpregistrationsmi.model.Organisation;
import com.prmgpregistrationsmi.model.OrganisationDetails;
import com.prmgpregistrationsmi.testhelpers.EventDAOBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrResponsesEventBuilder;
import com.prmgpregistrationsmi.utils.UUIDService;
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
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EhrResponsesEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @MockBean
    SplunkWebClient splunkWebClient;
    
    @MockBean
    OdsPortalWebClient odsPortalWebClient;

    @MockBean
    Clock clock;

    @Autowired
    private EventDAOBuilder eventDAOBuilder;

    @BeforeEach
    public void setup() {
        Clock mockClock = Clock.fixed(LocalDateTime.of(1990, 03, 3, 0, 0, 0).toInstant(ZoneOffset.of("Z")), ZoneId.systemDefault());
        doReturn(mockClock.instant()).when(clock).instant();
        doReturn(mockClock.getZone()).when(clock).getZone();
        when(odsPortalWebClient.getOrganisation(any())).thenReturn(Organisation.builder().Organisation(OrganisationDetails.builder().build()).build());
    }

    @Test
    void shouldUploadEhrResponsesEventToS3AndSendToSplunkCloud() {
        EhrResponsesEvent ehrResponsesEventRequest = EhrResponsesEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO expectedS3UploadEvent = eventDAOBuilder.withEvent(ehrResponsesEventRequest)
                .eventId(UUIDService.buildUUIDStringFromSeed(
                        ehrResponsesEventRequest.getConversationId() +
                                EventType.EHR_RESPONSES +
                                ehrResponsesEventRequest.getRegistrationEventDateTime())
                )
                .eventType(EventType.EHR_RESPONSES)

                .build();

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/ehr-responses", ehrResponsesEventRequest, EventResponse.class);

        assertEquals(expectedS3UploadEvent.getEventId(), actualResponseEvent.getEventId());

        verify(mockAmazonS3Client).putObject(
                "test_bucket",
                String.format("v1/2020/01/01/22/%s.json", expectedS3UploadEvent.getEventId()),
                expectedS3UploadEvent.toString()
        );

        verify(splunkWebClient).postEventToSplunkCloud(any(EventDAO.class));
    }
}
