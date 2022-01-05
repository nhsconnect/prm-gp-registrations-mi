package com.prmgpregistrationsmi.model.Event;

import com.prmgpregistrationsmi.model.gp2gp.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.testhelpers.gp2gp.RegistrationStartedEventBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventDAOTest {
    @Test
    void fromEventShouldReturnEventDAO() {

        RegistrationStartedEvent testEvent = RegistrationStartedEventBuilder.withDefaultEventValues().build();
        EventType gp2gpRegistrationStartedEventType = EventType.REGISTRATION_STARTED;
        EventDAO actualEventDAO = EventDAO.fromEvent(testEvent, gp2gpRegistrationStartedEventType);

        assertEquals(actualEventDAO.getEventId(), testEvent.getEventId());
        assertEquals(actualEventDAO.getEventGeneratedDateTime(), testEvent.getEventGeneratedDateTime());
        assertEquals(actualEventDAO.getEventType(), gp2gpRegistrationStartedEventType);
        assertEquals(actualEventDAO.getRegistrationId(), testEvent.getRegistrationId());
        assertEquals(actualEventDAO.getReportingSystemSupplier(), testEvent.getReportingSystemSupplier());
        assertEquals(actualEventDAO.getReportingPracticeOdsCode(), testEvent.getReportingPracticeOdsCode());
        assertEquals(actualEventDAO.getPayload(), testEvent.getPayload());
    }
}
