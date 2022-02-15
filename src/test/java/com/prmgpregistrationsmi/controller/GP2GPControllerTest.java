package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.gp2gp.EhrGenerated.EhrGeneratedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrIntegrated.EhrIntegratedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrRequested.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrSent.EhrSentEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrValidated.EhrValidatedEvent;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrGeneratedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrIntegratedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrRequestedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrSentEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrValidatedEventBuilder;
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
    void ehrRequestedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrRequestedEvent testEvent = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();
        TransferProtocol transferProtocol = TransferProtocol.GP2GP;

        when(registrationService.saveEvent(testEvent, EventType.EHR_REQUESTED, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpController.ehrRequestedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_REQUESTED, transferProtocol);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(expectedEventResponse.getEventId(), actualResponse.getEventId());
    }

    @Test
    void ehrGeneratedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrGeneratedEvent testEvent = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();
        TransferProtocol transferProtocol = TransferProtocol.GP2GP;

        when(registrationService.saveEvent(testEvent, EventType.EHR_GENERATED, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpController.ehrGeneratedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_GENERATED, transferProtocol);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(expectedEventResponse.getEventId(), actualResponse.getEventId());
    }

    @Test
    void ehrEventSentReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrSentEvent testEvent = EhrSentEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();

        TransferProtocol transferProtocol = TransferProtocol.GP2GP;

        when(registrationService.saveEvent(testEvent, EventType.EHR_SENT, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpController.ehrSentEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_SENT, transferProtocol);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(expectedEventResponse.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingEhrIntegratedEvent() throws UnableToUploadToS3Exception {
        EhrIntegratedEvent testEvent = EhrIntegratedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();

        TransferProtocol transferProtocol = TransferProtocol.GP2GP;

        when(registrationService.saveEvent(testEvent, EventType.EHR_INTEGRATED, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpController.ehrIntegratedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_INTEGRATED, transferProtocol);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(expectedEventResponse.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingEhrValidatedEvent() throws UnableToUploadToS3Exception {
        EhrValidatedEvent testEvent = EhrValidatedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();

        TransferProtocol transferProtocol = TransferProtocol.GP2GP;

        when(registrationService.saveEvent(testEvent, EventType.EHR_VALIDATED, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gp2gpController.ehrValidatedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_VALIDATED, transferProtocol);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(expectedEventResponse.getEventId(), actualResponse.getEventId());
    }
}