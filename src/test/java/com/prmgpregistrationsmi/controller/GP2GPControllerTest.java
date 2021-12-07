package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.EhrGenerated.EhrGeneratedEvent;
import com.prmgpregistrationsmi.model.EhrRequested.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.EhrGeneratedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.EhrRequestedEventBuilder;
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
    private RegistrationService mockRegistrationService;
    private GP2GPController gp2gpController;

    @BeforeEach
    void setUp() {
        gp2gpController =  new GP2GPController(mockRegistrationService);
    }

    @Test
    void registrationStartedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        RegistrationStartedEvent testEvent = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .eventId("event-test-12345")
                .build();
        EventDAO eventDAO = EventDAO.fromEvent(testEvent, EventType.GP2GP_REGISTRATION_STARTED);

        when(mockRegistrationService.saveEvent(testEvent, EventType.GP2GP_REGISTRATION_STARTED)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpController.registrationStartedEvent(testEvent);

        verify(mockRegistrationService).saveEvent(testEvent, EventType.GP2GP_REGISTRATION_STARTED);

        EventResponse expectedEventResponse = new EventResponse("event-test-12345");
        assertEquals(actualResponse, expectedEventResponse);
    }

    @Test
    void ehrRequestedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrRequestedEvent testEvent = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .eventId("event-test-23456")
                .build();
        EventDAO eventDAO = EventDAO.fromEvent(testEvent, EventType.EHR_REQUESTED);

        when(mockRegistrationService.saveEvent(testEvent, EventType.EHR_REQUESTED)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpController.ehrRequestedEvent(testEvent);

        verify(mockRegistrationService).saveEvent(testEvent, EventType.EHR_REQUESTED);

        EventResponse expectedEventResponse = new EventResponse("event-test-23456");
        assertEquals(actualResponse, expectedEventResponse);
    }

    @Test
    void ehrGeneratedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrGeneratedEvent testEvent = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .eventId("event-test-23456")
                .build();
        EventDAO eventDAO = EventDAO.fromEvent(testEvent, EventType.EHR_GENERATED);

        when(mockRegistrationService.saveEvent(testEvent, EventType.EHR_GENERATED)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpController.ehrGeneratedEvent(testEvent);

        verify(mockRegistrationService).saveEvent(testEvent, EventType.EHR_GENERATED);

        EventResponse expectedEventResponse = new EventResponse("event-test-23456");
        assertEquals(actualResponse, expectedEventResponse);
    }
}
