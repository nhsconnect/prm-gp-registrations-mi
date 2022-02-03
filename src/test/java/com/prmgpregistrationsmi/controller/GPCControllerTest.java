package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.PatientSwitchingStandardType;
import com.prmgpregistrationsmi.model.gpc.EhrIntegrated.EhrIntegratedEvent;
import com.prmgpregistrationsmi.model.gpc.EhrReadyToIntegrate.EhrReadyToIntegrateEvent;
import com.prmgpregistrationsmi.model.gpc.Error.ErrorEvent;
import com.prmgpregistrationsmi.model.gpc.InternalTransfer.InternalTransferEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateDocumentRequest.MigrateDocumentRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateDocumentResponse.MigrateDocumentResponseEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponseEvent;
import com.prmgpregistrationsmi.model.gpc.RegistrationCompleted.RegistrationCompletedEvent;
import com.prmgpregistrationsmi.model.gpc.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.gpc.EhrIntegratedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.EhrReadyToIntegrateEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.ErrorEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.InternalTransferEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateDocumentRequestEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateDocumentResponseEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateStructuredRecordRequestEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateStructuredRecordResponseEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.RegistrationCompletedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.RegistrationStartedEventBuilder;
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
        gpcController =  new GPCController(registrationService);
    }

    @Test
    void shouldReturnEventIdWhenReceivingRegistrationStartedEvent() throws UnableToUploadToS3Exception {
        RegistrationStartedEvent testEvent = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();

        PatientSwitchingStandardType patientSwitchingStandardType = PatientSwitchingStandardType.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.REGISTRATION_STARTED, patientSwitchingStandardType)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.registrationStartedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.REGISTRATION_STARTED, patientSwitchingStandardType);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(expectedEventResponse.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingMigrateStructuredRecordRequestEvent() throws UnableToUploadToS3Exception {
        MigrateStructuredRecordRequestEvent testEvent = MigrateStructuredRecordRequestEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();

        PatientSwitchingStandardType patientSwitchingStandardType = PatientSwitchingStandardType.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.MIGRATE_STRUCTURED_RECORD_REQUEST, patientSwitchingStandardType)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.migrateStructuredRecordRequestEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.MIGRATE_STRUCTURED_RECORD_REQUEST, patientSwitchingStandardType);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(expectedEventResponse.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingMigrateStructuredRecordResponseEvent() throws UnableToUploadToS3Exception {
        MigrateStructuredRecordResponseEvent testEvent = MigrateStructuredRecordResponseEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();

        PatientSwitchingStandardType patientSwitchingStandardType = PatientSwitchingStandardType.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.MIGRATE_STRUCTURED_RECORD_RESPONSE, patientSwitchingStandardType)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.migrateStructuredRecordResponseEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.MIGRATE_STRUCTURED_RECORD_RESPONSE, patientSwitchingStandardType);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(expectedEventResponse.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingMigrateDocumentRequestEvent() throws UnableToUploadToS3Exception {
        MigrateDocumentRequestEvent testEvent = MigrateDocumentRequestEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();

        PatientSwitchingStandardType patientSwitchingStandardType = PatientSwitchingStandardType.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.MIGRATE_DOCUMENT_REQUEST, patientSwitchingStandardType)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.migrateDocumentRequestEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.MIGRATE_DOCUMENT_REQUEST, patientSwitchingStandardType);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(expectedEventResponse.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingMigrateDocumentResponseEvent() throws UnableToUploadToS3Exception {
        MigrateDocumentResponseEvent testEvent = MigrateDocumentResponseEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();

        PatientSwitchingStandardType patientSwitchingStandardType = PatientSwitchingStandardType.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.MIGRATE_DOCUMENT_RESPONSE, patientSwitchingStandardType)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.migrateDocumentResponseEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.MIGRATE_DOCUMENT_RESPONSE, patientSwitchingStandardType);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(expectedEventResponse.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingEhrReadyToIntegrateEvent() throws UnableToUploadToS3Exception {
        EhrReadyToIntegrateEvent testEvent = EhrReadyToIntegrateEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();

        PatientSwitchingStandardType patientSwitchingStandardType = PatientSwitchingStandardType.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.EHR_READY_TO_INTEGRATE, patientSwitchingStandardType)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.ehrReadyToIntegrateEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_READY_TO_INTEGRATE, patientSwitchingStandardType);

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

        PatientSwitchingStandardType patientSwitchingStandardType = PatientSwitchingStandardType.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.EHR_INTEGRATED, patientSwitchingStandardType)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.ehrIntegratedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.EHR_INTEGRATED, patientSwitchingStandardType);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(expectedEventResponse.getEventId(), actualResponse.getEventId());
    }

    @Test
    void shouldReturnEventIdWhenReceivingRegistrationCompletedEvent() throws UnableToUploadToS3Exception {
        RegistrationCompletedEvent testEvent = RegistrationCompletedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();

        PatientSwitchingStandardType patientSwitchingStandardType = PatientSwitchingStandardType.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.REGISTRATION_COMPLETED, patientSwitchingStandardType)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.registrationCompletedEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.REGISTRATION_COMPLETED, patientSwitchingStandardType);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(expectedEventResponse.getEventId(), actualResponse.getEventId());
    }


    @Test
    void shouldReturnEventIdWhenReceivingErrorEvent() throws UnableToUploadToS3Exception {
        ErrorEvent testEvent = ErrorEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();

        PatientSwitchingStandardType patientSwitchingStandardType = PatientSwitchingStandardType.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.ERROR, patientSwitchingStandardType)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.errorEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.ERROR, patientSwitchingStandardType);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(expectedEventResponse.getEventId(), actualResponse.getEventId());
    }



    @Test
    void shouldReturnEventIdWhenReceivingInternalTransferEvent() throws UnableToUploadToS3Exception {
        InternalTransferEvent testEvent = InternalTransferEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder()
                .eventId(testEvent.getEventId())
                .build();

        PatientSwitchingStandardType patientSwitchingStandardType = PatientSwitchingStandardType.GP_CONNECT;

        when(registrationService.saveEvent(testEvent, EventType.INTERNAL_TRANSFER, patientSwitchingStandardType)).thenReturn(eventDAO);

        EventResponse actualResponse = gpcController.internalTransferEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.INTERNAL_TRANSFER, patientSwitchingStandardType);

        EventResponse expectedEventResponse = new EventResponse(testEvent.getEventId());
        assertEquals(expectedEventResponse.getEventId(), actualResponse.getEventId());
    }
}