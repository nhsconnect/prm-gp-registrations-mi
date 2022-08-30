package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.deprecated.Event.EventDAO;
import com.prmgpregistrationsmi.model.deprecated.Event.EventResponse;
import com.prmgpregistrationsmi.model.deprecated.Event.EventType;
import com.prmgpregistrationsmi.model.deprecated.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.deprecated.InternalTransfer.InternalTransferEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrGenerated.EhrGeneratedEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrIntegrated.EhrIntegratedEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrRequested.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrSent.EhrSentEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrValidated.EhrValidatedEvent;
import com.prmgpregistrationsmi.model.deprecated.gpc.EhrReadyToIntegrate.EhrReadyToIntegrateEvent;
import com.prmgpregistrationsmi.model.deprecated.gpc.MigrateDocumentRequest.MigrateDocumentRequestEvent;
import com.prmgpregistrationsmi.model.deprecated.gpc.MigrateDocumentResponse.MigrateDocumentResponseEvent;
import com.prmgpregistrationsmi.model.deprecated.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestEvent;
import com.prmgpregistrationsmi.model.deprecated.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponseEvent;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.PdsGeneralUpdate.PdsGeneralUpdateEvent;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.PdsTrace.PdsTraceEvent;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.SdsLookup.SdsLookupEvent;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.InternalTransferEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrIntegratedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.*;
import com.prmgpregistrationsmi.testhelpers.gpc.*;
import com.prmgpregistrationsmi.testhelpers.preTransfer.PdsGeneralUpdateEventBuilder;
import com.prmgpregistrationsmi.testhelpers.preTransfer.PdsTraceEventBuilder;
import com.prmgpregistrationsmi.testhelpers.preTransfer.RegistrationStartedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.preTransfer.SdsLookupEventBuilder;
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
    void ehrRequestedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrRequestedEvent testEvent = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();
        TransferProtocol transferProtocol = TransferProtocol.GP2GP;

        when(registrationService.saveEvent(testEvent, EventType.EHR_REQUESTED, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.ehrRequestedEvent(testEvent);

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

        EventResponse actualResponse = registrationController.ehrGeneratedEvent(testEvent);

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

        EventResponse actualResponse = registrationController.ehrSentEvent(testEvent);

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

        EventResponse actualResponse = registrationController.ehrIntegratedEvent(testEvent);

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

        EventResponse actualResponse = registrationController.ehrValidatedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_VALIDATED, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingRegistrationStartedEvent() throws UnableToUploadToS3Exception {
        RegistrationStartedEvent testEvent = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.PRE_TRANSFER;

        when(registrationService.saveEvent(testEvent, EventType.REGISTRATION_STARTED, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.registrationStartedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.REGISTRATION_STARTED, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingPdsTraceEvent() throws UnableToUploadToS3Exception {
        PdsTraceEvent testEvent = PdsTraceEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.PRE_TRANSFER;

        when(registrationService.saveEvent(testEvent, EventType.PDS_TRACE, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.pdsTraceEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.PDS_TRACE, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingPdsGeneralUpdateEvent() throws UnableToUploadToS3Exception {
        PdsGeneralUpdateEvent testEvent = PdsGeneralUpdateEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.PRE_TRANSFER;

        when(registrationService.saveEvent(testEvent, EventType.PDS_GENERAL_UPDATE, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.pdsGeneralUpdateEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.PDS_GENERAL_UPDATE, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingSdsLookupEvent() throws UnableToUploadToS3Exception {
        SdsLookupEvent testEvent = SdsLookupEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.PRE_TRANSFER;

        when(registrationService.saveEvent(testEvent, EventType.SDS_LOOKUP, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.sdsLookupEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.SDS_LOOKUP, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingMigrateStructuredRecordRequestEvent() throws UnableToUploadToS3Exception {
        MigrateStructuredRecordRequestEvent testEvent = MigrateStructuredRecordRequestEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.MIGRATE_STRUCTURED_RECORD_REQUEST, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.migrateStructuredRecordRequestEvent(testEvent);

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

        EventResponse actualResponse = registrationController.migrateStructuredRecordResponseEvent(testEvent);

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

        EventResponse actualResponse = registrationController.migrateDocumentRequestEvent(testEvent);

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

        EventResponse actualResponse = registrationController.migrateDocumentResponseEvent(testEvent);

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

        EventResponse actualResponse = registrationController.ehrReadyToIntegrateEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_READY_TO_INTEGRATE, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingInternalTransferEvent() throws UnableToUploadToS3Exception {
        InternalTransferEvent testEvent = InternalTransferEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.INTERNAL_TRANSFER;

        when(registrationService.saveEvent(testEvent, EventType.INTERNAL_TRANSFER, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.internalTransferEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.INTERNAL_TRANSFER, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }
}