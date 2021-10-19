package com.prmgpregistrationsmi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventDAOTest {
    @Test
    void fromEventShouldReturnEventDAO() {
        Event testEvent = Event.builder().build();
        EventDAO actualEventDAO = EventDAO.fromEvent(testEvent);

        assertEquals(actualEventDAO.getEventId(), testEvent.getEventId());
        assertEquals(actualEventDAO.getEventGeneratedTimestamp(), testEvent.getEventGeneratedTimestamp());
        assertEquals(actualEventDAO.getRegistrationId(), testEvent.getRegistrationId());
        assertEquals(actualEventDAO.getReportingSystemSupplier(), testEvent.getReportingSystemSupplier());
        assertEquals(actualEventDAO.getReportingPracticeOdsCode(), testEvent.getReportingPracticeOdsCode());
        assertEquals(actualEventDAO.getPayload(), testEvent.getPayload());
    }
}
