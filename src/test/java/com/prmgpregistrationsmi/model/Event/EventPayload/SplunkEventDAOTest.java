package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.SplunkEventDAO;
import com.prmgpregistrationsmi.testhelpers.BaseEventBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class SplunkEventDAOTest {
    @Test
    void fromEventShouldReturnSplunkEventDAO() {
        EventDAO testEventDAO = EventDAO.fromEvent(BaseEventBuilder.withDefaultEventValues().build(), EventType.REGISTRATIONS, null);

        SplunkEventDAO actualSplunkEventDAO = SplunkEventDAO.fromEventDAO(testEventDAO);

        assertEquals("itoc:gp2gp", actualSplunkEventDAO.getSource());
        assertEquals(testEventDAO, actualSplunkEventDAO.getEvent());
    }
}
