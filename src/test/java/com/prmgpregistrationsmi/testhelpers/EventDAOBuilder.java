package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.Event;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Component
public class EventDAOBuilder {

    @Autowired
    Clock clock;

    public EventDAO.EventDAOBuilder withEvent(Event event) {
        return EventDAO.builder()
                .eventId(UUID.randomUUID().toString())
                .eventGeneratedDateTime(LocalDateTime.now(clock).truncatedTo(ChronoUnit.SECONDS))
                .reportingSystemSupplier(event.getReportingSystemSupplier())
                .reportingPracticeOdsCode(event.getReportingPracticeOdsCode())
                .conversationId(event.getConversationId())
                .registrationEventDateTime(event.getRegistrationEventDateTime())
                .payload(event.getPayload());
    }
}
