package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.deprecated.Event.Event;
import com.prmgpregistrationsmi.model.deprecated.Event.EventDAO;

import java.util.UUID;

public class EventDAOBuilder {
    public static EventDAO.EventDAOBuilder withEvent(Event event) {
        return EventDAO.builder()
                .eventId(UUID.randomUUID().toString())
                .eventGeneratedDateTime(event.getEventGeneratedDateTime())
                .reportingSystemSupplier(event.getReportingSystemSupplier())
                .reportingPracticeOdsCode(event.getReportingPracticeOdsCode())
                .conversationId(event.getConversationId())
                .registrationEventDateTime(event.getRegistrationEventDateTime())
                .payload(event.getPayload());
    }
}
