package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.PatientSwitchingStandardType;
import com.prmgpregistrationsmi.model.gpc.MigrateDocumentRequest.MigrateDocumentRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateDocumentResponse.MigrateDocumentResponseEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponseEvent;
import com.prmgpregistrationsmi.model.gpc.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateDocumentRequestEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateDocumentResponseEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateStructuredRecordRequestEventBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateStructuredRecordResponseEventBuilder;
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
    void registrationStartedEventReturnsEventResponse() throws UnableToUploadToS3Exception {
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
        assertEquals(actualResponse, expectedEventResponse);
    }

    @Test
    void MigrateStructuredRecordRequestEventReturnsEventResponse() throws UnableToUploadToS3Exception {
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
        assertEquals(actualResponse, expectedEventResponse);
    }

    @Test
    void MigrateStructuredRecordResponseEventReturnsEventResponse() throws UnableToUploadToS3Exception {
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
        assertEquals(actualResponse, expectedEventResponse);
    }

    @Test
    void MigrateDocumentRequestEventReturnsEventResponse() throws UnableToUploadToS3Exception {
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
        assertEquals(actualResponse, expectedEventResponse);
    }

    @Test
    void MigrateDocumentResponseEventReturnsEventResponse() throws UnableToUploadToS3Exception {
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
        assertEquals(expectedEventResponse, actualResponse);
    }
}