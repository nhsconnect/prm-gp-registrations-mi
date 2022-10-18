package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.BaseEvent;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
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
    void ehrRequestsEventReturnsEventResponse() throws UnableToUploadToS3Exception {
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
    void ehrGeneratedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
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
    void shouldReturnEventIdWhenReceivingEhrIntegratedEvent() throws UnableToUploadToS3Exception {
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
    void shouldReturnEventIdWhenReceivingRegistrationEvent() throws UnableToUploadToS3Exception {
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
    void shouldReturnEventIdWhenReceivingDocumentResponsesEvent() throws UnableToUploadToS3Exception {
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
    void readyToIntegrateStatusesEventReturnsEventResponse() throws UnableToUploadToS3Exception {
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
    void degradesEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrDegradesEvent testEvent = EhrDegradesEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(eventService.saveDegradesEvent(testEvent, EventType.DEGRADES)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.ehrDegradesEvent(testEvent);

        verify(eventService).saveDegradesEvent(testEvent, EventType.DEGRADES);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }
}