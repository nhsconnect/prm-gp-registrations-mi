package com.prmgpregistrationsmi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventDAOTest {
    @Test
    void fromEventShouldReturnEventDAO() {
        Event testEvent = Event.builder().build();
        EventType gp2gpRegistrationStartedEventType = EventType.GP2GP_REGISTRATION_STARTED;
        EventDAO actualEventDAO = EventDAO.fromEvent(testEvent, gp2gpRegistrationStartedEventType);

        assertEquals(actualEventDAO.getEventId(), testEvent.getEventId());
        assertEquals(actualEventDAO.getEventGeneratedTimestamp(), testEvent.getEventGeneratedTimestamp());
        assertEquals(actualEventDAO.getEventType(), gp2gpRegistrationStartedEventType);
        assertEquals(actualEventDAO.getRegistrationId(), testEvent.getRegistrationId());
        assertEquals(actualEventDAO.getReportingSystemSupplier(), testEvent.getReportingSystemSupplier());
        assertEquals(actualEventDAO.getReportingPracticeOdsCode(), testEvent.getReportingPracticeOdsCode());
        assertEquals(actualEventDAO.getPayload(), testEvent.getPayload());
    }
}