package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.preTransfer.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.testhelpers.preTransfer.RegistrationStartedEventBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventDAOTest {
    @Test
    void fromEventShouldReturnEventDAO() {

        RegistrationStartedEvent testEvent = RegistrationStartedEventBuilder.withDefaultEventValues().build();
        EventType gp2gpRegistrationStartedEventType = EventType.REGISTRATION_STARTED;
        TransferProtocol transferProtocol = TransferProtocol.GP2GP;
        EventDAO actualEventDAO = EventDAO.fromEvent(testEvent, gp2gpRegistrationStartedEventType, transferProtocol);

        assertEquals(actualEventDAO.getEventId(), testEvent.getEventId());
        assertEquals(actualEventDAO.getEventGeneratedDateTime(), testEvent.getEventGeneratedDateTime());
        assertEquals(actualEventDAO.getEventType(), gp2gpRegistrationStartedEventType);
        assertEquals(actualEventDAO.getTransferProtocol(), transferProtocol);
        assertEquals(actualEventDAO.getRegistrationId(), testEvent.getRegistrationId());
        assertEquals(actualEventDAO.getReportingSystemSupplier(), testEvent.getReportingSystemSupplier());
        assertEquals(actualEventDAO.getReportingPracticeOdsCode(), testEvent.getReportingPracticeOdsCode());
        assertEquals(actualEventDAO.getPayload(), testEvent.getPayload());
    }
}
