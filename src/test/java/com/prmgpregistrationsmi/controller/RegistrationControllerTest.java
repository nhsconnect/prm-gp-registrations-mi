package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.*;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.EhrRequestedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationStartedEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RegistrationControllerTest {
    @MockBean
    private RegistrationService mockRegistrationService;

    @Test
    void registrationStartedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        RegistrationStartedEvent testEvent = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .eventId("event-test-12345")
                .build();
        EventDAO eventDAO = EventDAO.fromEvent(testEvent, EventType.GP2GP_REGISTRATION_STARTED);

        when(mockRegistrationService.saveEvent(any(RegistrationStartedEvent.class), eq(EventType.GP2GP_REGISTRATION_STARTED))).thenReturn(eventDAO);

        RegistrationController registrationController = new RegistrationController(mockRegistrationService);
        EventResponse actualResponse = registrationController.registrationStartedEvent(testEvent);

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

        when(mockRegistrationService.saveEvent(any(EhrRequestedEvent.class), eq(EventType.EHR_REQUESTED))).thenReturn(eventDAO);

        RegistrationController registrationController = new RegistrationController(mockRegistrationService);
        EventResponse actualResponse = registrationController.ehrRequestedEvent(testEvent);

        verify(mockRegistrationService).saveEvent(testEvent, EventType.EHR_REQUESTED);

        EventResponse expectedEventResponse = new EventResponse("event-test-23456");
        assertEquals(actualResponse, expectedEventResponse);
    }
}
