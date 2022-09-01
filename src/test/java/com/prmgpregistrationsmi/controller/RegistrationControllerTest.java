package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.deprecated.Event.EventDAO;
import com.prmgpregistrationsmi.model.deprecated.Event.EventResponse;
import com.prmgpregistrationsmi.model.deprecated.Event.EventType;
import com.prmgpregistrationsmi.model.deprecated.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.deprecated.InternalTransfer.InternalTransferEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrResponse.EhrResponseEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrIntegrated.EhrIntegratedEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrRequest.EhrRequestEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrValidated.EhrValidatedEvent;
import com.prmgpregistrationsmi.model.deprecated.gpc.EhrReadyToIntegrate.EhrReadyToIntegrateEvent;
import com.prmgpregistrationsmi.model.deprecated.gpc.MigrateDocumentResponse.MigrateDocumentResponseEvent;
import com.prmgpregistrationsmi.model.deprecated.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestEvent;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.PdsTrace.PdsTraceEvent;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.PdsUpdate.PdsUpdateEvent;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.SdsLookup.SdsLookupEvent;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.InternalTransferEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrResponseEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrIntegratedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrRequestEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrValidatedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.EhrReadyToIntegrateEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateDocumentResponseEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateStructuredRecordRequestEventBuilder;
import com.prmgpregistrationsmi.testhelpers.preTransfer.PdsTraceEventBuilder;
import com.prmgpregistrationsmi.testhelpers.preTransfer.PdsUpdateEventBuilder;
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
    void ehrRequestEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrRequestEvent testEvent = EhrRequestEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();
        TransferProtocol transferProtocol = TransferProtocol.GP2GP;

        when(registrationService.saveEvent(testEvent, EventType.EHR_REQUEST, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.ehrRequestEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_REQUEST, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }

    @Test
    void ehrGeneratedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
        EhrResponseEvent testEvent = EhrResponseEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();
        TransferProtocol transferProtocol = TransferProtocol.GP2GP;

        when(registrationService.saveEvent(testEvent, EventType.EHR_RESPONSE, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.ehrGeneratedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_RESPONSE, transferProtocol);

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
    void shouldReturnEventIdWhenReceivingPdsUpdateEvent() throws UnableToUploadToS3Exception {
        PdsUpdateEvent testEvent = PdsUpdateEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.PRE_TRANSFER;

        when(registrationService.saveEvent(testEvent, EventType.PDS_UPDATE, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = registrationController.pdsUpdateEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.PDS_UPDATE, transferProtocol);

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