package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.stage.InternalTransfer.InternalTransferEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrResponse.EhrResponseEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrIntegrated.EhrIntegratedEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrRequest.EhrRequestEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrTransferComplete.EhrTransferCompleteEvent;
import com.prmgpregistrationsmi.model.Event.stage.DocumentResponse.DocumentResponseEvent;
import com.prmgpregistrationsmi.model.Event.stage.PdsTrace.PdsTraceEvent;
import com.prmgpregistrationsmi.model.Event.stage.PdsUpdate.PdsUpdateEvent;
import com.prmgpregistrationsmi.model.Event.stage.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.model.Event.stage.TransferCapability.TransferCapabilityEvent;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.InternalTransferEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrResponseEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrIntegratedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrRequestEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrTransferCompleteEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.DocumentResponseEventBuilder;
import com.prmgpregistrationsmi.testhelpers.preTransfer.PdsTraceEventBuilder;
import com.prmgpregistrationsmi.testhelpers.preTransfer.PdsUpdateEventBuilder;
import com.prmgpregistrationsmi.testhelpers.preTransfer.RegistrationStartedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.preTransfer.TransferCapabilityEventBuilder;
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
    void ehrRequestEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrRequestEvent testEvent = EhrRequestEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(registrationService.saveEvent(testEvent, EventType.EHR_REQUEST)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.ehrRequestEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_REQUEST);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void ehrGeneratedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrResponseEvent testEvent = EhrResponseEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(registrationService.saveEvent(testEvent, EventType.EHR_RESPONSE)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.ehrResponseEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_RESPONSE);

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
    void shouldReturnEventIdWhenReceivingRegistrationStartedEvent() throws UnableToUploadToS3Exception {
        RegistrationStartedEvent testEvent = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(registrationService.saveEvent(testEvent, EventType.REGISTRATION_STARTED)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.registrationStartedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.REGISTRATION_STARTED);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingPdsTraceEvent() throws UnableToUploadToS3Exception {
        PdsTraceEvent testEvent = PdsTraceEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(registrationService.saveEvent(testEvent, EventType.PDS_TRACE)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.pdsTraceEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.PDS_TRACE);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingPdsUpdateEvent() throws UnableToUploadToS3Exception {
        PdsUpdateEvent testEvent = PdsUpdateEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(registrationService.saveEvent(testEvent, EventType.PDS_UPDATE)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.pdsUpdateEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.PDS_UPDATE);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingTransferCapabilityEvent() throws UnableToUploadToS3Exception {
        TransferCapabilityEvent testEvent = TransferCapabilityEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(registrationService.saveEvent(testEvent, EventType.SDS_LOOKUP)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.transferCapabilityEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.SDS_LOOKUP);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingDocumentResponseEvent() throws UnableToUploadToS3Exception {
        DocumentResponseEvent testEvent = DocumentResponseEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        when(registrationService.saveEvent(testEvent, EventType.DOCUMENT_RESPONSE)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.documentResponseEvent(testEvent);

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