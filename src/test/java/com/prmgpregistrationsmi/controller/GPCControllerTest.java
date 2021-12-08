package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.gpc.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.gpc.RegistrationStartedEventBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GPCControllerTest {
    @Mock
    private RegistrationService registrationService;
    private GPCController gpcController;

    @BeforeEach
    void setUp() {
        gpcController =  new GPCController(registrationService);
    }

    @Test
    void registrationStartedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        RegistrationStartedEvent testEvent = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();

        when(registrationService.saveEvent(testEvent, EventType.REGISTRATION_STARTED)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.registrationStartedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.REGISTRATION_STARTED);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(actualResponse, expectedEventResponse);
    }
}