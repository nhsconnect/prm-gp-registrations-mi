package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.stage.DocumentResponses.DocumentResponsesEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrIntegrated.EhrIntegratedEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrRequests.EhrRequestsEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrResponses.EhrResponsesEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrTransferComplete.EhrTransferCompleteEvent;
import com.prmgpregistrationsmi.model.Event.stage.InternalTransfer.InternalTransferEvent;
import com.prmgpregistrationsmi.model.Event.stage.Registrations.RegistrationsEvent;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.InternalTransferEventBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrIntegratedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrRequestsEventBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrResponsesEventBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrTransferCompleteEventBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.DocumentResponsesEventBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.RegistrationsEventBuilder;
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
    private RegistrationService registrationService;
    private RegistrationController registrationController;

    @BeforeEach
    void setUp() {
        registrationController =  new RegistrationController(registrationService);
    }

    @Test
    void ehrRequestsEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrRequestsEvent testEvent = EhrRequestsEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(registrationService.saveEvent(testEvent, EventType.EHR_REQUESTS)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.ehrRequestsEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_REQUESTS);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void ehrGeneratedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrResponsesEvent testEvent = EhrResponsesEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(registrationService.saveEvent(testEvent, EventType.EHR_RESPONSES)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.ehrResponsesEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_RESPONSES);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingEhrIntegratedEvent() throws UnableToUploadToS3Exception {
        EhrIntegratedEvent testEvent = EhrIntegratedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(registrationService.saveEvent(testEvent, EventType.EHR_INTEGRATED)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.ehrIntegratedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_INTEGRATED);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingTransferCompleteEvent() throws UnableToUploadToS3Exception {
        EhrTransferCompleteEvent testEvent = EhrTransferCompleteEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(registrationService.saveEvent(testEvent, EventType.EHR_TRANSFER_COMPLETE)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.ehrTransferCompleteEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_TRANSFER_COMPLETE);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingRegistrationEvent() throws UnableToUploadToS3Exception {
        RegistrationsEvent testEvent = RegistrationsEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(registrationService.saveEvent(testEvent, EventType.REGISTRATIONS)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.registrationsEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.REGISTRATIONS);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingDocumentResponsesEvent() throws UnableToUploadToS3Exception {
        DocumentResponsesEvent testEvent = DocumentResponsesEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(registrationService.saveEvent(testEvent, EventType.DOCUMENT_RESPONSE)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.documentResponsesEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.DOCUMENT_RESPONSE);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingInternalTransferEvent() throws UnableToUploadToS3Exception {
        InternalTransferEvent testEvent = InternalTransferEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(registrationService.saveEvent(testEvent, EventType.INTERNAL_TRANSFER)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.internalTransferEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.INTERNAL_TRANSFER);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }
}