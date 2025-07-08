package com.prmgpregistrationsmi.integrationtest.stage;

import com.prmgpregistrationsmi.model.Event.DegradesEventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.stage.EhrDegrades.EhrDegradesEvent;
import com.prmgpregistrationsmi.service.MessageSender;
import com.prmgpregistrationsmi.testhelpers.DegradesEventDAOBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrDegradesEventBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EhrDegradesEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    MessageSender messageSender;

    @MockBean
    Clock clock;

    @Autowired
    DegradesEventDAOBuilder degradesEventDAOBuilder;

    @BeforeEach
    public void setup() {
        Clock mockClock = Clock.fixed(LocalDateTime.of(1990, 03, 3, 0, 0, 0).toInstant(ZoneOffset.of("Z")), ZoneId.systemDefault());
        doReturn(mockClock.instant()).when(clock).instant();
        doReturn(mockClock.getZone()).when(clock).getZone();
    }

    @Test
    void shouldSendEhrDegradeEventViaMessageSender() {
        EhrDegradesEvent ehrDegradesEvent = EhrDegradesEventBuilder
                .withDefaultEventValues()
                .build();

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/ehr-degrades", ehrDegradesEvent, EventResponse.class);

        DegradesEventDAO expectedDegradesEventDAO = degradesEventDAOBuilder.withDegradesEvent(ehrDegradesEvent)
                .eventType(EventType.DEGRADES)
                .eventId(actualResponseEvent.getEventId())
                .build();

        verify(messageSender).send(any(DegradesEventDAO.class), eq(expectedDegradesEventDAO.getEventId()));
    }
}
