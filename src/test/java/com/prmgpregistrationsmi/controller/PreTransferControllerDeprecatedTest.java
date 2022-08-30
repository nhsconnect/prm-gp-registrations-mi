package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.deprecated.Event.EventDAO;
import com.prmgpregistrationsmi.model.deprecated.Event.EventResponse;
import com.prmgpregistrationsmi.model.deprecated.Event.EventType;
import com.prmgpregistrationsmi.model.deprecated.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.PdsGeneralUpdate.PdsGeneralUpdateEvent;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.PdsTrace.PdsTraceEvent;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.SdsLookup.SdsLookupEvent;
import com.prmgpregistrationsmi.service.RegistrationService;
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
class PreTransferControllerDeprecatedTest {
    @Mock
    private RegistrationService registrationService;
    private PreTransferControllerDeprecated preTransferControllerDeprecated;

    @BeforeEach
    void setUp() {
        preTransferControllerDeprecated =  new PreTransferControllerDeprecated(registrationService);
    }

    @Test
    void shouldReturnEventIdWhenReceivingRegistrationStartedEvent() throws UnableToUploadToS3Exception {
        RegistrationStartedEvent testEvent = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();
        
        TransferProtocol transferProtocol = TransferProtocol.PRE_TRANSFER;

        when(registrationService.saveEvent(testEvent, EventType.REGISTRATION_STARTED, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = preTransferControllerDeprecated.registrationStartedEvent(testEvent);

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

        EventResponse actualResponse = preTransferControllerDeprecated.pdsTraceEvent(testEvent);

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

        EventResponse actualResponse = preTransferControllerDeprecated.pdsGeneralUpdateEvent(testEvent);

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

        EventResponse actualResponse = preTransferControllerDeprecated.sdsLookupEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.SDS_LOOKUP, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }
}