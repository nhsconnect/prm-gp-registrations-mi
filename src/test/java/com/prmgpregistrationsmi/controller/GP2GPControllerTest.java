package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.EhrGenerated.EhrGeneratedEvent;
import com.prmgpregistrationsmi.model.EhrRequested.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.EhrSent.EhrSentEvent;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.EhrGeneratedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.EhrRequestedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.EhrSentEventBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationStartedEventBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GP2GPControllerTest {
    @Mock
    private RegistrationService registrationService;
    private GP2GPController gp2gpController;

    @BeforeEach
    void setUp() {
        gp2gpController =  new GP2GPController(registrationService);
    }

    @Test
    void registrationStartedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        RegistrationStartedEvent testEvent = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();

        when(registrationService.saveEvent(testEvent, EventType.GP2GP_REGISTRATION_STARTED)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpController.registrationStartedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.GP2GP_REGISTRATION_STARTED);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(actualResponse, expectedEventResponse);
    }

    @Test
    void ehrRequestedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrRequestedEvent testEvent = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();

        when(registrationService.saveEvent(testEvent, EventType.EHR_REQUESTED)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpController.ehrRequestedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_REQUESTED);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(actualResponse, expectedEventResponse);
    }

    @Test
    void ehrGeneratedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrGeneratedEvent testEvent = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();

        when(registrationService.saveEvent(testEvent, EventType.EHR_GENERATED)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpController.ehrGeneratedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_GENERATED);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(actualResponse, expectedEventResponse);
    }

    @Test
    void ehrEventSentReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrSentEvent testEvent = EhrSentEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();

        when(registrationService.saveEvent(testEvent, EventType.EHR_SENT)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpController.ehrSentEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_SENT);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(actualResponse, expectedEventResponse);
    }
}