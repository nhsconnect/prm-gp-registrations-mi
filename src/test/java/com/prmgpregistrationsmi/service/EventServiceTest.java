package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.SplunkWebclient.SplunkWebClient;
import com.prmgpregistrationsmi.model.Event.DegradesEventDAO;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.stage.EhrDegrades.EhrDegradesEvent;
import com.prmgpregistrationsmi.model.Event.stage.Registrations.RegistrationsEvent;
import com.prmgpregistrationsmi.testhelpers.stage.EhrDegradesEventBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.RegistrationsEventBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

class EventServiceTest {
    SplunkWebClient splunkWebClientMock = mock(SplunkWebClient.class);
    EnrichmentService enrichmentServiceMock = mock(EnrichmentService.class);
    MessagePublisher messagePublisherMock = mock(MessagePublisher.class);
    Clock clock = mock(Clock.class);
    LocalDateTime mockLocalDateTime = LocalDateTime.of(1990, 03, 3, 0, 0, 0);

    EventService eventService = new EventService(splunkWebClientMock, enrichmentServiceMock, messagePublisherMock, clock);

    @BeforeEach
    public void setup() {
        Clock mockClock = Clock.fixed(mockLocalDateTime.toInstant(ZoneOffset.of("Z")), ZoneId.systemDefault());
        doReturn(mockClock.instant()).when(clock).instant();
        doReturn(mockClock.getZone()).when(clock).getZone();
    }

    @Test
    void shouldCallPostEventToSplunkCloud() {
        RegistrationsEvent testEvent = RegistrationsEventBuilder
                .withDefaultEventValues()
                .build();
        EventType gp2gpRegistrationEventType = EventType.REGISTRATIONS;

        EventDAO expectedEventDAO = EventDAO.fromEvent(testEvent, gp2gpRegistrationEventType, mockLocalDateTime);

        EventDAO eventDAO = eventService.saveEvent(testEvent, gp2gpRegistrationEventType);

        verify(splunkWebClientMock, times(1)).postEventToSplunkCloud(eq(expectedEventDAO));
        assertEquals(eventDAO, expectedEventDAO);
    }

    @Test
    void shouldSendMessageWithEventDAO() {
        RegistrationsEvent testEvent = RegistrationsEventBuilder
                .withDefaultEventValues()
                .build();
        EventType gp2gpRegistrationEventType = EventType.REGISTRATIONS;

        EventDAO expectedEventDAO = EventDAO.fromEvent(testEvent, gp2gpRegistrationEventType, mockLocalDateTime);

        EventDAO eventDAO = eventService.saveEvent(testEvent, gp2gpRegistrationEventType);

        verify(messagePublisherMock, times(1)).sendMessage(eq(expectedEventDAO),eq(expectedEventDAO.getEventId()));
        assertEquals(eventDAO, expectedEventDAO);
    }

    @Test
    void shouldCallDegradesPostEventToSplunkCloud() {
        EhrDegradesEvent testEvent = EhrDegradesEventBuilder
                .withDefaultEventValues()
                .build();
        EventType gp2gpRegistrationEventType = EventType.DEGRADES;

        DegradesEventDAO expectedDegradesEventDAO = DegradesEventDAO.fromEvent(testEvent, gp2gpRegistrationEventType, mockLocalDateTime.truncatedTo(ChronoUnit.DAYS));

        DegradesEventDAO degradesEventDAO = eventService.saveDegradesEvent(testEvent, gp2gpRegistrationEventType);

        verify(splunkWebClientMock, times(1)).postEventToSplunkCloud(degradesEventDAO);
        assertEquals(degradesEventDAO.getEventGeneratedDateTime(), expectedDegradesEventDAO.getEventGeneratedDateTime());
        assertEquals(degradesEventDAO.getEventType(), expectedDegradesEventDAO.getEventType());
        assertEquals(degradesEventDAO.getReportingSystemSupplier(), expectedDegradesEventDAO.getReportingSystemSupplier());
        assertEquals(degradesEventDAO.getPayload(), expectedDegradesEventDAO.getPayload());
    }

    @Test
    void shouldSendMessageWithDegradesEventDAO() {
        EhrDegradesEvent testEvent = EhrDegradesEventBuilder
                .withDefaultEventValues()
                .build();
        EventType gp2gpRegistrationEventType = EventType.DEGRADES;
        DegradesEventDAO expectedDegradesEventDAO = DegradesEventDAO.fromEvent(testEvent, gp2gpRegistrationEventType, mockLocalDateTime.truncatedTo(ChronoUnit.DAYS));

        DegradesEventDAO degradesEventDAO = eventService.saveDegradesEvent(testEvent, gp2gpRegistrationEventType);

        verify(messagePublisherMock, times(1)).sendMessage(any(DegradesEventDAO.class), any(String.class));
        assertEquals(degradesEventDAO.getEventGeneratedDateTime(), expectedDegradesEventDAO.getEventGeneratedDateTime());
        assertEquals(degradesEventDAO.getEventType(), expectedDegradesEventDAO.getEventType());
        assertEquals(degradesEventDAO.getReportingSystemSupplier(), expectedDegradesEventDAO.getReportingSystemSupplier());
        assertEquals(degradesEventDAO.getPayload(), expectedDegradesEventDAO.getPayload());
    }
}
