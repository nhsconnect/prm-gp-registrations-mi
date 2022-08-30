package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.deprecated.Event.EventDAO;
import com.prmgpregistrationsmi.model.deprecated.Event.EventResponse;
import com.prmgpregistrationsmi.model.deprecated.Event.EventType;
import com.prmgpregistrationsmi.model.deprecated.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrGenerated.EhrGeneratedEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrIntegrated.EhrIntegratedEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrRequested.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrSent.EhrSentEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrValidated.EhrValidatedEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.Error.ErrorEvent;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrGeneratedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrIntegratedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrRequestedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrSentEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrValidatedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.ErrorEventBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GP2GPControllerDeprecatedTest {
    @Mock
    private RegistrationService registrationService;
    private GP2GPControllerDeprecated gp2gpControllerDeprecated;

    @BeforeEach
    void setUp() {
        gp2gpControllerDeprecated =  new GP2GPControllerDeprecated(registrationService);
    }

    @Test
    void ehrRequestedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrRequestedEvent testEvent = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();
        TransferProtocol transferProtocol = TransferProtocol.GP2GP;

        when(registrationService.saveEvent(testEvent, EventType.EHR_REQUESTED, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpControllerDeprecated.ehrRequestedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_REQUESTED, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void ehrGeneratedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrGeneratedEvent testEvent = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();
        TransferProtocol transferProtocol = TransferProtocol.GP2GP;

        when(registrationService.saveEvent(testEvent, EventType.EHR_GENERATED, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpControllerDeprecated.ehrGeneratedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_GENERATED, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void ehrEventSentReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrSentEvent testEvent = EhrSentEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.GP2GP;

        when(registrationService.saveEvent(testEvent, EventType.EHR_SENT, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpControllerDeprecated.ehrSentEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_SENT, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingEhrIntegratedEvent() throws UnableToUploadToS3Exception {
        EhrIntegratedEvent testEvent = EhrIntegratedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.GP2GP;

        when(registrationService.saveEvent(testEvent, EventType.EHR_INTEGRATED, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpControllerDeprecated.ehrIntegratedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_INTEGRATED, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingEhrValidatedEvent() throws UnableToUploadToS3Exception {
        EhrValidatedEvent testEvent = EhrValidatedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.GP2GP;

        when(registrationService.saveEvent(testEvent, EventType.EHR_VALIDATED, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpControllerDeprecated.ehrValidatedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_VALIDATED, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingErrorEvent() throws UnableToUploadToS3Exception {
        ErrorEvent testEvent = ErrorEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.GP2GP;

        when(registrationService.saveEvent(testEvent, EventType.ERROR, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpControllerDeprecated.errorEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.ERROR, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }
}