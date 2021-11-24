package com.prmgpregistrationsmi.model;

import com.prmgpregistrationsmi.testhelpers.DataBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventDAOTest {
    @Test
    void fromEventShouldReturnEventDAO() {

        RegistrationStartedEvent testEvent = DataBuilder.withDefaultEventValues().build();
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
