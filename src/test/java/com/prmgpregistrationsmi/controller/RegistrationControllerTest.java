package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.model.Event.*;
import com.prmgpregistrationsmi.model.Event.stage.DocumentResponses.DocumentResponsesEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrDegrades.EhrDegradesEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrIntegrations.EhrIntegrationsEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrResponses.EhrResponsesEvent;
import com.prmgpregistrationsmi.model.Event.stage.Registrations.RegistrationsEvent;
import com.prmgpregistrationsmi.service.EventService;
import com.prmgpregistrationsmi.testhelpers.BaseEventBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {
    @Mock
    private EventService eventService;
    private RegistrationController registrationController;

    @BeforeEach
    void setUp() {
        registrationController =  new RegistrationController(eventService);
    }

    @Test
    void ehrRequestsEventReturnsEventResponse() {
        BaseEvent testEvent = BaseEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(eventService.saveEvent(testEvent, EventType.EHR_REQUESTS)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.ehrRequestsEvent(testEvent);

        verify(eventService).saveEvent(testEvent, EventType.EHR_REQUESTS);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void ehrGeneratedEventReturnsEventResponse() {
        EhrResponsesEvent testEvent = EhrResponsesEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(eventService.saveEvent(testEvent, EventType.EHR_RESPONSES)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.ehrResponsesEvent(testEvent);

        verify(eventService).saveEvent(testEvent, EventType.EHR_RESPONSES);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingEhrIntegratedEvent() {
        EhrIntegrationsEvent testEvent = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(eventService.saveEvent(testEvent, EventType.EHR_INTEGRATIONS)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.ehrIntegrationsEvent(testEvent);

        verify(eventService).saveEvent(testEvent, EventType.EHR_INTEGRATIONS);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingRegistrationEvent() {
        RegistrationsEvent testEvent = RegistrationsEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(eventService.saveEvent(testEvent, EventType.REGISTRATIONS)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.registrationsEvent(testEvent);

        verify(eventService).saveEvent(testEvent, EventType.REGISTRATIONS);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingDocumentResponsesEvent() {
        DocumentResponsesEvent testEvent = DocumentResponsesEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(eventService.saveEvent(testEvent, EventType.DOCUMENT_RESPONSES)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.documentResponsesEvent(testEvent);

        verify(eventService).saveEvent(testEvent, EventType.DOCUMENT_RESPONSES);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void readyToIntegrateStatusesEventReturnsEventResponse() {
        BaseEvent testEvent = BaseEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(eventService.saveEvent(testEvent, EventType.READY_TO_INTEGRATE_STATUSES)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.readyToIntegrateStatusesEvent(testEvent);

        verify(eventService).saveEvent(testEvent, EventType.READY_TO_INTEGRATE_STATUSES);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }


    @Test
    void degradesEventReturnsEventResponse() {
        EhrDegradesEvent testEvent = EhrDegradesEventBuilder
                .withDefaultEventValues()
                .build();

        DegradesEventDAO degradesEventDAO = DegradesEventDAO.builder().build();

        when(eventService.saveDegradesEvent(testEvent, EventType.DEGRADES)).thenReturn(degradesEventDAO);

        EventResponse actualResponse = registrationController.ehrDegradesEvent(testEvent);

        verify(eventService).saveDegradesEvent(testEvent, EventType.DEGRADES);

        assertEquals(degradesEventDAO.getEventId(), actualResponse.getEventId());
    }
}