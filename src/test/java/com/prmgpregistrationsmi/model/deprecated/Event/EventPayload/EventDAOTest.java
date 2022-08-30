package com.prmgpregistrationsmi.model.deprecated.Event.EventPayload;

import com.prmgpregistrationsmi.model.deprecated.Event.EventDAO;
import com.prmgpregistrationsmi.model.deprecated.Event.EventType;
import com.prmgpregistrationsmi.model.deprecated.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.testhelpers.preTransfer.RegistrationStartedEventBuilder;
import com.prmgpregistrationsmi.utils.UUIDService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventDAOTest {

    @Test
    void fromEventShouldReturnEventDAO() {
        LocalDateTime timeNow = LocalDateTime.of(2020, 1, 1, 22, 22, 22);
        String fixedLocalDateTime = "2020-01-01T22:22:22";

        RegistrationStartedEvent testEvent = RegistrationStartedEventBuilder.withDefaultEventValues().build();
        EventType gp2gpRegistrationStartedEventType = EventType.REGISTRATION_STARTED;
        TransferProtocol transferProtocol = TransferProtocol.GP2GP;
        EventDAO actualEventDAO = EventDAO.fromEvent(testEvent, gp2gpRegistrationStartedEventType, transferProtocol, timeNow);
        String eventIdSeed = testEvent.getConversationId() + gp2gpRegistrationStartedEventType + fixedLocalDateTime;

        assertEquals(actualEventDAO.getEventId(), UUIDService.buildUUIDStringFromSeed(eventIdSeed));
        assertEquals(actualEventDAO.getEventGeneratedDateTime(), timeNow);
        assertEquals(actualEventDAO.getEventType(), gp2gpRegistrationStartedEventType);
        assertEquals(actualEventDAO.getTransferProtocol(), transferProtocol);
        assertEquals(actualEventDAO.getReportingSystemSupplier(), testEvent.getReportingSystemSupplier());
        assertEquals(actualEventDAO.getReportingPracticeOdsCode(), testEvent.getReportingPracticeOdsCode());
        assertEquals(actualEventDAO.getConversationId(), testEvent.getConversationId());
        assertEquals(actualEventDAO.getRegistrationEventDateTime(), testEvent.getRegistrationEventDateTime());
        assertEquals(actualEventDAO.getPayload(), testEvent.getPayload());
    }
}
