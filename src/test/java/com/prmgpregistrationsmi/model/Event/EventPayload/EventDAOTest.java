package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.stage.EhrDegrades.EhrDegradesEvent;
import com.prmgpregistrationsmi.model.Event.stage.Registrations.RegistrationsEvent;
import com.prmgpregistrationsmi.testhelpers.stage.EhrDegradesEventBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.RegistrationsEventBuilder;
import com.prmgpregistrationsmi.utils.UUIDService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventDAOTest {

    @Test
    void fromEventShouldReturnEventDAO() {
        LocalDateTime timeNow = LocalDateTime.of(2020, 1, 1, 22, 22, 22);
        String fixedLocalDateTime = "2020-01-01T22:22:22";

        RegistrationsEvent testEvent = RegistrationsEventBuilder.withDefaultEventValues().build();
        EventType gp2gpRegistrationEventType = EventType.REGISTRATIONS;
        EventDAO actualEventDAO = EventDAO.fromEvent(testEvent, gp2gpRegistrationEventType, timeNow);
        String eventIdSeed = testEvent.getConversationId() + gp2gpRegistrationEventType + fixedLocalDateTime;

        assertEquals(actualEventDAO.getEventId(), UUIDService.buildUUIDStringFromSeed(eventIdSeed));
        assertEquals(actualEventDAO.getEventGeneratedDateTime(), timeNow);
        assertEquals(actualEventDAO.getEventType(), gp2gpRegistrationEventType);
        assertEquals(actualEventDAO.getReportingSystemSupplier(), testEvent.getReportingSystemSupplier());
        assertEquals(actualEventDAO.getReportingPracticeOdsCode(), testEvent.getReportingPracticeOdsCode());
        assertEquals(actualEventDAO.getConversationId(), testEvent.getConversationId());
        assertEquals(actualEventDAO.getRegistrationEventDateTime(), testEvent.getRegistrationEventDateTime());
        assertEquals(actualEventDAO.getPayload(), testEvent.getPayload());
    }

    @Test
    void fromEhrDegradesEventShouldReturnEventDAO() {
        LocalDateTime timeNow = LocalDateTime.of(2020, 1, 1, 0, 0, 0);

        EhrDegradesEvent testEvent = EhrDegradesEventBuilder.withDefaultEventValues().build();
        EventType gp2gpRegistrationEventType = EventType.DEGRADES;
        EventDAO actualEventDAO = EventDAO.fromEvent(testEvent, gp2gpRegistrationEventType, timeNow);

        assertEquals(actualEventDAO.getEventId().length(), 36);
        assertEquals(actualEventDAO.getEventGeneratedDateTime(), timeNow);
        assertEquals(actualEventDAO.getEventType(), gp2gpRegistrationEventType);
        assertEquals(actualEventDAO.getReportingSystemSupplier(), testEvent.getReportingSystemSupplier());
        assertEquals(actualEventDAO.getPayload(), testEvent.getPayload());
    }
}
