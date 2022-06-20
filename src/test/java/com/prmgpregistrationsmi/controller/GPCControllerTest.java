package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.gpc.EhrIntegrated.EhrIntegratedEvent;
import com.prmgpregistrationsmi.model.gpc.EhrReadyToIntegrate.EhrReadyToIntegrateEvent;
import com.prmgpregistrationsmi.model.gpc.Error.ErrorEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateDocumentRequest.MigrateDocumentRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateDocumentResponse.MigrateDocumentResponseEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponseEvent;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.gpc.EhrIntegratedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.EhrReadyToIntegrateEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.ErrorEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateDocumentRequestEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateDocumentResponseEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateStructuredRecordRequestEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateStructuredRecordResponseEventBuilder;
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
        gpcController = new GPCController(registrationService);
    }

    @Test
    void shouldReturnEventIdWhenReceivingMigrateStructuredRecordRequestEvent() throws UnableToUploadToS3Exception {
        MigrateStructuredRecordRequestEvent testEvent = MigrateStructuredRecordRequestEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.MIGRATE_STRUCTURED_RECORD_REQUEST, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.migrateStructuredRecordRequestEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.MIGRATE_STRUCTURED_RECORD_REQUEST, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingMigrateStructuredRecordResponseEvent() throws UnableToUploadToS3Exception {
        MigrateStructuredRecordResponseEvent testEvent = MigrateStructuredRecordResponseEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.MIGRATE_STRUCTURED_RECORD_RESPONSE, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.migrateStructuredRecordResponseEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.MIGRATE_STRUCTURED_RECORD_RESPONSE, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingMigrateDocumentRequestEvent() throws UnableToUploadToS3Exception {
        MigrateDocumentRequestEvent testEvent = MigrateDocumentRequestEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.MIGRATE_DOCUMENT_REQUEST, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.migrateDocumentRequestEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.MIGRATE_DOCUMENT_REQUEST, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingMigrateDocumentResponseEvent() throws UnableToUploadToS3Exception {
        MigrateDocumentResponseEvent testEvent = MigrateDocumentResponseEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.MIGRATE_DOCUMENT_RESPONSE, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.migrateDocumentResponseEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.MIGRATE_DOCUMENT_RESPONSE, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingEhrReadyToIntegrateEvent() throws UnableToUploadToS3Exception {
        EhrReadyToIntegrateEvent testEvent = EhrReadyToIntegrateEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.EHR_READY_TO_INTEGRATE, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.ehrReadyToIntegrateEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_READY_TO_INTEGRATE, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingEhrIntegratedEvent() throws UnableToUploadToS3Exception {
        EhrIntegratedEvent testEvent = EhrIntegratedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.EHR_INTEGRATED, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.ehrIntegratedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_INTEGRATED, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingErrorEvent() throws UnableToUploadToS3Exception {
        ErrorEvent testEvent = ErrorEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.ERROR, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.errorEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.ERROR, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }
}