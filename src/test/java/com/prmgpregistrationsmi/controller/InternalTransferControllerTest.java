package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.InternalTransfer.InternalTransferEvent;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.InternalTransferEventBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InternalTransferControllerTest {

    @Mock
    private RegistrationService registrationService;
    private InternalTransferController internalTransferController;

    @BeforeEach
    void setUp() {
        internalTransferController =  new InternalTransferController(registrationService);
    }

    @Test
    void shouldReturnEventIdWhenReceivingInternalTransferEvent() throws UnableToUploadToS3Exception {
        InternalTransferEvent testEvent = InternalTransferEventBuilder
                .withDefaultEventValues()
                .build();

        EventDAO eventDAO = EventDAO.builder().build();

        TransferProtocol transferProtocol = TransferProtocol.INTERNAL_TRANSFER;

        when(registrationService.saveEvent(testEvent, EventType.INTERNAL_TRANSFER, transferProtocol)).thenReturn(eventDAO);

        EventResponse actualResponse = internalTransferController.internalTransferEvent(testEvent);

        verify(registrationService).saveEvent(testEvent, EventType.INTERNAL_TRANSFER, transferProtocol);

        assertEquals(eventDAO.getEventId(), actualResponse.getEventId());
    }
}