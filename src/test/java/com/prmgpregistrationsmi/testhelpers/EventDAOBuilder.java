package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.Event;
import com.prmgpregistrationsmi.model.Event.EventDAO;

import java.util.UUID;

public class EventDAOBuilder {
    public static EventDAO.EventDAOBuilder withEvent(Event event) {
        return EventDAO.builder()
                .eventId(UUID.randomUUID().toString())
                .eventGeneratedDateTime(event.getEventGeneratedDateTime())
                .reportingSystemSupplier(event.getReportingSystemSupplier())
                .reportingPracticeOdsCode(event.getReportingPracticeOdsCode())
                .conversationId(event.getConversationId())
                .transferEventDateTime(event.getTransferEventDateTime())
                .payload(event.getPayload());
    }
}
