package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.stage.Registration.RegistrationEvent;
import com.prmgpregistrationsmi.testhelpers.preTransfer.RegistrationEventBuilder;
import com.prmgpregistrationsmi.utils.UUIDService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventDAOTest {

    @Test
    void fromEventShouldReturnEventDAO() {
        LocalDateTime timeNow = LocalDateTime.of(2020, 1, 1, 22, 22, 22);
        String fixedLocalDateTime = "2020-01-01T22:22:22";

        RegistrationEvent testEvent = RegistrationEventBuilder.withDefaultEventValues().build();
        EventType gp2gpRegistrationEventType = EventType.REGISTRATION_STARTED;
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
}
