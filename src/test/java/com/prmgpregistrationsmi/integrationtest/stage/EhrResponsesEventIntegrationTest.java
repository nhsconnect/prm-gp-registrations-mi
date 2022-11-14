package com.prmgpregistrationsmi.integrationtest.stage;

import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.stage.EhrResponses.EhrResponsesEvent;
import com.prmgpregistrationsmi.service.MessageSender;
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
    MessageSender messageSender;

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
    void shouldSendEhrResponsesEventViaMessageSender() {
        EhrResponsesEvent ehrResponsesEventRequest = EhrResponsesEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO expectedEventDAO = eventDAOBuilder.withEvent(ehrResponsesEventRequest)
                .eventId(UUIDService.buildUUIDStringFromSeed(
                        ehrResponsesEventRequest.getConversationId() +
                                EventType.EHR_RESPONSES +
                                ehrResponsesEventRequest.getRegistrationEventDateTime())
                )
                .eventType(EventType.EHR_RESPONSES)
                .build();

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/ehr-responses", ehrResponsesEventRequest, EventResponse.class);

        assertEquals(expectedEventDAO.getEventId(), actualResponseEvent.getEventId());

        verify(messageSender).send(any(EventDAO.class), eq(expectedEventDAO.getEventId()));
    }
}
