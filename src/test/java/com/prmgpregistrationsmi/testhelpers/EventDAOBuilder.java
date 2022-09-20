package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.*;
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

    public EventDAO.EventDAOBuilder withEvent(BaseEvent event) {
        Payload payload = null;
        if(event instanceof PayloadEvent) {
            payload = ((PayloadEvent<?>)event).getPayload();
        }
        return EventDAO.builder()
                .eventId(UUID.randomUUID().toString())
                .eventGeneratedDateTime(LocalDateTime.now(clock).truncatedTo(ChronoUnit.SECONDS))
                .reportingSystemSupplier(event.getReportingSystemSupplier())
                .reportingPracticeOdsCode(event.getReportingPracticeOdsCode())
                .conversationId(event.getConversationId())
                .registrationEventDateTime(event.getRegistrationEventDateTime())
                .payload(payload);
    }

    public EventDAO.EventDAOBuilder withEvent(BaseEventWithOptionalSendingPracticeOdsCode event) {
        Payload payload = null;
        if(event instanceof PayloadEventWithOptionalSendingPracticeOdsCode) {
            payload = ((PayloadEventWithOptionalSendingPracticeOdsCode<?>)event).getPayload();
        }
        return EventDAO.builder()
                .eventId(UUID.randomUUID().toString())
                .eventGeneratedDateTime(LocalDateTime.now(clock).truncatedTo(ChronoUnit.SECONDS))
                .reportingSystemSupplier(event.getReportingSystemSupplier())
                .reportingPracticeOdsCode(event.getReportingPracticeOdsCode())
                .conversationId(event.getConversationId())
                .registrationEventDateTime(event.getRegistrationEventDateTime())
                .payload(payload);
    }
}
